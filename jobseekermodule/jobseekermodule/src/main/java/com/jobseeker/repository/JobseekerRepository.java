package com.jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobseeker.entity.JobSeeker;

@Repository
public interface JobseekerRepository extends JpaRepository<JobSeeker, Long> {

	
	
}
