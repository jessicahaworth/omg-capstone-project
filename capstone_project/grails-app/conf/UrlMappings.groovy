class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/login")
//		"/"(view:"/index")
		"/"(controller:'omg')
		"500"(view:'/error')
	}
}
