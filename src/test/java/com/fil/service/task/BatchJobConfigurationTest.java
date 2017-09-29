package com.fil.service.task;

import org.junit.Test;

import com.fil.service.model.Customer;

public class BatchJobConfigurationTest {
	@Test(expected=RuntimeException.class)
	public void processorCrashesOnGivenAccountNo() throws Exception {
		CustomerItemProcessor processor = new CustomerItemProcessor("123456");
		Customer customer = new Customer("Sushant", "Kapoor", "123456");
		processor.process(customer);
	}

	@Test
	public void processorDoesNotCrashByDefault() throws Exception {
		CustomerItemProcessor processor = new CustomerItemProcessor(null);
		Customer customer = new Customer("Sushant", "Kapoor", "123456");
		processor.process(customer);
	}
}
