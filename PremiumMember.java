
/**
 * Write a description of class PremiumMember here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PremiumMember extends GymMember{
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
        return this.discountAmount;
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
        if (isFullPayment==true){
            this.discountAmount=0.10*getPremiumCharge(); 
            System.out.println("Discount amount: "+getDiscountAmount());
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
}