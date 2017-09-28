package com.fil.service.model;

public class Customer {

	private String firstName;

	private String id;

	private String lastName;

	private String accountNo;

	public String getId() {
		return id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public Customer() {
	}

	public Customer(String firstName, String lastName, String accountNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNo = accountNo;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Customer ID : " + this.getId() + " First Name : " + this.firstName + "  Last Name : " + lastName
				+ " Account No : " + this.accountNo;
	}
}