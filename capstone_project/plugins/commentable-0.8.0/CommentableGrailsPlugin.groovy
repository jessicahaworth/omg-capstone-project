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
import org.grails.comments.*
import grails.util.*

class CommentableGrailsPlugin {
	def version = "0.8.0"
	def grailsVersion = "1.1 > *"
	def loadAfter = ["hibernate"]

	def pluginExcludes = [
			"grails-app/views/test/index.gsp",
			"grails-app/views/error.gsp",
			"grails-app/controllers/TestController.groovy",
			"grails-app/domain/org/grails/comments/TestPoster.groovy",
			"grails-app/domain/org/grails/comments/TestEntry.groovy"
	]

	def author = "Graeme Rocher"
	def authorEmail = "graeme.rocher@springsource.com"
	def title = "Commentable Plugin"
	def description = """\
Adds support for comments. Mark up any of your domain classes as having comments and \
then use the tag library and partial templates to integrate comments into your views."""

	// URL to the plugin's documentation
	def documentation = "http://grails.org/Commentable+Plugin"
	def license = "APACHE"
	def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPCOMMENTABLE" ]

	def doWithSpring = {
		def config = application.config
		
		if(!config.grails.commentable.poster.evaluator) {
			config.grails.commentable.poster.evaluator = { request.user }
		}
	}

	def doWithDynamicMethods = { ctx ->
		for(domainClass in application.domainClasses) {
			if(Commentable.class.isAssignableFrom(domainClass.clazz)) {
				domainClass.clazz.metaClass {
					'static' {
						getRecentComments {->
							def clazz = delegate
							CommentLink.withCriteria {
								projections { property "comment" }
								eq 'type',GrailsNameUtils.getPropertyName(clazz)
								maxResults 5
								cache true
								comment {
									order "dateCreated", "desc"
								}
							}
						}
					}
					
					addComment { poster, String text ->
						if(delegate.id == null) throw new CommentException("You must save the entity [${delegate}] before calling addComment")
						
						def posterClass = poster.class.name
						def i = posterClass.indexOf('_$$_javassist')
						if(i>-1)
							posterClass = posterClass[0..i-1]
						
						def c = new Comment(body:text, posterId:poster.id, posterClass:posterClass)
						if(!c.validate()) {
							throw new CommentException("Cannot create comment for arguments [$poster, $text], they are invalid.")
						}
						c.save()
						def link = new CommentLink(comment:c, commentRef:delegate.id, type:GrailsNameUtils.getPropertyName(delegate.class))
						link.save()
						try {
							delegate.onAddComment(c)
						} catch (MissingMethodException e) {}
						return delegate
					}

					getComments = {->
						def instance = delegate
						if(instance.id != null) {
							CommentLink.withCriteria {
								projections {
									property "comment"
								}
								eq "commentRef", instance.id
								eq 'type', GrailsNameUtils.getPropertyName(instance.class)
								cache true
							}
						} else {
							return Collections.EMPTY_LIST
						}
					}
					
					getTotalComments = {->
						def instance = delegate
						if(instance.id != null) {
							CommentLink.createCriteria().get {
								projections {
									rowCount()
								}
								eq "commentRef", instance.id
								eq 'type', GrailsNameUtils.getPropertyName(instance.class)
								cache true
							}
						} else {
							return 0
						}
					}
					
					removeComment { Comment c ->
						CommentLink.findAllByComment(c)*.delete()
						c.delete(flush:true) // cascades deletes to links
					}
					
					removeComment { Long id ->
						def c = Comment.get(id)
						if(c) removeComment(c)
					}
				}
			}
		}
	}

}
