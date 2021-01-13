package com.app.util;

public class Validation {
	
	public static boolean isValidUserName(String userName) {
		boolean b = false;
		if(userName.matches("^(?=\\S+$)[a-zA-Z0-9]{6,20}$")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidPassword(String password) {
		boolean b = false;
		if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)[a-zA-Z0-9]{8,20}$")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidName(String name) {
		boolean b = false;
		if(name.matches("[a-zA-Z0-9 ]{2,20}")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidAddress(String address) {
		boolean b = false;
		if(address.matches("[a-zA-Z0-9 ]{2,10} [a-zA-Z0-9 ]{2,40}, [a-zA-Z ]{2,10}, [a-zA-Z ]{4,14}, [0-9]{5}")) {
			b = true;
		}
		return b;
	}

	public static boolean isValidPhone(String phone) {
		boolean b = false;
		if(phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidEmail(String email) {
		boolean b = false;
		if(email.matches("[0-9a-zA-Z]{1,30}@[0-9a-zA-Z]{1,20}.[0-9a-zA-Z]{1,20}")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidCreditScore(int creditScore) {
		boolean b = false;
		if(creditScore >= 299 && creditScore <= 850) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidSocialSecurityNumber(String socialSecurity) {
		boolean b = false;
		if(socialSecurity.matches("[0-9]{9}")) {
			b = true;
		}
		return b;
	}
	
	public static boolean isValidDob(String dobEntered) {
		boolean b = false;
		
		if(dobEntered.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			b = true;
		}

		return b;
	}
	
	public static boolean isValidTransactionAmount(double d) {
		boolean b = false;
		
		if(d>=0.01d && d<=20000.0d) {
			b = true;
		}

		return b;
	}
}
