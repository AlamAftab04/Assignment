package com.bank.cc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditCategory {
	HIGH("HighCreditLine"),

	LOW("LowCreditLine"),

	MEDIUM("MediumCreditLine");

	private String value;

	CreditCategory(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CreditCategory fromValue(String text) {
		for (CreditCategory b : CreditCategory.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}