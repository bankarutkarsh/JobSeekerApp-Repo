package com.job.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.entity.Job;
import com.job.exception.JobNotFoundException;
import com.job.repository.JobRepository;

@Service
public class JobService {

	// Creating a JobRepository Instance to access all features of Spring Data Jpa
	@Autowired
	private JobRepository jobRepository;

	Logger logger = LoggerFactory.getLogger(JobService.class);

	// Service Method for finding job using jobId
	/**
	 * @param jobId
	 * @return
	 * @throws JobNotFoundException
	 */
	public Job getJobDetailsByJobId(Long jobId) throws JobNotFoundException {
		Job job = jobRepository.findById(jobId).orElse(null);
		if (job == null) {
			logger.error("Job with id : " + jobId + "not found.");
			throw new JobNotFoundException("No Job found for jobId : " + jobId);
		}
		logger.info("Job details = {}",job);
		return job;
	}

	// Service Method for finding job using different criteria's
	public List<Job> getJobListUsingCriteria(String workingArea, Integer workingExperience, String jobLocation,
			String requiredSkill) throws JobNotFoundException {
		List<Job> jobs = jobRepository.findJobsByCriteria(workingArea, workingExperience, jobLocation, requiredSkill);
		if (jobs.isEmpty()) {
			logger.error("No Job found for applied criteria");
			throw new JobNotFoundException("No Job found for applied criteria");
		}
		// filter according to job skills
//		jobs.stream().filter(x->Arrays.asList(x.getRequiredSkill().split(",")).collect(Collectors.toList()).contains(requiredSkill));
		logger.info("Matching jobs = {}", jobs);
		return jobs;
	}

	// Service Method for adding new job
	public void addJob(Job job) {
		jobRepository.save(job);
		logger.info("Job added successfully");
	}

}
