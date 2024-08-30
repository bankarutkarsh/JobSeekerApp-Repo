package com.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.job.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	
//Query to get filtered jobs with criteria - workingArea , jobLocation, workingExperience
	
	@Query("SELECT j FROM Job j WHERE (:workingArea IS NULL OR j.workingArea = :workingArea) "
			+ "AND (:jobLocation IS NULL OR j.jobLocation=:jobLocation)"
			+ " AND (:workingExperience IS NULL OR j.workingExperience <= :workingExperience)")
	List<Job> findJobsByCriteria(String workingArea, Integer workingExperience, String jobLocation);

}
