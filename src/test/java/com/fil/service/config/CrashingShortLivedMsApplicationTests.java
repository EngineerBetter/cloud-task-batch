package com.fil.service.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fil.service.config.CrashingShortLivedMsApplicationTests.TestConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={TestConfig.class, BatchJobConfiguration.class})
public class CrashingShortLivedMsApplicationTests {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testLaunchJob() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
	}

	@PropertySource("application-crashing.properties")
	public static class TestConfig {
		@Bean
		JobLauncherTestUtils testUtils() {
			return new JobLauncherTestUtils();
		}
	}
}
