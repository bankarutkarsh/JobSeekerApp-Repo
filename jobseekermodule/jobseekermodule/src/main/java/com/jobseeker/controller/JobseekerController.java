package com.jobseeker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobseeker.beans.ApiResponse;
import com.jobseeker.beans.JobSeekerBean;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.exception.JobSeekerIdNotFoundException;
import com.jobseeker.service.JobseekerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobseeker")
public class JobseekerController {

	@Autowired
	private JobseekerService jobseekerService;

	Logger logger = LoggerFactory.getLogger(JobseekerController.class);

	@PostMapping
	public ResponseEntity<ApiResponse> addJobseeker(@Valid @RequestBody JobSeeker jobseeker) {
		logger.info("----------------------------------------------");
		logger.info("Add job seeker controller mapping started");
		jobseekerService.addJobseeker(jobseeker);
		ApiResponse response = new ApiResponse("Jobseeker profile added successfully.", 200);
		logger.info("----------------------------------------------");
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PatchMapping("/update/{jobseekerId}")
	public ResponseEntity<ApiResponse> updateJobseekerProfile(@Valid @RequestBody JobSeekerBean jobseeker,
			@PathVariable Long jobseekerId) throws JobSeekerIdNotFoundException {
		logger.info("----------------------------------------------");
		logger.info("Update job seeker controller mapping started");
		jobseekerService.updateJobseekerProfile(jobseeker, jobseekerId);
		ApiResponse response = new ApiResponse("Jobseeker profile updated successfully.", 200);
		logger.info("----------------------------------------------");
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
