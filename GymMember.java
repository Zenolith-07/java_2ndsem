
/**
 * Write a description of class GymMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

// Abstract class GymMember
public abstract class GymMember{
    // Protetcted attributes
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;

   
    // Creating constructor
    public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate){
        this.id = id; // Set the member ID
        this.name = name; // Set the member name
        this.location = location; // Set the member location
        this.phone = phone; // Set the member phone number
        this.email = email; // Set the member email
        this.gender = gender; // Set the member gender
        this.DOB = DOB; // Set the member date of birth
        this.membershipStartDate = membershipStartDate; // Set the membership start date
        this.attendance = 0; // Initialize attendance to 0 (new member)
        this.loyaltyPoints = 0.0; // Initialize loyalty points to 0 (new member)
        this.activeStatus = false; // Set membership status to inactive by default
    }
   
    // Creating accessor method
    // Accessor method to get the member ID
    public int getId() {
        return this.id; // Return the member ID
    }

    // Accessor method to get the member name
    public String getName() {
        return this.name; // Return the member name
    }

    // Accessor method to get the member location
    public String getLocation() {
        return this.location; // Return the member location
    }

    // Accessor method to get the member phone number
    public String getPhone() {
        return this.phone; // Return the member phone number
    }

    // Accessor method to get the member email
    public String getEmail() {
        return this.email; // Return the member email
    }

    // Accessor method to get the member gender
    public String getGender() {
        return this.gender; // Return the member gender
    }

    // Accessor method to get the member date of birth
    public String getDOB() {
        return this.DOB; // Return the member date of birth
    }

    // Accessor method to get the membership start date
    public String getMembershipStartDate() {
        return this.membershipStartDate; // Return the membership start date
    }

    // Accessor method to get the member attendance count
    public int getAttendance() {
        return this.attendance; // Return the member attendance count
    }

    // Accessor method to get the member loyalty points
    public double getLoyaltyPoints() {
        return this.loyaltyPoints; // Return the member loyalty points
    }

    //Accessor method to get members activestatus
    public boolean isActiveStatus(){
        return this.activeStatus; // Return the member active status
    }
   
    // Abstract method markAttendance() to track attendence
    public abstract void markAttendance();
   
    // Method to activate membership
    public void activateMembership(){
        activeStatus = true; // Set the membership status to active
        System.out.println("Membership is activated of " +getName()); //Print activation message
    }
   
    //Method to deactiveate member
    public void deactivateMembership(){
        if(this.activeStatus){ //Checks if the membership is currently active
            this.activeStatus = false; //Sets active status to false
            System.out.println("Membership is deactivated of " +getName());// Print deactivation message
        }else{
            System.out.println("Membership is already deactivated of " +getName()+". Please activate it first.");// Print message if already inactive
        }
    }
    // Methd to resret memebr details
    public void resetMember(){
        this.activeStatus = false; //Sets active status to false
        this.attendance = 0; //Reset attendance count o
        this.loyaltyPoints = 0.0; //Reset loyalty point to 0
        System.out.println( "Mr/Ms. "+getName()+" deatils has been reset"); //Print reset message
    }
   
    // Method to display member deatils
    public void display(){
        System.out.println("Member Details:"); //Print header
        System.out.println("ID: " + getId()); // Print member ID
        System.out.println("Name: " + getName()); // Print member name
        System.out.println("Location: " + getLocation()); // Print member location
        System.out.println("Phone: " + getPhone()); // Print member phone number
        System.out.println("Email: " + getEmail()); // Print member email
        System.out.println("Gender: " + getGender()); // Print member gender
        System.out.println("DOB: " + getDOB()); // Print member date of birth
        System.out.println("Membership Start Date: " + getMembershipStartDate()); // Print membership start date
        System.out.println("Attendance: " + getAttendance()); // Print attendance count
        System.out.println("Loyalty Points: " + getLoyaltyPoints()); // Print loyalty points
        System.out.println("Active Status: " + isActiveStatus()); // Print membership status
    }
}
