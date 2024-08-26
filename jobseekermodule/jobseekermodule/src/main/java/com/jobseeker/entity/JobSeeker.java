package com.jobseeker.entity;

import java.time.LocalDate;
import java.util.List;

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
@ToString
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobseekerId;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Only aplhabets allowed with spaces")
	@Size(max = 20, message = "Length should be 0-20 characters")
	private String name;
	@NotNull
	@Email(message = "Enter a valid email address")
	private String email;
	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Enter valid Phone number exactly 10 digits ,e.g - 9037323325")
	@Size(max = 10)
	private String phoneNumber;
	@NotNull
	@Past(message = "Enter correct Date of birth")
	private LocalDate dateOfBirth;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "City name must contain only alphabets")
	@Size(max = 20, message = "Length should be 0-20 characters")
	private String addressCity;
	@NotNull
	private String education;
	@NotNull
	@PositiveOrZero
	private Integer experience;
	@NotNull
	@PositiveOrZero
	private Long currentSalary;
	@NotNull
	@Positive
	private Long expectedSalary;
	@NotNull
	private String skills;

	@OneToMany(mappedBy = "jobseeker")
	private List<JobApplications> appliedJobs;
}
