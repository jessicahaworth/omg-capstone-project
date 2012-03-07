package capstone_project

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def login = {}
	
	//def skillSet = params.list("Java","C++", "HTML", "PERL", "Prolog", "C", "Javascript", "Python", "Visual Basic", "LUA", "Cobalt", "Grails", "Fortran", "Rails", "Assembly")
	
	def beforeInterceptor = beforeInterceptor = [action:this.&auth, except:['login', 'logout', 'authenticate']]
	
	def auth() {
		if(!session.user) {
			redirect(controller:"user", action:"login")
			return false
		}
		if(!session.user.admin){
			flash.message = "Tsk tsk—admins only"
			redirect(controller:"user", action:"list")
			return false
		}
	}
	
	def debug(){
		println "DEBUG: ${actionUri} called."
		println "DEBUG: ${params}"
	}
	
	def logout = {
		flash.message = "Goodbye ${session.user.login}"
		session.user = null
		redirect(action:"login")
	}
	def authenticate = {
		def user = User.findByLoginAndPassword(params.login,params.password)
		if(user){
			session.user = user
			flash.message = "Hello ${user.login}!"
			redirect(controller:"user", action:"show", id:user.id)
		}
		else{
			flash.message = "Sorry, ${params.login}. Please try again."
			redirect(action:"login")
		}
	}
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
		
		def _toBeRemoved = userInstance.skills.findAll {!it}
		
		if (_toBeRemoved) {
			userInstance.skills.removeAll(_toBeRemoved)
		 }
		
		//update my indexes
		userInstance.skills.eachWithIndex(){skl, i ->
			if(skl)
				skl.index = i
		}
		
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }
	
	def disp(){
		def userInstance = User.get(params.userID)
		if(!userInstance){
			flash.message = message("Could not find ${params.login}")
			redirect(action:"list")
		}	
		
		[userInstance: userInstance]111
	}
	
    def show() {
        def userInstance = User.get(params.id)
		
		userInstance.comments.sort();
		/*System.out.println("before: "+userInstance.comments);
		int max = userInstance.comments.size();
		System.out.println("Max is: "+max);
		for (int i = 0; i < max; i++)
			System.out.println(i+ ": "+userInstance.comments[i].dateCreated);
		for ( int i = 0; i < max; i++)
		{
			System.out.println(i);
			for ( int j = 0; j < max; j++)
			{
				System.out.println(j);
				Date d1 = userInstance.comments[i].dateCreated;
				Date d2 =  userInstance.comments[j].dateCreated; 
				if ( d1 < d2)
				{
					System.out.println("fuck, we are swapping!");
					org.grails.comments.Comment c;
					c = new org.grails.comments.Comment();
					c =  userInstance.comments[i];
					userInstance.comments[i] = userInstance.comments[j];
					userInstance.comments[j] = c;
				}
			}
		}*/
		System.out.println("after: "+userInstance.comments.sort());
		//userInstance.comments = myComments;
		//myComments.sort()
		//userInstance.comments = myComments
        if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit() {
        def userInstance = User.get(params.id)
	
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params
		
		def _toBeDeleted = userInstance.skills.findAll {it?.deleted || !it}
		
		// if there are phones to be deleted remove them all
		if (_toBeDeleted) {
			userInstance.skills.removeAll(_toBeDeleted)
		}
		
		//update my indexes
		userInstance.skills.eachWithIndex(){skl, i ->
			if(skl)
				skl.index = i
		}
		
        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete() {
        def userInstance = User.get(params.id)
        if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
