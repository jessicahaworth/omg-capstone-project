package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class ProjectSkillController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectSkillInstanceList: ProjectSkill.list(params), projectSkillInstanceTotal: ProjectSkill.count()]
    }

    def create() {
        [projectSkillInstance: new ProjectSkill(params)]
    }

    def save() {
        def projectSkillInstance = new ProjectSkill(params)
        if (!projectSkillInstance.save(flush: true)) {
            render(view: "create", model: [projectSkillInstance: projectSkillInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), projectSkillInstance.id])
        redirect(action: "show", id: projectSkillInstance.id)
    }

    def show() {
        def projectSkillInstance = ProjectSkill.get(params.id)
        if (!projectSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "list")
            return
        }

        [projectSkillInstance: projectSkillInstance]
    }

    def edit() {
        def projectSkillInstance = ProjectSkill.get(params.id)
        if (!projectSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "list")
            return
        }

        [projectSkillInstance: projectSkillInstance]
    }

    def update() {
        def projectSkillInstance = ProjectSkill.get(params.id)
        if (!projectSkillInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (projectSkillInstance.version > version) {
                projectSkillInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'projectSkill.label', default: 'ProjectSkill')] as Object[],
                          "Another user has updated this ProjectSkill while you were editing")
                render(view: "edit", model: [projectSkillInstance: projectSkillInstance])
                return
            }
        }

        projectSkillInstance.properties = params

        if (!projectSkillInstance.save(flush: true)) {
            render(view: "edit", model: [projectSkillInstance: projectSkillInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), projectSkillInstance.id])
        redirect(action: "show", id: projectSkillInstance.id)
    }

    def delete() {
        def projectSkillInstance = ProjectSkill.get(params.id)
        if (!projectSkillInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "list")
            return
        }

        try {
            projectSkillInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'projectSkill.label', default: 'ProjectSkill'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
