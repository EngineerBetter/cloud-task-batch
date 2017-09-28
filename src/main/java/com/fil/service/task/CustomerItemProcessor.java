package com.fil.service.task;

import org.springframework.batch.item.ItemProcessor;

import com.fil.service.model.Customer;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

	@Override
	public Customer process(Customer customer) throws Exception {
		final String firstName = customer.getFirstName().toUpperCase();
		final String lastName = customer.getLastName().toLowerCase();
		final String accountNo = customer.getAccountNo();
		final Customer c = new Customer(firstName, lastName, accountNo);
		return c;
	}

}
