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
	@NotNull(message = "Value cannot be null")
	private String jobTitle;
	@NotNull(message = "Value cannot be null")
	private String description;
	@NotNull(message = "Value cannot be null")
	private String companyName;
	@NotNull(message = "Value cannot be null")
	private String workingArea;   
	@NotNull(message = "Value cannot be null")
	@PositiveOrZero(message = "Cannot be a negative value")
	private Integer workingExperience; // In Years
	@NotNull(message = "Value cannot be null")
	private String jobLocation;        // City name
	@NotNull(message = "Value cannot be null")
	private String jobType;
	@NotNull(message = "Value cannot be null")
	@Positive(message = "Cannot be a negative value or Zero")
	private Long salary;
	@NotNull(message = "Value cannot be null")
	private String requiredSkill;
	@CreationTimestamp
	private LocalDate postedDate;
	@NotNull(message = "Value cannot be null")
	@Future(message = "Must be a future Date")
	private LocalDate applicationDeadline;
	@NotNull(message = "Value cannot be null")
	@Email(message = "Enter a correct email id")
	private String contactEmail;
}
