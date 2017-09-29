package com.fil.service.task;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.fil.service.model.Customer;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
	private final String crashOnAccountNo;

	public CustomerItemProcessor(@Autowired String crashOnAccountNo) {
		this.crashOnAccountNo = crashOnAccountNo;
	}

	@Override
	public Customer process(Customer customer) throws Exception {
		final String firstName = customer.getFirstName().toUpperCase();
		final String lastName = customer.getLastName().toLowerCase();
		final String accountNo = customer.getAccountNo();

		if(accountNo.equals(crashOnAccountNo)) {
			throw new RuntimeException("Deliberately crashing on account number: "+accountNo);
		}

		final Customer c = new Customer(firstName, lastName, accountNo);
		return c;
	}

}
