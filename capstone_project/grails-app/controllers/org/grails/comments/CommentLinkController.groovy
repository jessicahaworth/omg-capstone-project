package org.grails.comments

import org.springframework.dao.DataIntegrityViolationException

class CommentLinkController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [commentLinkInstanceList: CommentLink.list(params), commentLinkInstanceTotal: CommentLink.count()]
    }

    def create() {
        [commentLinkInstance: new CommentLink(params)]
    }

    def save() {
        def commentLinkInstance = new CommentLink(params)
        if (!commentLinkInstance.save(flush: true)) {
            render(view: "create", model: [commentLinkInstance: commentLinkInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), commentLinkInstance.id])
        redirect(action: "show", id: commentLinkInstance.id)
    }

    def show() {
        def commentLinkInstance = CommentLink.get(params.id)
        if (!commentLinkInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "list")
            return
        }

        [commentLinkInstance: commentLinkInstance]
    }

    def edit() {
        def commentLinkInstance = CommentLink.get(params.id)
        if (!commentLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "list")
            return
        }

        [commentLinkInstance: commentLinkInstance]
    }

    def update() {
        def commentLinkInstance = CommentLink.get(params.id)
        if (!commentLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (commentLinkInstance.version > version) {
                commentLinkInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'commentLink.label', default: 'CommentLink')] as Object[],
                          "Another user has updated this CommentLink while you were editing")
                render(view: "edit", model: [commentLinkInstance: commentLinkInstance])
                return
            }
        }

        commentLinkInstance.properties = params

        if (!commentLinkInstance.save(flush: true)) {
            render(view: "edit", model: [commentLinkInstance: commentLinkInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), commentLinkInstance.id])
        redirect(action: "show", id: commentLinkInstance.id)
    }

    def delete() {
        def commentLinkInstance = CommentLink.get(params.id)
        if (!commentLinkInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "list")
            return
        }

        try {
            commentLinkInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commentLink.label', default: 'CommentLink'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
