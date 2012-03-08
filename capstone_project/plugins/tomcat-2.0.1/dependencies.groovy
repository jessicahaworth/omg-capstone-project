
tomcatVersion = "7.0.25"

grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir	= "target/test-reports"
grails.project.dependency.resolution = {
    inherits "global" // inherit Grails' default dependencies
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
		grailsCentral()
	}
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		build( "org.apache.tomcat:tomcat-catalina-ant:$tomcatVersion" ) {
			transitive = false
		}
		build "org.apache.tomcat.embed:tomcat-embed-core:$tomcatVersion"
		build "org.apache.tomcat.embed:tomcat-embed-jasper:$tomcatVersion"	
		build "org.apache.tomcat.embed:tomcat-embed-logging-log4j:$tomcatVersion"	
		build "org.apache.tomcat.embed:tomcat-embed-logging-juli:$tomcatVersion"			
		
		// needed for JSP compilation
		runtime "org.eclipse.jdt.core.compiler:ecj:3.6.2"

        build "org.grails:grails-plugin-tomcat:${grailsVersion}"
    }

}
