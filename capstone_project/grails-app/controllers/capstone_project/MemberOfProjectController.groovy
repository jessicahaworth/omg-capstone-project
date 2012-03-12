package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class MemberOfProjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [memberOfProjectInstanceList: MemberOfProject.list(params), memberOfProjectInstanceTotal: MemberOfProject.count()]
    }

    def create() {
        [memberOfProjectInstance: new MemberOfProject(params)]
    }

    def save() {
        def memberOfProjectInstance = new MemberOfProject(params)
        if (!memberOfProjectInstance.save(flush: true)) {
            render(view: "create", model: [memberOfProjectInstance: memberOfProjectInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), memberOfProjectInstance.id])
        redirect(action: "show", id: memberOfProjectInstance.id)
    }

    def show() {
        def memberOfProjectInstance = MemberOfProject.get(params.id)
        if (!memberOfProjectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "list")
            return
        }

        [memberOfProjectInstance: memberOfProjectInstance]
    }

    def edit() {
        def memberOfProjectInstance = MemberOfProject.get(params.id)
        if (!memberOfProjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "list")
            return
        }

        [memberOfProjectInstance: memberOfProjectInstance]
    }

    def update() {
        def memberOfProjectInstance = MemberOfProject.get(params.id)
        if (!memberOfProjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (memberOfProjectInstance.version > version) {
                memberOfProjectInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'memberOfProject.label', default: 'MemberOfProject')] as Object[],
                          "Another user has updated this MemberOfProject while you were editing")
                render(view: "edit", model: [memberOfProjectInstance: memberOfProjectInstance])
                return
            }
        }

        memberOfProjectInstance.properties = params

        if (!memberOfProjectInstance.save(flush: true)) {
            render(view: "edit", model: [memberOfProjectInstance: memberOfProjectInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), memberOfProjectInstance.id])
        redirect(action: "show", id: memberOfProjectInstance.id)
    }

    def delete() {
        def memberOfProjectInstance = MemberOfProject.get(params.id)
        if (!memberOfProjectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "list")
            return
        }

        try {
            memberOfProjectInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
