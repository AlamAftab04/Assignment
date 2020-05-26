package com.bank.cc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class User {
	private long userId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private List<CreditCard> cards;
	private String ssn;
}
