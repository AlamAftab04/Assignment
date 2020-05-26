package com.bank.cc.model;

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
public class CreditCard {

	private long cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvv;
	private CreditCategory creditCategory;
	private Balance balance;
	private CardStatus status;

}
