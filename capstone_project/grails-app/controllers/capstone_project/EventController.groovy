package capstone_project

//	this controller is one of the 2 controllers that users of OMG actually interface with.
//  this controller manipulates Event objects and interfaces with the fullCalendar plugin

import org.joda.time.DateTime
import org.joda.time.Instant

import grails.converters.JSON
import java.text.SimpleDateFormat

class EventController {
	//	each closure corresponds to a url
    //once again, eventService logic is provided here
	def eventService

	//index...not url associated with it.  default page rendered by the system
    def index = {
		//wrapper to insure a valid session for user exists.
		if (session.user == null)
		{
			redirect(controller:"omg", action: "login")
		}

    }

	//list events
    def list = {
        def (startRange, endRange) = [params.long('start'), params.long('end')].collect { new Instant(it  * 1000L).toDate() }

		//query
        def events = Event.withCriteria {
            or {
                and {
                    eq("isRecurring", false)
                    between("startTime", startRange, endRange)
                }
                and {
                    eq("isRecurring", true)
                    or {
                        isNull("recurUntil")
                        ge("recurUntil", startRange)
                    }
                }
            }
        }


        // iterate through to see if we need to add additional Event instances because of recurring
        // events
        def eventList = []

        def displayDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        events.each {event ->

            def dates = eventService.findOccurrencesInRange(event, startRange, endRange)

            dates.each { date ->
                DateTime startTime = new DateTime(date)
                DateTime endTime = startTime.plusMinutes(event.durationMinutes)

                /*
                    start/end and occurrenceStart/occurrenceEnd are separate because fullCalendar will use the client's local timezone (which may be different than the server's timezone)
                    start/end are used to render the events on the calendar and the occurrenceStart/occurrenceEnd values are passed along to the show popup
                */

                eventList << [
                        id: event.id,
                        title: event.title,
                        allDay: false,
                        start: displayDateFormatter.format(startTime.toDate()),
                        end: displayDateFormatter.format(endTime.toDate()),
                        occurrenceStart: startTime.toInstant().millis,
                        occurrenceEnd: endTime.toInstant().millis
                ]
            }
        }

        withFormat {
            html {
                [eventInstanceList: eventList]
            }
            json {
                render eventList as JSON
            }
        }
    }

	//create a new event
    def create = {
		if (session.user == null)
		{
			redirect(controller:"omg", action: "login")
		}

        def eventInstance = new Event()
        eventInstance.properties = params

        [eventInstance: eventInstance]
    }

	//show a specific event
    def show = {
        def (occurrenceStart, occurrenceEnd) = [params.long('occurrenceStart'), params.long('occurrenceEnd')]
        def eventInstance = Event.get(params.id)

        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else {
            def model = [eventInstance: eventInstance, occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd]

            if (request.xhr) {
                render(template: "showPopup", model: model)
            }
            else {
                model
            }
        }

    }

	//save an event
    def save = {
        def eventInstance = new Event(params)

        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "show", id: eventInstance.id)
        }
        else {
            render(view: "create", model: [eventInstance: eventInstance])
        }

    }
	
	//edit an event
    def edit = {
        def eventInstance = Event.get(params.id)
        def (occurrenceStart, occurrenceEnd) = [params.long('occurrenceStart'), params.long('occurrenceEnd')]

        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else {
            [eventInstance: eventInstance, occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd]
        }

    }

	//update an event
    def update = {
        def eventInstance = Event.get(params.id)
        String editType = params.editType

        def result = eventService.updateEvent(eventInstance, editType, params)

        if (!result.error) {
            flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "index")
        }
        if (result.error == 'not.found') {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else if (result.error == 'has.errors') {
            render(view: "edit", model: [eventInstance: eventInstance])
        }

    }


	//delete an event
    def delete = {
        def eventInstance = Event.get(params.id)
        String deleteType = params.deleteType
        Date occurrenceStart = new Instant(params.long('occurrenceStart')).toDate()

        def result = eventService.deleteEvent(eventInstance, occurrenceStart, deleteType)

        if (!result.error) {
            redirect(action: "index")
        }
        if (result.error == 'not.found') {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else if (result.error == 'has.errors') {
            redirect(action: "index")
        }
    }

    
}