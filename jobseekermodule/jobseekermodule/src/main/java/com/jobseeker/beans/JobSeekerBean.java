package com.jobseeker.beans;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerBean {
	private Long jobseekerId; 
	@Email(message = "Enter a valid email address")
	private String email;
	@Pattern(regexp = "^[0-9]{10}$", message = "Enter valid Phone number exactly 10 digits ,e.g - 9037323325")
	@Size(max = 10)
	private String phoneNumber;
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "City name must contain only alphabets")
	@Size(max = 20, message = "Length should be 0-20 characters")
	private String addressCity;
	private String education;
	@PositiveOrZero(message = "Can be positive or zero")
	private Integer experience;
	@PositiveOrZero(message = "Can be positive or zero")
	private Long currentSalary;
	@Positive(message = "must be a positive value")
	private Long expectedSalary;
	
	private String skills;
	
}
