package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class OmgController {

	//custom shows...
	def showUser()
	{
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
			redirect(action: "list")
			return
		}
		
		def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
		System.out.println("useradmins: " + userAdmins);
		
		def userProjects = MemberOfProject.findAllWhere(user: session.user)
		System.out.println("userprojects: "+ userProjects );
		
		session.userAdmins = userAdmins
		session.userProjects = userProjects;
		
		[	userInstance: userInstance, 
			userAdmins: userAdmins,
			userProjects: userProjects	]
		
	}
	
	def showProject()
	{
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect(action: "list")
			return
		}
		def userAdmins = MemberOfProject.findAllWhere(user: session.user, admin: true)
		System.out.println("useradmins: " + userAdmins);
		
		def userProjects = MemberOfProject.findAllWhere(user: session.user)
		System.out.println("userprojects: "+ userProjects );
		
		session.userAdmins = userAdmins
		session.userProjects = userProjects;
		
		[projectInstance: projectInstance]
	}
	
	
	//custom lists
    def listUserToProject(params)
	{
		System.out.println(params);
		def projectInstance = Project.get(params.project_id)
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		[userInstanceList: User.list(params), 
			userInstanceTotal: User.count(), 
			projectInstance: projectInstance]
	}
	
	def addUserToProject(params)
	{

		params.user = User.get(params.user_id)
		params.project = Project.get(params.project_id)
		System.out.println( params )
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
	
	def listSkillToProject(params)
	{
		System.out.println(params);
		def projectInstance = Project.get(params.project_id)

		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		[skillInstanceList: Skill.list(params), 
			skillInstanceTotal: Skill.count(),
			projectInstance: projectInstance]
	}
	
	def addSkillToProject(params)
	{

		params.skill = Skill.get(params.skill_id)
		params.project = Project.get(params.project_id)
		System.out.println( params )
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
	
	def listSkillToUser(params)
	{
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[skillInstanceList: Skill.list(params), skillInstanceTotal: Skill.count()]		
	}
	
	def addSkillToUser(params)
	{

		params.user = User.get(params.user_id)
		params.skill = Skill.get(params.skill_id)
		System.out.println( params )
		def userskill = new UserSkill(params)
		def query = UserSkill.where {
			user == params.user && skill == params.skill

		}

		if ( !query.find() )
			userskill.save(flush: true);
			
		redirect(action: "listSkillToUser" )
	}
	
	def listProjectToUser(params)
	{
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[projectInstanceList: Project.list(params), projectInstanceTotal: Project.count()]
	}	
	
	def addProjectToUser(params)
	{

		params.user = User.get(params.user_id)
		params.project = Project.get(params.project_id)
		params.admin = false
		
		System.out.println( params )
		def memberofproject = new MemberOfProject(params)
		def query = MemberOfProject.where {
			user == params.user && project == params.project

		}

		if ( !query.find() )
			memberofproject.save(flush: true);
			
		redirect(action: "listProjectToUser" )
	}
	
	def createUser()
	{
		[userInstance: new User(params)]
	}
	
	def saveUser() {
		def userInstance = new User(params)
		if (!userInstance.save(flush: true)) {
			render(view: "createUser", model: [userInstance: userInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
		redirect(controller:"user", action: "login")
	}
	
	def editUser() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
			redirect(action: "showUser", id:session.user.id)
			return
		}

		[userInstance: userInstance]
	}

	def updateUser() {
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
	
	def editProject()
	{
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect(action: "showUser", id:session.user.id)
			return
		}

		[projectInstance: projectInstance]
	}
	
	def updateProject() {
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
