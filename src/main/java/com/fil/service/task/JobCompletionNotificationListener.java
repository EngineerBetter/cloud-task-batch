package com.fil.service.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fil.service.model.Customer;
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			List<Customer> results = jdbcTemplate.query("SELECT * FROM CUSTOMER", new RowMapper<Customer>() {
				@Override
				public Customer mapRow(ResultSet rs, int row) throws SQLException {
					Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getString(4));
					customer.setId(rs.getString(1));
					return customer;
				}
			});
			log.info("======================= Customer Details ========================");
			for (Customer customer : results) {
				log.info("Found " + customer + " in the database.");
			}

		}
	}
}