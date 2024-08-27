package com.jobseeker.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobseekerId;
	@NotNull(message = "Value cannot be null")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only aplhabets allowed with spaces")
	@Size(max = 20, message = "Length should be 0-20 characters")
	private String name;
	@NotNull(message = "Value cannot be null")
	@Email(message = "Enter a valid email address")
	@Column(unique = true)
	private String email;
	@NotNull(message = "Value cannot be null")
	@Size(min = 7, message = "Must be greater than 7 characters.")
	@Column(unique = true)
	private String password;
	@NotNull(message = "Value cannot be null")
	@Pattern(regexp = "^[0-9]{10}$", message = "Enter valid Phone number exactly 10 digits ,e.g - 9037323325")
	@Size(max = 10)
	@Column(unique = true)
	private String phoneNumber;
	@NotNull(message = "Value cannot be null")
	@Past(message = "Enter correct Date of birth")
	private LocalDate dateOfBirth;
	@NotNull(message = "Value cannot be null")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "City name must contain only alphabets")
	@Size(max = 20, message = "Length should be 0-20 characters")
	private String addressCity;
	@NotNull(message = "Value cannot be null")
	private String education;
	@NotNull(message = "Value cannot be null")
	@PositiveOrZero(message = "Cannot be a negative value")
	private Integer experience;
	@NotNull(message = "Value cannot be null")
	@PositiveOrZero(message = "Cannot be a negative value")
	private Long currentSalary;
	@NotNull(message = "Value cannot be null")
	@Positive(message = "Cannot be a negative value or zero")
	private Long expectedSalary;
	@NotNull(message = "Value cannot be null")
	private String skills;

	@OneToMany(mappedBy = "jobseeker")
	private List<JobApplications> appliedJobs;

	@Override
	public String toString() {
		return "JobSeeker [jobseekerId=" + jobseekerId + ", name=" + name + ", dateOfBirth=" + dateOfBirth
				+ ", addressCity=" + addressCity + ", education=" + education + ", experience=" + experience
				+ ", expectedSalary=" + expectedSalary + ", skills=" + skills + "]";
	}
}
