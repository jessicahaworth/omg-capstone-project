package capstone_project

class AdminFilters {

    def filters = {
		//adminOnly(controller:'*', action:"(create|edit|update|delete|save)") {
		adminOnly(controller:'*', action:"delete") {
			before = {
				if(!session?.user?.admin){ 
					flash.message = "Sorry, admin only"
					redirect(controller:"user", action:"login")
					return false
				}
			}
		}
	}
}
