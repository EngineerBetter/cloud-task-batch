package com.fil.service.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class TimeStampJobParameter implements JobParametersIncrementer{

	@Override
	public JobParameters getNext(JobParameters parameters) {
		JobParameters jobParameters =
				  new JobParametersBuilder()
				  .addLong("time",System.currentTimeMillis()).toJobParameters();

		return jobParameters;
	}

}
