package fineMainagement;

public class Users {
    private String email;
    private String name;
    private String pass;
    private String role;

    public Users(String email, String name, String pass, String role) {
        this.email = email.toLowerCase().trim();
        this.name = name;
        this.pass = pass.trim();
        this.role = role.toUpperCase().trim();
    }

    public String getEmail(){ 
    	return email;
    	}
    public String getName(){
    	return name; 
    	}
    public String getPass(){ 
    	return pass;
    	}
    public String getRole(){
    	return role; 
    	}
    public void setName(String name){ 
    	this.name = name;
    	}
    public void setPass(String pass){ 
    	this.pass = pass;
    	}
}