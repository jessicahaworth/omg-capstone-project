package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class SkillController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def addSkill(params)
	{

		params.user = User.get(params.user_id)
		params.skill = Skill.get(params.skill_id)
		System.out.println( params )
		def userskill = new UserHasSkill(params)
		def query = UserHasSkill.where {
			user == params.user && skill == params.skill		
			
		}
		 
		if ( !query.find() )
			userskill.save(flush: true);
		redirect(action: "list" )
	}
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        
		
		def query = UserHasSkill.where {
			user == session.user		
		}
		def userskillslist = query.list() 
		
		[skillInstanceList: Skill.list(params), 
			skillInstanceTotal: Skill.count(),
			userskills:userskillslist]
    }

    def create() {
        [skillInstance: new Skill(params)]
    }

    def save() {
        def skillInstance = new Skill(params)
        if (!skillInstance.save(flush: true)) {
            render(view: "create", model: [skillInstance: skillInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'skill.label', default: 'Skill'), skillInstance.id])
        redirect(action: "show", id: skillInstance.id)
    }

    def show() {
        def skillInstance = Skill.get(params.id)
        if (!skillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "list")
            return
        }

        [skillInstance: skillInstance]
    }

    def edit() {
        def skillInstance = Skill.get(params.id)
        if (!skillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "list")
            return
        }

        [skillInstance: skillInstance]
    }

    def update() {
        def skillInstance = Skill.get(params.id)
        if (!skillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (skillInstance.version > version) {
                skillInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'skill.label', default: 'Skill')] as Object[],
                          "Another user has updated this Skill while you were editing")
                render(view: "edit", model: [skillInstance: skillInstance])
                return
            }
        }

        skillInstance.properties = params

        if (!skillInstance.save(flush: true)) {
            render(view: "edit", model: [skillInstance: skillInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'skill.label', default: 'Skill'), skillInstance.id])
        redirect(action: "show", id: skillInstance.id)
    }

    def delete() {
        def skillInstance = Skill.get(params.id)
        if (!skillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "list")
            return
        }

        try {
            skillInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
