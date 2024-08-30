package com.jobseeker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobseeker.beans.JobSeekerBean;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.exception.JobSeekerIdNotFoundException;
import com.jobseeker.repository.JobseekerRepository;

@Service
public class JobseekerService {

	@Autowired
	private JobseekerRepository jobseekerRepository;
	
	Logger logger = LoggerFactory.getLogger(JobseekerService.class);
	
	// Service layer for creating a new jobSeeker profile
	public void addJobseeker(JobSeeker jobseeker) {
		jobseekerRepository.save(jobseeker);
		logger.info("Jobseeker Profile added successfully. Profile Details = {}"+jobseeker);
	}

	// Service layer for updating a existing jobSeeker profile
	public void updateJobseekerProfile(JobSeekerBean jobseeker) throws JobSeekerIdNotFoundException {		
		JobSeeker profile = jobseekerRepository.findById(jobseeker.getJobseekerId()).orElse(null);
		if(profile==null) {
			logger.error("No jobseeker profile found for jobseekerId : "+jobseeker.getJobseekerId());
			throw new JobSeekerIdNotFoundException("No jobseeker profile found for jobseekerId : "+jobseeker.getJobseekerId());
		}
		
		if(jobseeker.getEmail() != null) {
			profile.setEmail(jobseeker.getEmail());
		}
		if(jobseeker.getPhoneNumber() != null) {
			profile.setPhoneNumber(jobseeker.getPhoneNumber());
		}
		if(jobseeker.getAddressCity() != null) {
			profile.setAddressCity(jobseeker.getAddressCity());
		}
		if(jobseeker.getEducation() != null) {
			profile.setEducation(jobseeker.getEducation());
		}
		if(jobseeker.getExperience() != null) {
			profile.setExperience(jobseeker.getExperience());
		}
		if(jobseeker.getCurrentSalary() != null) {
			profile.setCurrentSalary(jobseeker.getCurrentSalary());
		}
		if(jobseeker.getExpectedSalary() != null) {
			profile.setExpectedSalary(jobseeker.getExpectedSalary());
		}
		if(jobseeker.getSkills() != null) {
			profile.setSkills(jobseeker.getSkills());
		}
		
		jobseekerRepository.save(profile);
		logger.info("Job seeker profile updated successfully. Profile Details = {}" + profile);
	}
	
	
	
}
