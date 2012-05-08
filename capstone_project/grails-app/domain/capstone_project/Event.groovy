package capstone_project

import org.joda.time.DateTime
import org.joda.time.Minutes
 
import org.joda.time.DateTime
import org.joda.time.Minutes

//	This domain is representative of a an event in the OMG system.
//  Each event is its own self-contained object.
class Event {
    
    String title		//title of the event
    String location		//location of the event
	String description	//short description of the purpose of the event

    Date startTime		//start of the event, DateTime format
    Date endTime		//end of the event, DateTime format

    // 	Recurring Options, we do not use the recurring functionality provided by 
	//	fullCalendar because we could not get it to work correctly
    boolean isRecurring = false
    EventRecurType recurType
    Integer recurInterval = 1
    Date recurUntil
    Integer recurCount
    Event sourceEvent

	//	domain mappings
	//	recurDaysOfWeek and excludeDays must be included for hibernate to map correctly, but we do not use them in OMG
    static hasMany = [recurDaysOfWeek: Integer, excludeDays: Date]
	//	transients are datamembers of the domain that are explicitly not stored within the database
	//	durationMinutes is calculated locally and changes dynamically.  thus it makes no sense to store it permanently in the database
    static transients = ['durationMinutes']

	//	an object of type eventService, source found in capstone_project/event
	//	the eventService off loads much of the logic for events outside of the domain.
    def eventService

	//	constraints for events
	//  used for input validation and sanitization.
    static constraints = {
        title(nullable: false, blank: false)	//eg - title can not be null, and can not be blank
        location(nullable: true, blank:  true)
        description(nullable: true, blank: true)
        
		//not used
		recurType(nullable: true)
        recurInterval(nullable: true)
        recurUntil(nullable: true)
        recurCount(nullable: true)
        startTime(nullable: false)
        excludeDays(nullable: true)
        sourceEvent(nullable: true)
        
		startTime(required: true, nullable: false)
		//	endTime has a validator function.  a validator is a lightweight function whose purpose is to validate data entered by the user
		//	is this case, we must make sure the endTime occurs after the start time
		endTime(required: true, nullable: false, validator: {val, obj -> val > obj.startTime} )
	
		//not used
        recurDaysOfWeek(validator: {val, obj -> 
            if (obj.recurType == EventRecurType.WEEKLY && !val) {return 'null'}
        })
    }

	//member methods
    public int getDurationMinutes() {
		//I:	none
		//O:	calculates duration in minutes
		//R:	returns this duration
        Minutes.minutesBetween(new DateTime(startTime), new DateTime(endTime)).minutes
    }

	//not used
    private void updateRecurringValues() {
        if (!isRecurring) {
            recurType = null
            recurCount = null
            recurInterval = null
            recurUntil = null
            excludeDays?.clear()
            recurDaysOfWeek?.clear()
        }

        // Set recurUntil date based on the recurCount value
        if (recurCount && !recurUntil) {
           Date recurCountDate = startTime

           for (int i in 1..(recurCount - 1)) {
               recurCountDate = eventService.findNextOccurrence(this, new DateTime(recurCountDate).plusMinutes(1).toDate())
           }

           recurUntil = new DateTime(recurCountDate).plusMinutes(durationMinutes).toDate()
        }
        
    }
	
	//	method automatically called before update
    def beforeUpdate() {
        //updateRecurringValues()	//commented out to due to issues with saving recurring events correctly
    }
    
	//	method automatically called before insert
    def beforeInsert() {
        //updateRecurringValues()	//commented out to due to issues with saving recurring events correctly
    }
    
	//	method automatically called before delete
    def beforeDelete() {
		//query the database to find the event with this id
        def associatedEvents = Event.withCriteria {
            eq('sourceEvent.id', this.id)
        }

		//set each event satisfying the query to null
        associatedEvents.each{def event ->
            event.with {
                sourceEvent = null
                save(flush: true)	//push update to db
            }
        }
        
    }
    

}

//not used
public enum EventRecurType {
    DAILY('Daily'),
    WEEKLY('Weekly'),
    MONTHLY('Monthly'),
    YEARLY('Yearly')

    String name

    EventRecurType(String name) {
        this.name = name
    }
}