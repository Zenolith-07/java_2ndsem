import java.io.Serializable;

/**
 * Write a description of class PremiumMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PremiumMember extends GymMember implements Serializable {
    private final double premiuimCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
   
    //Constructor class.
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer){
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
       
        //Initializing PremiumMember attributes.
        this.premiuimCharge=50000;
        this.paidAmount=0;
        this.isFullPayment=false;
        this.discountAmount=0;
        this.personalTrainer="Kiran Rai" ;
    }
   
    //Accessor method.
    public double getPremiumCharge(){
        return this.premiuimCharge;
    }
    public String getPersonalTrainer(){
        return this.personalTrainer;
    }
    public boolean getIsFullPayment(){
        return this.isFullPayment;
    }
    public double getDiscountAmount(){
        // Ensure discount is calculated if conditions are met and it hasn't been set.
        if (this.isFullPayment && this.discountAmount == 0) {
            calculateDiscount();
        }
        return this.discountAmount;
    }
   
    public double getPaidAmount() { // Added missing accessor
        return this.paidAmount;
    }
   
    //Overriding abstratc method markattendance.
     @Override
    public void markAttendance(){
        if(super.activeStatus){
            super.attendance++; 
            super.loyaltyPoints += 10;
        }
    }
   
    //Method to pay any due amount.
    public String payDueAmount(double paidAmount){
       this.paidAmount+=paidAmount;
        if(this.paidAmount==getPremiumCharge()){
             this.isFullPayment= true;
             System.out.println("The payment is completed, no futher payment requierd.");
        }
       
        else if(this.paidAmount>getPremiumCharge()){
            System.out.println("The paid amount exceeds the premiuim charge.");
        }
        else{
            this.isFullPayment=false;
            double remainingAmount=this.premiuimCharge-this. paidAmount;
            System.out.println("Payment was successful. Remaing amount to be paid: "+remainingAmount);
        }
       
       
        return "Sucess";
    }
   
   
    //method to calculate discount.
    public void calculateDiscount(){
        if (isFullPayment == true){
            this.discountAmount = 0.10 * getPremiumCharge(); // 10% discount
            // System.out.println("Discount amount calculated: "+this.discountAmount); // Optional: for logging
        } else {
            this.discountAmount = 0; // No discount if not fully paid
        }
    }
   
    //method to revert premium member.
    public void revertPremiumMember(){
        super.resetMember();  
        this.personalTrainer="";
        this.isFullPayment=false;
        this.paidAmount=0;
        this.discountAmount=0;
        System.out.println("Premiuim memebr reverted.");
    }
   
    //Overriding display method.
    @Override
    public void display(){
        super.display();
        System.out.println("Personal Trainer: "+getPersonalTrainer());
        System.out.println("Paid amount: "+paidAmount);
        if(getIsFullPayment()==false){
            double remainingAmount=getPremiumCharge()-this.paidAmount;
            System.out.println("Remaining amount: " +remainingAmount);
        }else{
            calculateDiscount();
        }
    }

    @Override
    public String getDisplayInfo() {
        StringBuilder sb = new StringBuilder(super.getDisplayInfo());
        sb.append("Personal Trainer: ").append(getPersonalTrainer()).append("\n");
        sb.append("Paid amount: ").append(paidAmount).append("\n");
        sb.append("Premium Charge: ").append(String.format("%.2f", getPremiumCharge())).append("\n");
        if (!getIsFullPayment()) {
            double remainingAmount = getPremiumCharge() - this.paidAmount;
            sb.append("Remaining amount: ").append(String.format("%.2f", remainingAmount)).append("\n");
        } else {
            // Ensure discount is calculated before displaying if fully paid
            if (this.discountAmount == 0) { // Recalculate if not set, though payDueAmount should handle it
                calculateDiscount();
            }
            sb.append("Status: Fully Paid\n");
            if (getDiscountAmount() > 0) {
                 sb.append("Discount Applied (10%): ").append(String.format("%.2f", getDiscountAmount())).append("\n");
            }
        }
        return sb.toString();
    }
}