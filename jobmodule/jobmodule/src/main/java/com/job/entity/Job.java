package com.job.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	private String jobTitle;
	private String description;
	private String companyName;
	private String workingArea;        
	private Integer workingExperience; // In Years
	private String jobLocation;        // City name
	private String jobType;
	private Long salary;
	private String requiredSkill;
	@CreationTimestamp
	private LocalDate postedDate;
	private LocalDate applicationDeadline;
	private String contactEmail;
}
