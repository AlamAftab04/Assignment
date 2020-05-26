package com.bank.cc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
public class ApplicationRequest {

	@NotNull
	private long applicationId;

	@NotNull
	@Size(min = 5, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 5, max = 50)
	private String lastName;

	@NotNull
	private long phoneNumber;

	@NotNull
	@Pattern(message = "Invalid email", regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	@NotNull
	@Pattern(regexp = "[A-Z0-9]{10}", message="invalid SSN")
	private String ssn;

}
