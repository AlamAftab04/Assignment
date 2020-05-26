package com.bank.cc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditCardOperation {

	TRANSACT("Transact"),
	WITHDRAW("Withdraw"),
	CREDIT("Credit");

	private String value;

	CreditCardOperation(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CreditCardOperation fromValue(String text) {
		for (CreditCardOperation b : CreditCardOperation.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
