/* Copyright 2004-2005 Graeme Rocher
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
class TomcatGrailsPlugin {
    // the plugin version
    def version = "2.0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
	    "grails-app/domain/*.groovy",
	    "grails-app/controllers/*.groovy"		
    ]
    // don't package in war
    def scopes = [excludes:'war']
	
    def author = "Graeme Rocher"
    def authorEmail = "graeme.rocher@springsource.com"
    def title = "Apache Tomcat plugin for Grails"
    def description = '''
Makes Tomcat 7.0 the default servlet container for Grails at development time.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/tomcat"

}
