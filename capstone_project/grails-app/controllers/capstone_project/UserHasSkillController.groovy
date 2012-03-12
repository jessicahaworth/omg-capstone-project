package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class UserHasSkillController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userHasSkillInstanceList: UserHasSkill.list(params), userHasSkillInstanceTotal: UserHasSkill.count()]
    }

    def create() {
        [userHasSkillInstance: new UserHasSkill(params)]
    }

    def save() {
        def userHasSkillInstance = new UserHasSkill(params)
        if (!userHasSkillInstance.save(flush: true)) {
            render(view: "create", model: [userHasSkillInstance: userHasSkillInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), userHasSkillInstance.id])
        redirect(action: "show", id: userHasSkillInstance.id)
    }

    def show() {
        def userHasSkillInstance = UserHasSkill.get(params.id)
        if (!userHasSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "list")
            return
        }

        [userHasSkillInstance: userHasSkillInstance]
    }

    def edit() {
        def userHasSkillInstance = UserHasSkill.get(params.id)
        if (!userHasSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "list")
            return
        }

        [userHasSkillInstance: userHasSkillInstance]
    }

    def update() {
        def userHasSkillInstance = UserHasSkill.get(params.id)
        if (!userHasSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userHasSkillInstance.version > version) {
                userHasSkillInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userHasSkill.label', default: 'UserHasSkill')] as Object[],
                          "Another user has updated this UserHasSkill while you were editing")
                render(view: "edit", model: [userHasSkillInstance: userHasSkillInstance])
                return
            }
        }

        userHasSkillInstance.properties = params

        if (!userHasSkillInstance.save(flush: true)) {
            render(view: "edit", model: [userHasSkillInstance: userHasSkillInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), userHasSkillInstance.id])
        redirect(action: "show", id: userHasSkillInstance.id)
    }

    def delete() {
        def userHasSkillInstance = UserHasSkill.get(params.id)
        if (!userHasSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userHasSkillInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
