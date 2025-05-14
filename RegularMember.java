
/**
 * Write a description of class RegularMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RegularMember extends GymMember {
    private final int AttendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;
   
    //Constructor class.
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource){
        //superclass constructor
        super (id, name, location, phone, email, gender, DOB, membershipStartDate);
       
        // initializing regualrmember attributes
        this.isEligibleForUpgrade=false; 
        this.AttendanceLimit=30; 
        this.plan="basic"; 
        this.price=6500;
        this.removalReason=""; 
        this.referralSource=referralSource;
    }
   
    // Creating accessor method
    public final int getAttendanceLimit(){
        return this.AttendanceLimit;
    }
    public boolean getisEligibleForUpgrade(){ 
        return this.isEligibleForUpgrade;
    }
    public String getRemovalReason(){ 
        return this.removalReason;
    }
    public String getReferralSource(){ 
        return this.referralSource;
    }
    public String getPlan(){ 
        return this.plan;
    }
    public double getPrice(){ 
        return this.price;
    }
   
    //Implementing the abstract method in markAttendance().
    @Override
    public void markAttendance(){
        if(activeStatus){
            attendance++; 
            loyaltyPoints += 10;
        }
    }
   
    // Method to get any desired plan price.
    public double getPlanPrice(String plan){
        switch(plan){
            case "basic": //Basic plan
                return 6500;
            case "standard": // Standard plan
                return 12500;
            case "deluxe": // Deluxe plan
                return 18500;
            default: //When Invalid plan is entered
                return -1;
        }
    }

    // Method to upgrade plan
    public String upgradePlan(String newPlan){
        if(getAttendance()>=AttendanceLimit){
            isEligibleForUpgrade=true;
            System.out.println("The member is eligible for upgrade.");
        }
       
        if (this.isEligibleForUpgrade==false){
            System.out.println( "The member is not eligible for upgrade.");
        }
        if (this.plan.equals(newPlan)){
            System.out.println("The upgrade not eligible, same plan is chosen");
        }
        double newPlanPrice= getPlanPrice(newPlan);
        if (newPlanPrice==-1){
            System.out.println("Invalid plan selected");
        }
       
        //Updating the plan and price.
        this.plan=newPlan;
        this.price=newPlanPrice;
        return "plan upgraded successfully";
    }
   
    //Creating method to revert to regular member.
    public void revertRegeularMember(String removalReason){
        super.resetMember();
        this.isEligibleForUpgrade=false;
        this.plan="basic";
        this.price=6500;
        this.loyaltyPoints=0.0;
        this.attendance=0;
        System.out.println("Member reverted to basic plan. Removal reason: "+removalReason);
    }
    @Override
    public void display(){
        super.display(); 
        System.out.println("Plan: "+this.plan);
        System.out.println("Price: "+this.price);
        if(getRemovalReason()!=""){
            System.out.println("Removal reason: "+getRemovalReason());
        }
    }
}
