package capstone_project

import org.springframework.dao.DataIntegrityViolationException

//	this is the big controller for the project.  originally i wanted to have the entire website handled through this single
//	controller.  however, this was not possible when trying to integrate the fullCalendar plugin.  as a compromise, everything
//	but the events are handled by this controller

class OmgController {

	//custom shows...
	//this is default url for the OMG system
	def index()
	{
		//System.out.println(session.user)
		
		//if a session does not exist
		if (session.user == null)
		{
			//redirect to login
			redirect(action: "login")
		}
		else
			//else go to the user's page
			redirect(action: "showUser", id:session.user.id)
	}
	
	//auto-generated login closure.
	def login = {}
	
	//logout page
	def logout = {
		//if the session is not null
		if ( session.user != null )
			//flash message to the user with current session
			flash.message = "Goodbye ${session.user.login}"
		//set the current sessi0n to null
		session.user = null
		//redirect back to login
		redirect(action:"login")
	}
	
	/***********************************************************************/
	//	authenticate a user login
	def authenticate = {
		//search for user with given login and password
		def user = User.findByLoginAndPassword(params.login,params.password)
		//if the user can be found, user will be not null
		if(user){
			//capture user within session
			session.user = user
			//flash a nice message
			flash.message = "Hello ${user.login}!"
			//redirect to user's homepage
			redirect(controller:"omg", action:"showUser", id:user.id)
		}
		else{
			//user could not be found...redirect back to login
			flash.message = "Sorry, ${params.login}. Please try again."
			redirect(action:"login")
		}
	}

	//shows a given user's homepage
	def showUser()
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//System.out.println(params.id);
			//find the user with the given id ( passed in params )
			def userInstance = User.get(params.id)
			//if the userinstance is null
			if (!userInstance) {
				//flash message saying user with id could not be found
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				//redirect back to user list view
				redirect(action: "listUser")
				return
			}
			
			//if a userinstance is found
			//find all project member is admin of
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			//find all projects user is a member of
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			//capture this info in session
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			//pass these objects to views in params
			[	userInstance: userInstance, 
				userAdmins: userAdmins,
				userProjects: userProjects	]
		}
	}
	
	//show a certain projects page
	def showProject()
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//query the db for the project with given id
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect(action: "")
				return
			}
			//find all users who are admins of projects
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			//find all users of project
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			//capture in session for user in views
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			//pass in params for use in views
			[projectInstance: projectInstance]
		}
	}
	
	
	//custom lists
	//list users
	def listUser()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//capture max number of users in params
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			//pass the users as a list to views
			[userInstanceList: User.list(params), userInstanceTotal: User.count()]
		}
	}
	//list projects
    def listUserToProject(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
			else
			{
			//System.out.println(params);
			def projectInstance = Project.get(params.project_id)
			
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			[userInstanceList: User.list(params), 
				userInstanceTotal: User.count(), 
				projectInstance: projectInstance]
		}
	}
	
	//add a given user to a given project
	def addUserToProject(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else 
		{
			//find the user with given user id
			params.user = User.get(params.user_id)
			//find the project with the given project id
			params.project = Project.get(params.project_id)
			//System.out.println( params )
			//default admin to false
			params.admin = false
			//create a new memberOfProject object with params
			def memberofproject = new MemberOfProject(params)
			//create a query with provided info
			def query = MemberOfProject.where {
				user == params.user && project == params.project
	
			}
	
			//if the executed query finds nothing, returns null
			if ( !query.find() )
				//push the new object to db
				memberofproject.save(flush: true);
			
				
			//print the errors
			memberofproject.errors.each {
				println it
			}
			//redirect listUserToProject 
			redirect(action: "listUserToProject", params: [project_id: params.project_id] )
		}
	}
	
	//list skills to add to a project
	def listSkillToProject(params)
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			
			//query the db for a project with the given project id
			def projectInstance = Project.get(params.project_id)
	
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			
			//find project admins
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			//find project users
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			//find current projectSkills
			def projectSkills = ProjectSkill.findAllWhere(project: projectInstance)
			
			//capture in session
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			//System.out.println(projectSkills.toString());
			
			//pass to view in params
			[skillInstanceList: Skill.list(params), 
				skillInstanceTotal: Skill.count(),
				projectInstance: projectInstance,
				projectSkills: projectSkills]
		}
	}
	
	//add a skill to a project
	def addSkillToProject(params)
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the skill
			params.skill = Skill.get(params.skill_id)
			//find the project
			params.project = Project.get(params.project_id)
			//System.out.println( params )
			//init admin to false
			params.admin = false
			//create a new projectSkill object
			def projectSkill = new ProjectSkill(params)
			//prep the query
			def query = ProjectSkill.where {
				skill == params.skill && project == params.project
	
			}
			//execute the query
			if ( !query.find() )
				//if it returns null, save the new object to db
				projectSkill.save(flush: true);
			
			//print errors to console
			projectSkill.errors.each {
				println it
			}
			
			//redirect
			redirect(action: "listSkillToProject", params: [project_id: params.project_id] )
		}
	}
	
	
	//list skills to add to u a user
	def listSkillToUser(params)
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find projects user admins
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			//find projects user is part of
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			//find skills the user already ahs
			def userSkills = UserSkill.findAllWhere(user:session.user)
			//System.out.println("userskills: " + userSkills);
			
			//capture in session
			session.userAdmins = userAdmins
			session.userProjects = userProjects
			session.userSkills = userSkills
			
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			//pass in params
			[skillInstanceList: Skill.list(params), skillInstanceTotal: Skill.count()]		
		}
	}
	
	//adds a skill to a user
	def addSkillToUser(params)
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the user
			params.user = User.get(params.user_id)

			//find the skill
			params.skill = Skill.get(params.skill_id)
			//System.out.println( params )
			
			//create the new userSkill object with params
			def userskill = new UserSkill(params)
			//prep the query
			def query = UserSkill.where {
				user == params.user && skill == params.skill
	
			}
	
			//execute the query
			if ( !query.find() )
				//if nothing is found, save to db
				userskill.save(flush: true);
			
			//redirect
			redirect(action: "listSkillToUser" )
		}
	}
	
	//list projects to be added to a user
	def listProjectToUser(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			[projectInstanceList: Project.list(params), projectInstanceTotal: Project.count()]
		}
	}	
	
	//add project to a user
	def addProjectToUser(params)
	{

		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			params.user = User.get(params.user_id)
			params.project = Project.get(params.project_id)
			params.admin = false
			
			//System.out.println( params )
			def memberofproject = new MemberOfProject(params)
			def query = MemberOfProject.where {
				user == params.user && project == params.project
	
			}
	
			if ( !query.find() )
				memberofproject.save(flush: true);
				
			redirect(action: "listProjectToUser" )
		}
	}
	
	//create a new user
	def createUser()
	{
		// don't allow registration while logged in
		if (session.user)
		{
			redirect(action: "showUser", id:session.user.id)
		}
		else
		{
			[userInstance: new User(params)]
//			def user = User.findByLoginAndPassword(params.login,params.password)
//			if(user){
//				session.user = user
//				flash.message = "Hello ${user.login}!"
//				redirect(controller:"omg", action:"showUser", id:user.id)
//			}
		}
	}
	
	//save a user after update
	def saveUser() {

			def userInstance = new User(params)
			if (!userInstance.save(flush: true)) {
				render(view: "createUser", model: [userInstance: userInstance])
				return
			}
	
			flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			redirect(controller:"user", action: "login")
		}

	
	//edit a user
	def editUser() {
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the user with user id
			def userInstance = User.get(params.id)
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
			//pass user via params
			[userInstance: userInstance]
		}
	}

	//update a user
	def updateUser() {
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the user in db
			def userInstance = User.get(params.id)
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
			//version control stuff....
			if (params.version) {
				def version = params.version.toLong()
				if (userInstance.version > version) {
					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
							  [message(code: 'user.label', default: 'User')] as Object[],
							  "Another user has updated this User while you were editing")
					render(view: "edit", model: [userInstance: userInstance])
					return
				}
			}
	
			//capture the user properties from params
			userInstance.properties = params
	
			//try and save the user
			if (!userInstance.save(flush: true)) {
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
	
			//flash a message and redirect
			flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			redirect(action: "showUser", id: userInstance.id)
		}
	}
	
	
	//create a new project
	def createProject()
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//pass via params
			[userInstance:User.get(session.user.id),
				projectInstance: new Project(params)]
		}
	}
	
	//save a project
	def saveProject()
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//create a new project instance with params
			def projectInstance = new Project(params)
			//save the instance
			if (!projectInstance.save(flush: true)) {
				render(view: "create", model: [projectInstance: projectInstance])
				return
			}
			
			//create a corresponding member of project object for new project with user as admin
			def newAdmin = new MemberOfProject(
				user: session.user,
				project: projectInstance,
				admin: true)
			if (!newAdmin.save(flush: true)) {
				render(view: "createProject", model: [projectInstance: projectInstance])
				return
			}
			
			//flash and redirect
			flash.message = message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])
			redirect(action: "showProject", id: projectInstance.id)
		}
		
	}
	
	//edit a project
	def editProject()
	{
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the project ot edit
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
			//pass via params to view
			[projectInstance: projectInstance]
		}
	}
	
	//update a project
	def updateProject() {
		//session wrapper 
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//find the project to update
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
			if (params.version) {
				def version = params.version.toLong()
				if (projectInstance.version > version) {
					projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
							  [message(code: 'project.label', default: 'Project')] as Object[],
							  "Another user has updated this Project while you were editing")
					render(view: "edit", model: [projectInstance: projectInstance])
					return
				}
			}
	
			//update the project with params
			projectInstance.properties = params
	
			//save the project
			if (!projectInstance.save(flush: true)) {
				render(view: "edit", model: [projectInstance: projectInstance])
				return
			}
	
			//flash and redirect
			flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])
			redirect(action: "showProject", id: projectInstance.id)
		}
	}
	
	//create a new skill
	def createSkill() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//pass to view via params
			[skillInstance: new Skill(params)]
		}
	}

	//save a skill
	def saveSkill() {
		//session wrapper
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			//create a new skill object
			def skillInstance = new Skill(params)
			//try to save the skill
			if (!skillInstance.save(flush: true)) {
				render(view: "createSkill", model: [skillInstance: skillInstance])
				return
			}
			
			//create a new userSkill instance.  with current skill and user
			def newUserSkill = new UserSkill(
				user: session.user,
				skill: skillInstance)
			
			//save the userSkill to db
			if (!newUserSkill.save(flush: true)) {
				render(view: "createSkill", model: [skillInstance: skillInstance])
				return
			}
			
			//flash and redirect
			flash.message = message(code: 'default.created.message', args: [message(code: 'skill.label', default: 'Skill'), skillInstance.id])
			redirect(action: "showSkill", id: skillInstance.id)
		}
	}

	//show a skill
	def showSkill() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def skillInstance = Skill.get(params.id)
			if (!skillInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
				redirect(action: "list")
				return
			}
	
			[skillInstance: skillInstance]
		}
	}
	
	
	
	
}
