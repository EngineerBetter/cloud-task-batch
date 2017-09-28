package com.fil.service.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.fil.service.task.JobCompletionNotificationListener;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@EnableBatchProcessing
@EnableTask  
@Import({EmbeddedDataSourceConfiguration.class,JobCompletionNotificationListener.class})
@ContextConfiguration(classes=BatchJobConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class ShortLivedMsApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//@Test
	public void testLaunchJob() throws Exception {

		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

	}

	//@Test
	public void testLaunchStep() {

		JobExecution jobExecution = jobLauncherTestUtils.launchStep("readerStep");
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}

}
