package com.bank.cc.dao.model;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.Data;


@Data
@Validated

public class TransactionHistory {
	
	private List<CreditCardTransactionEntity> transactons;

}
