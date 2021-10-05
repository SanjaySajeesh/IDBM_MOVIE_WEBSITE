package app.model;



public class Account {
	// Valid account types:
	public static final String ADMIN = "ADMIN";
	public static final String PCO = "PCO";
	public static final String CRITIC = "CRITIC";
	public static final String USER = "USER";
	
    private String firstName;
    private String lastName;
    private String address;
    private String type;
    private String username;

    /**
     * Only stores hashed passwords.
     */
    private String password;

    private String country;
    private String gender;
    private String email;
    
    private String public_name;	
    private String phone_num;
    private String postal_code; 
    
    public Account(String type, String un, String p, String fn, String ln, String a, String c, String g, String email) {
        username = un;
		this.type = type;
        password = p;
        this.firstName = fn;
    	this.lastName = ln;
    	this.address = a;
    	this.country = c;
    	this.gender = g;
    	this.email = email;
    	this.public_name = un;
    	
    }
    //constructor for PCO and Critics users
    public Account(String type, String un, String p, String fn, String ln, String a, String c, String g, String email, String phoneNum, String organisation, String postal_code) {
    	this(type, un, p, fn, ln, a, c, g, email);
    	public_name = organisation;
    	this.phone_num = phoneNum;
        this.postal_code = postal_code;
    }
    public String getPostalCode() {
    	return postal_code;
    }
    public String getPhoneNum() {
    	return phone_num;
    }
    public String getPublicName() {
    	return public_name;
    }

    public String getFirstName() {
 		return firstName;
 	}


 	public String getLastName() {
 		return lastName;
 	}


 	public String getAddress() {
 		return address;
 	}


 	public String getCountry() {
 		return country;
 	}


 	public String getGender() {
 		return gender;
 	}


 	public String getEmail() {
 		return email;
 	}


 	public void updateDetails() {
         // TODO
     }

     public String getUsername() {
         return username;
     }


    public String getPassword() {
        return password;
    }
    
    public String getType() {
    	return type;
    }

}
