package com.job.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.beans.ApiResponse;
import com.job.entity.Job;
import com.job.exception.JobNotFoundException;
import com.job.service.JobService;

import jakarta.validation.Valid;

@RestController
public class JobController {

	// Creating a JobService Instance to call service methods
	@Autowired
	private JobService jobService;

	Logger logger = LoggerFactory.getLogger(JobController.class);
	
	// Controller mapping for finding job details using jobId.
	@GetMapping("/job/{jobId}")
	public ResponseEntity<Job> getJobDetailsByJobId(@PathVariable("jobId") Long jobId) throws JobNotFoundException {
		logger.info("----------------------------------------------");
		logger.info("Find job by id controller mapping started");
		Job job = jobService.getJobDetailsByJobId(jobId);
		logger.info("----------------------------------------------");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	// Controller mapping for finding jobs list according to different parameters.
	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> getJobListUsingCriteria(
			@RequestParam(value = "workingArea", required = false) String workingArea,
			@RequestParam(value = "workingExperience", required = false) Integer workingExperience,
			@RequestParam(value = "jobLocation", required = false) String jobLocation,
			@RequestParam(value = "requiredSkill", required = false) String requiredSkill) throws JobNotFoundException {
		logger.info("----------------------------------------------");
		logger.info("Find job using criteria controller mapping started");
		List<Job> jobs = jobService.getJobListUsingCriteria(workingArea, workingExperience, jobLocation,requiredSkill);	
		logger.info("----------------------------------------------");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	// Controller mapping for creating a new job entry.
	@PostMapping("/job")
	public ResponseEntity<ApiResponse> addJob(@Valid @RequestBody Job job) {
		logger.info("----------------------------------------------");
		logger.info("Add new job controller mapping started");
		jobService.addJob(job);
		ApiResponse response = new ApiResponse("Job added successfully", 200);
		logger.info("----------------------------------------------");
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
