/* Copyright 2006-2007 Graeme Rocher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.comments

import grails.util.*
import javax.servlet.http.HttpServletResponse

class CommentableController {


    def add = {
        def poster = evaluatePoster()
        def commentLink
        try {
            if (params['comment'] instanceof Map) {
                Comment.withTransaction { status ->
                    def comment = new Comment(params['comment'])
                    comment.posterId = poster.id
                    comment.posterClass = poster.class.name
                    commentLink = new CommentLink(params['commentLink'])
                    commentLink.type = GrailsNameUtils.getPropertyName(commentLink.type)

                    if (!comment.save()) {
                        status.setRollbackOnly()
                    }
                    else {
                        commentLink.comment = comment
                        if (!commentLink.save()) status.setRollbackOnly()
                    }
                }
            }
        }
        catch (Exception e) {
            log.error "Error posting comment: ${e.message}"
        }

        def comments = CommentLink.withCriteria {
            projections {
                property "comment"
            }
            eq 'type', commentLink.type
            eq 'commentRef', commentLink.commentRef
            cache true
        }
        if (request.xhr || params.async) {
            def noEscape = false
            plugin.isAvailable(name: 'grails-ui') { noEscape = true }
            render template: "/commentable/comment",
				    plugin:"commentable",
                    collection: comments,
                    var: "comment",
                    model: [noEscape: noEscape]
        }
        else {
			String foo = params.commentPageURI;
			String bar = "";
			int count = 0;
			for (int i =0; i<foo.length(); i++)
			{
				if ( foo[i] == '/')
					count++;
				if ( count >= 2 )
					bar += foo[i];
			}
			params.commentPageURI = bar;
            redirect url: params.commentPageURI
        }
    }

    def delete = {
        def comment = Comment.get(params.id)

        if (comment) {
            CommentLink.findAllByComment(comment).each {
                it.delete()
            }
            comment.delete()

            if (params.commentPageURI) {
                redirect url: params.commentPageURI
            } else {
                render status: HttpServletResponse.SC_OK
            }
        } else {
            render status: HttpServletResponse.SC_NOT_FOUND
        }
    }

    private evaluatePoster() {
        def evaluator = grailsApplication.config.grails.commentable.poster.evaluator
        def poster
        if (evaluator instanceof Closure) {
            evaluator.delegate = this
            evaluator.resolveStrategy = Closure.DELEGATE_ONLY
            poster = evaluator.call()
        }

        if (!poster) {
            throw new CommentException("No [grails.commentable.poster.evaluator] setting defined or the evaluator doesn't evaluate to an entity. Please define the evaluator correctly in grails-app/conf/Config.groovy or ensure commenting is secured via your security rules")
        }
        if (!poster.id) {
            throw new CommentException("The evaluated Comment poster is not a persistent instance.")
        }
        return poster
    }
}