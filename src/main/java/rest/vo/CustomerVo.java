package rest.vo;

import javax.persistence.Column;

import core.entities.Account;
import core.entities.Address;

public class CustomerVo {
	
	private String userName;
	private String password;
	private Address address;
	private String stripeToken;
	private String fullName;
	private String phone;
	
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CustomerVo() {
		// TODO Auto-generated constructor stub
	}
	
	public Account convert(){
		Account account = new Account();
		account.setUserName(this.userName);
		account.setPassword(this.password);
		account.setStripeId(this.stripeToken);
		account.setAddress(this.address);
		account.setFullName(this.fullName);
		account.setPhone(this.phone);
		return account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getStripeToken() {
		return stripeToken;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
}
