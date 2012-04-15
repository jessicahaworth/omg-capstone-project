package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class OmgController {

	//custom shows...
	def index()
	{
		System.out.println(session.user)
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
			redirect(action: "showUser", id:session.user.id)
	}
	
	def calendarTest()
	{
		
	}
	
	def login = {}
	
	def logout = {
		flash.message = "Goodbye ${session.user.login}"
		session.user = null
		redirect(action:"login")
	}
	
	/***********************************************************************/
	def authenticate = {
		def user = User.findByLoginAndPassword(params.login,params.password)
		if(user){
			session.user = user
			flash.message = "Hello ${user.login}!"
			redirect(controller:"omg", action:"showUser", id:user.id)
		}
		else{
			flash.message = "Sorry, ${params.login}. Please try again."
			redirect(action:"login")
		}
	}

	
	def showUser()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def userInstance = User.get(params.id)
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect(action: "listUser")
				return
			}
			
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			[	userInstance: userInstance, 
				userAdmins: userAdmins,
				userProjects: userProjects	]
		}
	}
	
	def showProject()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect(action: "")
				return
			}
			def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
			//System.out.println("useradmins: " + userAdmins);
			
			def userProjects = MemberOfProject.findAllWhere(user: session.user)
			//System.out.println("userprojects: "+ userProjects );
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			[projectInstance: projectInstance]
		}
	}
	
	
	//custom lists
	def listUser()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			[userInstanceList: User.list(params), userInstanceTotal: User.count()]
		}
	}
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
	
	def addUserToProject(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			params.user = User.get(params.user_id)
			params.project = Project.get(params.project_id)
			//System.out.println( params )
			params.admin = false
			def memberofproject = new MemberOfProject(params)
			def query = MemberOfProject.where {
				user == params.user && project == params.project
	
			}
	
			if ( !query.find() )
				memberofproject.save(flush: true);
			
			memberofproject.errors.each {
				println it
			}
			
			redirect(action: "listUserToProject", params: [project_id: params.project_id] )
		}
	}
	
	def listSkillToProject(params)
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
			
			def projectSkills = ProjectSkill.findAllWhere(project: projectInstance)
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects;
			
			//System.out.println(projectSkills.toString());
			
			[skillInstanceList: Skill.list(params), 
				skillInstanceTotal: Skill.count(),
				projectInstance: projectInstance,
				projectSkills: projectSkills]
		}
	}
	
	def addSkillToProject(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			params.skill = Skill.get(params.skill_id)
			params.project = Project.get(params.project_id)
			//System.out.println( params )
			params.admin = false
			def projectSkill = new ProjectSkill(params)
			def query = ProjectSkill.where {
				skill == params.skill && project == params.project
	
			}
	
			if ( !query.find() )
				projectSkill.save(flush: true);
			
			projectSkill.errors.each {
				println it
			}
			
			redirect(action: "listSkillToProject", params: [project_id: params.project_id] )
		}
	}
	
	def listSkillToUser(params)
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
			
			def userSkills = UserSkill.findAllWhere(user:session.user)
			//System.out.println("userskills: " + userSkills);
			
			session.userAdmins = userAdmins
			session.userProjects = userProjects
			session.userSkills = userSkills
			
			params.max = Math.min(params.max ? params.int('max') : 10, 100)
			[skillInstanceList: Skill.list(params), skillInstanceTotal: Skill.count()]		
		}
	}
	
	def addSkillToUser(params)
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			params.user = User.get(params.user_id)
			params.skill = Skill.get(params.skill_id)
			//System.out.println( params )
			def userskill = new UserSkill(params)
			def query = UserSkill.where {
				user == params.user && skill == params.skill
	
			}
	
			if ( !query.find() )
				userskill.save(flush: true);
				
			redirect(action: "listSkillToUser" )
		}
	}
	
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
	
	def createUser()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			[userInstance: new User(params)]
		}
	}
	
	def saveUser() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def userInstance = new User(params)
			if (!userInstance.save(flush: true)) {
				render(view: "createUser", model: [userInstance: userInstance])
				return
			}
	
			flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			redirect(controller:"user", action: "login")
		}
	}
	
	def editUser() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def userInstance = User.get(params.id)
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
			[userInstance: userInstance]
		}
	}

	def updateUser() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def userInstance = User.get(params.id)
			if (!userInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
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
	
			userInstance.properties = params
	
			if (!userInstance.save(flush: true)) {
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
	
			flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
			redirect(action: "showUser", id: userInstance.id)
		}
	}
	
	def createProject()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			[userInstance:User.get(session.user.id),
				projectInstance: new Project(params)]
		}
	}
	
	def saveProject()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def projectInstance = new Project(params)
			if (!projectInstance.save(flush: true)) {
				render(view: "create", model: [projectInstance: projectInstance])
				return
			}
			
			def newAdmin = new MemberOfProject(
				user: session.user,
				project: projectInstance,
				admin: true)
			if (!newAdmin.save(flush: true)) {
				render(view: "createProject", model: [projectInstance: projectInstance])
				return
			}
			
			
			flash.message = message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])
			redirect(action: "showProject", id: projectInstance.id)
		}
		
	}
	
	def editProject()
	{
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect(action: "showUser", id:session.user.id)
				return
			}
	
			[projectInstance: projectInstance]
		}
	}
	
	def updateProject() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
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
	
			projectInstance.properties = params
	
			if (!projectInstance.save(flush: true)) {
				render(view: "edit", model: [projectInstance: projectInstance])
				return
			}
	
			flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])
			redirect(action: "showProject", id: projectInstance.id)
		}
	}
	
	def createSkill() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			[skillInstance: new Skill(params)]
		}
	}

	def saveSkill() {
		if (session.user == null)
		{
			redirect(action: "login")
		}
		else
		{
			def skillInstance = new Skill(params)
			if (!skillInstance.save(flush: true)) {
				render(view: "createSkill", model: [skillInstance: skillInstance])
				return
			}
			
			def newUserSkill = new UserSkill(
				user: session.user,
				skill: skillInstance)
			
			if (!newUserSkill.save(flush: true)) {
				render(view: "createSkill", model: [skillInstance: skillInstance])
				return
			}
			
			flash.message = message(code: 'default.created.message', args: [message(code: 'skill.label', default: 'Skill'), skillInstance.id])
			redirect(action: "showSkill", id: skillInstance.id)
		}
	}

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
