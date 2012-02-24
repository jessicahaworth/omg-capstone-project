package capstone_project

class ProfilePage implements Serializable {
	
	
	String userName
	String firstName
	String lastName
	//String phoneNumber
	String emailAddress
	String skillSet
	Date dateCreated
	Date lastUpdated

	static constraints = {
		userName(blank:false, unique:true)
		lastName(blank:false, maxSize:50)
		firstName(blank:false, maxSize:50)
		emailAddress(email:true)
		//phoneNumber(matches:" [0-9] +")
		skillSet()
		}
	
	static mapping = {
		id generator:'assigned', column:'userName', name:'userName', type:'string'
		}
	

}
