package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class AdminOfProjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [adminOfProjectInstanceList: AdminOfProject.list(params), adminOfProjectInstanceTotal: AdminOfProject.count()]
    }

    def create() {
        [adminOfProjectInstance: new AdminOfProject(params)]
    }

    def save() {
        def adminOfProjectInstance = new AdminOfProject(params)
        if (!adminOfProjectInstance.save(flush: true)) {
            render(view: "create", model: [adminOfProjectInstance: adminOfProjectInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), adminOfProjectInstance.id])
        redirect(action: "show", id: adminOfProjectInstance.id)
    }

    def show() {
        def adminOfProjectInstance = AdminOfProject.get(params.id)
        if (!adminOfProjectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "list")
            return
        }

        [adminOfProjectInstance: adminOfProjectInstance]
    }

    def edit() {
        def adminOfProjectInstance = AdminOfProject.get(params.id)
        if (!adminOfProjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "list")
            return
        }

        [adminOfProjectInstance: adminOfProjectInstance]
    }

    def update() {
        def adminOfProjectInstance = AdminOfProject.get(params.id)
        if (!adminOfProjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adminOfProjectInstance.version > version) {
                adminOfProjectInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'adminOfProject.label', default: 'AdminOfProject')] as Object[],
                          "Another user has updated this AdminOfProject while you were editing")
                render(view: "edit", model: [adminOfProjectInstance: adminOfProjectInstance])
                return
            }
        }

        adminOfProjectInstance.properties = params

        if (!adminOfProjectInstance.save(flush: true)) {
            render(view: "edit", model: [adminOfProjectInstance: adminOfProjectInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), adminOfProjectInstance.id])
        redirect(action: "show", id: adminOfProjectInstance.id)
    }

    def delete() {
        def adminOfProjectInstance = AdminOfProject.get(params.id)
        if (!adminOfProjectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "list")
            return
        }

        try {
            adminOfProjectInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
