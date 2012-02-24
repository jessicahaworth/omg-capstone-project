import capstone_project.ProfilePage
import capstone_project.User
import capstone_project.Event
import capstone_project.Project
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
		switch(GrailsUtil.environment){
			case "development":
			
			def profAdmin = new ProfilePage(userName:"admin", lastName:"min", firstName:"ad", emailAddress:"admin@admin.admin" , skillSet:"Chuck Norris")
			profAdmin.save()
			if(profAdmin.hasErrors()){
				println profAdmin.errors
			}
			
			def admin = new User(
				login:"admin",
				password:"wordpass",
				role:"admin",
				
				)
			admin.save()
			if(admin.hasErrors()){
				println admin.errors
			}
			
			def jdoe = new User(login:"jdoe",
				password:"password",
				role:"user")
			jdoe.save()
			if(jdoe.hasErrors()){
				println jdoe.errors
			}
		}
    }
    def destroy = {
    }
}
