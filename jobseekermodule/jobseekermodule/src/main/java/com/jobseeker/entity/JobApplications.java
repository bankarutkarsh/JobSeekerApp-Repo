package com.jobseeker.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.jobseeker.beans.JobBean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobApplications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicationId;
	@NotNull
	private Long jobId;
	@NotNull
	private String jobTitle;
	@NotNull
	private String companyName;
	@NotNull  
	private String jobLocation;
	@NotNull
	private String requiredSkill;
	@NotNull
	private String contactEmail;
	
	@CreationTimestamp
	private LocalDate applicationDate;
	@NotNull
	private String status;	
	@ManyToOne
	@JoinColumn(name = "jobseeker")
	private JobSeeker jobseeker; 
}
