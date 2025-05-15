import java.io.Serializable;

/**
 * Write a description of class RegularMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RegularMember extends GymMember implements Serializable {
    private final int AttendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;
    private double paidAmount;
    private boolean isFullPayment;
    private double discountAmount;
   
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
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.discountAmount = 0;
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
    public double getPaidAmount() {
        return this.paidAmount;
    }
    public boolean getIsFullPayment() {
        return this.isFullPayment;
    }
    public double getDiscountAmount() {
        // Ensure discount is calculated if conditions are met and it hasn't been set.
        if (this.isFullPayment && this.discountAmount == 0) {
            calculateDiscount();
        }
        return this.discountAmount;
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
        if (plan == null) return -1; // Handle null input
        switch(plan.toLowerCase()){ // Convert to lowercase for case-insensitive matching
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
        if (newPlan == null || newPlan.trim().isEmpty()) {
            System.out.println("Upgrade not eligible, new plan name is invalid.");
            return "invalid plan name";
        }

        if(getAttendance()>=AttendanceLimit){
            isEligibleForUpgrade=true;
            System.out.println("The member is eligible for upgrade.");
        }
       
        if (this.isEligibleForUpgrade==false){
            System.out.println( "The member is not eligible for upgrade.");
        }
        if (this.plan.equalsIgnoreCase(newPlan)){
            System.out.println("The upgrade not eligible, same plan is chosen");
            return "same plan chosen";
        }
        double newPlanPrice= getPlanPrice(newPlan);
        if (newPlanPrice==-1){
            System.out.println("Invalid plan selected: " + newPlan + ". Defaulting to basic or keeping current.");
            return "invalid plan selected";
        }
       
        //Updating the plan and price.
        this.plan=newPlan;
        this.price=newPlanPrice;
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.discountAmount = 0;
        System.out.println("Plan upgraded. Please make payment for the new plan: " + newPlan);
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
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.discountAmount = 0;
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

    @Override
    public String getDisplayInfo() {
        StringBuilder sb = new StringBuilder(super.getDisplayInfo());
        sb.append("Plan: ").append(this.plan).append("\n");
        sb.append("Price: ").append(String.format("%.2f", this.price)).append("\n");
        sb.append("Paid Amount: ").append(String.format("%.2f", getPaidAmount())).append("\n");
        if (!getIsFullPayment()) {
            sb.append("Remaining Amount: ").append(String.format("%.2f", (this.price - getPaidAmount()))).append("\n");
        } else {
            sb.append("Status: Fully Paid\n");
            if (getDiscountAmount() > 0) {
                sb.append("Discount Applied: ").append(String.format("%.2f", getDiscountAmount())).append("\n");
            }
        }
        sb.append("Referral Source: ").append(getReferralSource()).append("\n");
        if (getRemovalReason() != null && !getRemovalReason().isEmpty()) {
            sb.append("Removal reason: ").append(getRemovalReason()).append("\n");
        }
        return sb.toString();
    }

    // Method to calculate discount.
    public void calculateDiscount() {
        if (this.isFullPayment) {
            this.discountAmount = 0.05 * this.price; // 5% discount for full payment
            System.out.println("5% discount applied: " + this.discountAmount);
        } else {
            this.discountAmount = 0;
        }
    }

    // Method to pay due amount.
    public String payDueAmount(double payment) {
        if (payment <= 0) {
            return "Payment amount must be positive.";
        }
        this.paidAmount += payment;
        if (this.paidAmount >= this.price) {
            this.isFullPayment = true;
            this.paidAmount = this.price; // Ensure paidAmount doesn't exceed price
            calculateDiscount(); // Calculate discount upon full payment
            System.out.println("Full payment completed for plan " + this.plan + ". Discount applied if eligible.");
            return "Full payment completed.";
        } else {
            this.isFullPayment = false;
            double remaining = this.price - this.paidAmount;
            System.out.println("Payment successful. Remaining amount for plan " + this.plan + ": " + remaining);
            return "Payment successful. Remaining: " + remaining;
        }
    }
}
