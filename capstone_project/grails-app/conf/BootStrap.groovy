import java.util.Date;

import capstone_project.User
import capstone_project.Event
import capstone_project.Project
import capstone_project.Skill
import capstone_project.MemberOfProject
import capstone_project.UserSkill
import capstone_project.ProjectSkill

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
			
			//users
			def jdoe = new User(login:"jdoe",
				password:"password",
				role:"user",
				lastName:"Doe", 
				firstName:"J", 
				emailAddress:"jdoe@nbody.com",
				
				)
			jdoe.save()
			if(jdoe.hasErrors()){
				println jdoe.errors
			}
			
			//projects
			def testProject = new Project (
				name: "testProject",
				description: "test project"
				)
			testProject.save()
			if(testProject.hasErrors()){
				println testProject.errors
			}
			
			def testProject2 = new Project (
				name: "testProject2",
				description: "test project2"
				)
			testProject2.save()
			if(testProject2.hasErrors()){
				println testProject2.errors
			}
			
			//skills
			def testSkill = new Skill(
				s_name:"testSkill",
				)
			testSkill.save()
			if(testSkill.hasErrors()){
				println testSkill.errors
			}
			
			def testSkill2 = new Skill(
				s_name:"testSkill2",
				)
			testSkill2.save()
			if(testSkill2.hasErrors()){
				println testSkill2.errors
			}
						
			/*def foo = new MemberOfProject(
				user:jdoe
				project:testProject
				admin:true
				)
			foo.save();
			if(foo.hasErrors()){
				println foo.errors
			}
			
			foo = new MemberOfProject(
				user:admin
				project:testProject
				admin:false
				)
			foo.save();
			if(foo.hasErrors()){
				println foo.errors
			}
			
			foo = new MemberOfProject(
				user:admin
				project:testProject2
				admin:true
				)
			foo.save();
			if(foo.hasErrors()){
				println foo.errors
			}*/
			
		}
    }
    def destroy = {
    }
}
