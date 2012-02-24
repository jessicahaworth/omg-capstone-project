package capstone_project

class ProfilePageController {

	def scaffold = ProfilePage
   def show() {
        def profilePageInstance = ProfilePage.get(params.id)
        if (!profilePageInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [profilePageInstance: profilePageInstance]
    }
   
   def update() {
	   def profilePageInstance = ProfilePage.get(params.id)
	   if (!profilePageInstance) {
		   flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
		   redirect(action: "list")
		   return
	   }

	   if (params.version) {
		   def version = params.version.toLong()
		   if (profilePageInstance.version > version) {
			   profilePageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						 [message(code: 'user.label', default: 'User')] as Object[],
						 "Another user has updated this User while you were editing")
			   render(view: "edit", model: [profilePageInstance: profilePageInstance])
			   return
		   }
	   }

	   profilePageInstance.properties = params

	   if (!profilePageInstance.save(flush: true)) {
		   render(view: "edit", model: [profilePageInstance: profilePageInstance])
		   return
	   }

	   flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), profilePageInstance.id])
	   redirect(action: "show", id: profilePageInstance.userName)
   }
   
   def save() {
	   def profilePageInstance = new ProfilePage(params)
	   if (!profilePageInstance.save(flush: true)) {
		   render(view: "create", model: [profilePageInstance: profilePageInstance])
		   return
	   }

	   flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), profilePageInstance.id])
	   redirect(action: "show", id: profilePageInstance.userName)
   }
   
}
