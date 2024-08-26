package com.jobseeker.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobseeker.beans.JobBean;
import com.jobseeker.entity.JobApplications;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.exception.JobDeadLinePassedException;
import com.jobseeker.exception.JobNotAppliedException;
import com.jobseeker.exception.JobNotFoundException;
import com.jobseeker.exception.JobSeekerIdNotFoundException;
import com.jobseeker.repository.JobApplicationsRepository;
import com.jobseeker.repository.JobseekerRepository;

import jakarta.transaction.Transactional;

@Service
public class JobApplicationsService {

	@Autowired
	private JobApplicationsRepository jobApplicationsRepository;

	@Autowired
	private JobseekerRepository jobseekerRepository;

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(JobApplicationsService.class);
	
	@Transactional
	public void applyToJob(Long jobId, Long jobseekerId) throws JobSeekerIdNotFoundException, JobNotFoundException,
			JobDeadLinePassedException, JobNotAppliedException {
		JobSeeker jobseeker = jobseekerRepository.findById(jobseekerId).orElse(null);
		if (jobseeker == null) {
			logger.error("No jobseeker profile found for jobseekerId : " + jobseekerId);
			throw new JobSeekerIdNotFoundException("No jobseeker profile found for jobseekerId : " + jobseekerId);
		}
		JobBean job = restTemplate.getForObject("http://localhost:8085/app1/job/" + jobId, JobBean.class);

		if (job == null) {
			logger.error("No job found for jobId : " + jobId);
			throw new JobNotFoundException("No job found for jobId : " + jobId);
		}
		
		if (job.getApplicationDeadline().isBefore(LocalDate.now())) {
			logger.error("Cannot apply as application Deadline is crossed");
			throw new JobDeadLinePassedException("Sorry, cannot apply as application Deadline is crossed");
		}
			
		if (job.getWorkingExperience() > jobseeker.getExperience()) {
			logger.error("Job not applied as experience criteria failed");
			throw new JobNotAppliedException("Experience required for job : " + job.getWorkingExperience()
					+ " years does not match your experience : " + jobseeker.getExperience() + " years.");
		}
			
		for(JobApplications j : jobseeker.getAppliedJobs())
			if(j.getJobId() == jobId) {
				logger.error("You have already applied for this job");
				throw new JobNotAppliedException("You have already applied for this job");
			}
		

		JobApplications jobApplication = new JobApplications();

		jobApplication.setJobId(jobId);
		jobApplication.setJobseeker(jobseeker);

		jobApplicationsRepository.save(jobApplication);
		logger.info("Successfully applied for the jobId : "+jobId);
	}

	public List<JobApplications> getAllAppliedJobsById(Long jobseekerId)
			throws JobSeekerIdNotFoundException, JobNotFoundException {
		JobSeeker jobseeker = jobseekerRepository.findById(jobseekerId).orElse(null);
		if (jobseeker == null) {
			logger.error("No jobseeker profile found for jobseekerId : " + jobseekerId);
			throw new JobSeekerIdNotFoundException("No jobseeker profile found for jobseekerId : " + jobseekerId);
		}
		List<JobApplications> applications = jobApplicationsRepository.findAllByJobseekerJobseekerId(jobseekerId);
		if (applications.isEmpty()) {
			logger.error("You have not applied to any job");
			throw new JobNotFoundException("No jobs applied by jobseeker with id : " + jobseekerId);
		}
		for (JobApplications application : applications) {
			application.setJobseeker(null);
		}
		logger.info("Job Applications={}",applications);
		return applications;
	}

}
