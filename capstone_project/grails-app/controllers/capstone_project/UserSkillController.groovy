package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class UserSkillController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userSkillInstanceList: UserSkill.list(params), userSkillInstanceTotal: UserSkill.count()]
    }

    def create() {
        [userSkillInstance: new UserSkill(params)]
    }

    def save() {
        def userSkillInstance = new UserSkill(params)
        if (!userSkillInstance.save(flush: true)) {
            render(view: "create", model: [userSkillInstance: userSkillInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), userSkillInstance.id])
        redirect(action: "show", id: userSkillInstance.id)
    }

    def show() {
        def userSkillInstance = UserSkill.get(params.id)
        if (!userSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "list")
            return
        }

        [userSkillInstance: userSkillInstance]
    }

    def edit() {
        def userSkillInstance = UserSkill.get(params.id)
        if (!userSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "list")
            return
        }

        [userSkillInstance: userSkillInstance]
    }

    def update() {
        def userSkillInstance = UserSkill.get(params.id)
        if (!userSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userSkillInstance.version > version) {
                userSkillInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userSkill.label', default: 'UserSkill')] as Object[],
                          "Another user has updated this UserSkill while you were editing")
                render(view: "edit", model: [userSkillInstance: userSkillInstance])
                return
            }
        }

        userSkillInstance.properties = params

        if (!userSkillInstance.save(flush: true)) {
            render(view: "edit", model: [userSkillInstance: userSkillInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), userSkillInstance.id])
        redirect(action: "show", id: userSkillInstance.id)
    }

    def delete() {
        def userSkillInstance = UserSkill.get(params.id)
        if (!userSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userSkillInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userSkill.label', default: 'UserSkill'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
