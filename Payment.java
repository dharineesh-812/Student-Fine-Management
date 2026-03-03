package fineMainagement;

public class Payment {
    private static int counter = 1;

    private int paymentId;
    private String studentEmail;
    private String fineType;
    private double amount;
    private String date;
    private String collectedBy;

    public Payment(String studentEmail, String fineType, double amount, String date, String collectedBy) {
        this.paymentId = counter++;
        this.studentEmail = studentEmail;
        this.fineType = fineType;
        this.amount = amount;
        this.date = date;
        this.collectedBy = collectedBy;
    }

    public int getPaymentId(){ 
    	return paymentId; 
    	}
    public String getStudentEmail(){
    	return studentEmail; 
    	}
    public String getFineType(){return fineType;
    }
    public double getAmount(){
    	return amount;
    	}
    public String getDate(){
    	return date;
    }

    public void setAmount(double amount){
    	this.amount = amount; 
    }
}