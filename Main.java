package fineMainagement;
import java.util.*;
public class Main{
	    public static Scanner scan =new Scanner(System.in);
		static ArrayList<Users> allUsers=new ArrayList<>();
		static ArrayList<Payment> allPayments=new ArrayList<>();
		static Users currentUser=null;
		public static void main(String [] args) {
			defaultAdmin();
			while(true){
				if(currentUser==null) {
					currentUser=authUser();
				}
				else {
					 String role=currentUser.getRole();
					 if(role.equals("ADMIN")) {
						 adminPanel(currentUser);
						 break;
					 }
					 else if(role.equals("CASHIER")) {
						 cashierPanel(currentUser);
					     break;
				}
					 else if(role.equals("STUDENT")) {
						 studentPanel(currentUser);
						 break;
					 }
				}		
			}
		}
		private static void defaultAdmin() {
			Users dAdmin=new Users("admin@gmail.com","Admin","admin","ADMIN");
			allUsers.add(dAdmin);
		}
		private static Users authUser() {
			System.out.println("1.Register / 2.Login");
			int input=scan.nextInt();
			if(input == 1)
				return userRegister();
			else
				return userLogin();
		}
		
		static Users userRegister() {
			String email,name,pass,role;
			System.out.println("Enter email :");        
			email=scan.next();
			System.out.println("Enter name :");
			name=scan.next();
			System.out.println("Enter pass :");
			pass=scan.next();
			System.out.println("Enter role :");
			role=scan.next();
			Users user=new Users(email,name,pass,role);
			allUsers.add(user);
			System.out.println("Registration Completed..");
			return userLogin();
	    }
		static Users userLogin() {
			String email,pass;
			System.out.println("Enter your email");
			email=scan.next();
			System.out.print("Enter your pass");
			pass=scan.next();
			for( Users u:allUsers) {
				if(u.getEmail().equals(email.toLowerCase().trim())&& u.getPass().equals(pass.trim())) {
					System.out.println("Logged in Successfully");
					return u;
				}
			}
			System.out.println("Invalid Information");
			return null;
		}
		private static void adminPanel(Users user) {
		    while (true) {
		        System.out.println("\n--- ADMIN PANEL ---");
		        System.out.println("1. View All Payments");
		        System.out.println("2. Filter By Fine Type");
		        System.out.println("3. Filter By Student Email");
		        System.out.println("4. Add Payment");
		        System.out.println("5. Update Payment");
		        System.out.println("6. Delete Payment");
		        System.out.println("7. Logout");
		        int choice = scan.nextInt();
		        switch (choice) {
		            case 1:
		            	   	if(allPayments.isEmpty())
		            		System.out.println("No Payment Found !!");
		                for (Payment p : allPayments) {
		                    System.out.println(p.getPaymentId() + " | "+ p.getStudentEmail() + " | "
		                     + p.getFineType() + " | "+ p.getAmount() + " | "+ p.getDate());
		                }
		                break;
		            case 2:
		                System.out.println("Enter Fine Type:");
		                String type = scan.next();
		                for (Payment p : allPayments) {
		                    if (p.getFineType().equalsIgnoreCase(type)) {
		                        System.out.println(p.getPaymentId() + " | " + p.getAmount());
		                    }
		                }
		                break;
		            case 3:
		                System.out.println("Enter Student Email:");
		                String email = scan.next();
		                for (Payment p : allPayments) {
		                    if (p.getStudentEmail().equalsIgnoreCase(email)) {
		                        System.out.println(p.getPaymentId() + " | " + p.getAmount());
		                    }
		                }
		                break;
		            case 4:
		                addPayment(user);
		                break;
		            case 5:
		                updatePayment();
		                break;
		            case 6:
		                deletePayment();
		                break;
		            case 7:
		                currentUser = null;
		                return;
		        }
		    }
		}
		private static void cashierPanel(Users user) {
		    while (true) {
		        System.out.println("\n--- CASHIER PANEL ---");
		        System.out.println("1. Add Payment");
		        System.out.println("2. Update Payment");
		        System.out.println("3. Delete Payment");
		        System.out.println("4. Logout");
		        int choice = scan.nextInt();
		        switch (choice) {
		            case 1:
		                addPayment(user);
		                break;
		            case 2:
		                updatePayment();
		                break;
		            case 3:
		                deletePayment();
		                break;
		            case 4:
		                currentUser = null;
		                return;
		        }
		    }
		}
		private static void studentPanel(Users user) {
		    while (true) {
		        System.out.println("\n--- STUDENT PANEL ---");
		        System.out.println("1. View Profile");
		        System.out.println("2. Edit Profile");
		        System.out.println("3. Logout");
		        int choice = scan.nextInt();
		        switch (choice) {
		            case 1:
		                System.out.println("Email: " + user.getEmail());
		                System.out.println("Name: " + user.getName());
		                System.out.println("Role: " + user.getRole());
		                break;
		            case 2:
		                System.out.println("Enter New Name:");
		                user.setName(scan.next());
		                System.out.println("Enter New Password:");
		                user.setPass(scan.next());
		                System.out.println("Profile Updated!");
		                break;
		            case 3:
		                currentUser = null;
		                return;
		        }
		    }
		}
		private static void addPayment(Users collector) {
		    System.out.println("Enter Student Email:");
		    String studentEmail = scan.next();
		    System.out.println("Enter Fine Type:");
		    String fineType = scan.next();
		    System.out.println("Enter Amount:");
		    double amount = scan.nextDouble();
		    System.out.println("Enter Date:");
		    String date = scan.next();
		    allPayments.add(new Payment(studentEmail, fineType, amount, date, collector.getEmail()));
		    System.out.println("Payment Added Successfully!");
		}

		private static void updatePayment() {
		    System.out.println("Enter Payment ID:");
		    int id = scan.nextInt();

		    for (Payment p : allPayments) {
		        if (p.getPaymentId() == id) {
		            System.out.println("Enter New Amount:");
		            p.setAmount(scan.nextDouble());
		            System.out.println("Updated Successfully");
		            return;
		        }
		    }
		    System.out.println("Payment Not Found!!");
		}

		private static void deletePayment() {
		    System.out.println("Enter Payment ID:");
		    int id = scan.nextInt();

		    Iterator<Payment> iterator = allPayments.iterator();
		    while (iterator.hasNext()) {
		        if (iterator.next().getPaymentId() == id) {
		            iterator.remove();
		            System.out.println("Deleted Successfully!");
		            return;
		        }
		    }
		    System.out.println("Payment Not Found!");
		}
		
}
