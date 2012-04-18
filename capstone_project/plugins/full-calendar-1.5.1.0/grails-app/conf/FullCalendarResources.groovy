modules = {
	'full-calendar' {
		dependsOn 'jquery'
		dependsOn 'jquery-ui'
		resource url: [ plugin:'full-calendar', dir:'css/fullcalendar', file:'fullcalendar.css' ]
		resource url: [ plugin:'full-calendar', dir:'css/fullcalendar', file:'fullcalendar.print.css' ], attrs: [ media: 'print' ]
		resource url: [ plugin:'full-calendar', dir:'js/fullcalendar', file:'fullcalendar.js' ], 
            disposition:'head', nominify: true
	}
}