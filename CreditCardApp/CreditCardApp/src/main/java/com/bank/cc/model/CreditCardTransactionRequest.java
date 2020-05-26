package com.bank.cc.model;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class CreditCardTransactionRequest {

	@NotNull
	private long requestTransactonId;
	@NotNull
	private CreditCardOperation operation;
	@NotNull
	private long cardNumber;
	@NotNull
	@Range(min = 1, max = 12)
	private int expiryMonth;
	@NotNull
	// can extract to constants,used
	@Range(min = 2012, max = 2030)
	private int expiryYear;
	@NotNull
	@Range(message = "Invalid CVV", min = 100, max = 999)
	private int cvv;
	@NotNull
	private LocalDateTime transactionTimeStamp;
	@NotNull
	@DecimalMin("0.1")
	private Double amount;
}
