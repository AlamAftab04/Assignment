package com.bank.cc.dao.model;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditCardDetailsEntity {
	
	
	private CreditCardEntity card;
	private BalanceEntity balance;
	private TransactionHistory history;
}

