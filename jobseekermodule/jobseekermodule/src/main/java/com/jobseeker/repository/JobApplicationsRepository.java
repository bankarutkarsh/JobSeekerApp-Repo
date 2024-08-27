package com.jobseeker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobseeker.entity.JobApplications;

public interface JobApplicationsRepository extends JpaRepository<JobApplications, Long> {

	List<JobApplications> findAllByJobseekerJobseekerId(Long jobseekerId);
	
}
