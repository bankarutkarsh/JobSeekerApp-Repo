package com.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.entity.Job;
import com.job.repository.JobRepository;

@Service
public class JobService {

	// Creating a JobRepository Instance to access all features of Spring Data Jpa
	@Autowired
	private JobRepository jobRepository;

	// Service Method for finding job using jobId
	public Job getJobDetailsByJobId(Long jobId) {
		Job job = jobRepository.findById(jobId).orElse(null);
		// if(job==null) throw Exception.-------------------------------
		return job;
	}
	
	// Service Method for finding job using different criteria's
	public List<Job> getJobListUsingCriteria(String workingArea, Integer workingExperience, String jobLocation, String requiredSkill) {
		List<Job> jobs = jobRepository.findJobsByCriteria(workingArea, workingExperience, jobLocation);
		// if(jobs.isEmpty()) throw Exception.-------------------------------
		
		//filter according to job skills
		
		return jobs;
	}

	// Service Method for adding new job
	public void addJob(Job job) {
		jobRepository.save(job);		
	}
	
	
	
}
