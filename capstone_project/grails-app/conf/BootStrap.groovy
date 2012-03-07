import capstone_project.User
import capstone_project.Event
import capstone_project.Project
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
		switch(GrailsUtil.environment){
			case "development":
			
			def admin = new User(
				login:"admin",
				password:"wordpass",
				role:"admin",
				lastName:"min", 
				firstName:"ad", 
				emailAddress:"admin@admin.admin",
				// try and fix this here
				)
			admin.save()
			if(admin.hasErrors()){
				println admin.errors
			}
			
			def jdoe = new User(login:"jdoe",
				password:"password",
				role:"user",
				lastName:"Doe", 
				firstName:"J", 
				emailAddress:"jdoe@nbody.com")
			jdoe.save()
			if(jdoe.hasErrors()){
				println jdoe.errors
			}
		}
    }
    def destroy = {
    }
}
