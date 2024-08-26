package com.job.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
	@NotNull
	private String jobTitle;
	@NotNull
	private String description;
	@NotNull
	private String companyName;
	@NotNull
	private String workingArea;   
	@NotNull
	@PositiveOrZero
	private Integer workingExperience; // In Years
	@NotNull
	private String jobLocation;        // City name
	@NotNull
	private String jobType;
	@NotNull
	@Positive
	private Long salary;
	@NotNull
	private String requiredSkill;
	@CreationTimestamp
	private LocalDate postedDate;
	@NotNull
	@Future
	private LocalDate applicationDeadline;
	@NotNull
	@Email
	private String contactEmail;
}
