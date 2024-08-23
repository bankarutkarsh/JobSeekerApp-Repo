package com.jobseeker.beans;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobBean {

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
	private LocalDate postedDate;
	private LocalDate applicationDeadline;
	private String contactEmail;
	
}
