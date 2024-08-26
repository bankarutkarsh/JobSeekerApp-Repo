package com.jobseeker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobseeker.beans.ApiResponse;
import com.jobseeker.entity.JobApplications;
import com.jobseeker.exception.JobDeadLinePassedException;
import com.jobseeker.exception.JobNotAppliedException;
import com.jobseeker.exception.JobNotFoundException;
import com.jobseeker.exception.JobSeekerIdNotFoundException;
import com.jobseeker.service.JobApplicationsService;

@RestController
public class JobApplicationsController {

	@Autowired
	private JobApplicationsService jobApplicationsService;
	
	Logger logger = LoggerFactory.getLogger(JobApplicationsController.class);

	@PostMapping("/apply/{jobId}/{jobseekerId}")
	public ResponseEntity<ApiResponse> applyToJob(@PathVariable Long jobId, @PathVariable Long jobseekerId)
			throws JobSeekerIdNotFoundException, JobNotFoundException, JobDeadLinePassedException,
			JobNotAppliedException {
		
		logger.info("----------------------------------------------");
		logger.info("Apply for job using jobId & jobseekerId controller mapping called");
		
		jobApplicationsService.applyToJob(jobId, jobseekerId);
		ApiResponse response = new ApiResponse("Successfully applied for jobId : " + jobId, 200);
		logger.info("----------------------------------------------");
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/applications/{jobseekerId}")
	public ResponseEntity<List<JobApplications>> getAllAppliedJobsById(@PathVariable Long jobseekerId)
			throws JobSeekerIdNotFoundException, JobNotFoundException {
		logger.info("----------------------------------------------");
		logger.info("See all job applications using jobseekerId controller mapping called");
		List<JobApplications> applications = jobApplicationsService.getAllAppliedJobsById(jobseekerId);
		logger.info("----------------------------------------------");
		return new ResponseEntity<List<JobApplications>>(applications, HttpStatus.OK);
	}

}
