package capstone_project

class ProfilePage {

	static constraints = {
		userID(blank:false, unique:true)
		password(blank:false, password:true)
		lastName(blank:false, maxSize:50)
		firstName(blank:false, maxSize:50)
		emailAddress(email:true)
		phoneNumber(matches:" [0-9] +")
		skillSet()
		lastUpdated()
		dateCreated()
		}
	String userID
	String password
	String firstName
	String lastName
	String phoneNumber
	Date dateCreated
	Date lastUpdated
	String skillSet
	String emailAddress
	
	
}
