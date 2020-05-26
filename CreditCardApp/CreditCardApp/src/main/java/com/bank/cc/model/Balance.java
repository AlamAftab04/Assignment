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
public class Balance {

	private Double cardLimit;
	private Double cardLimitAvailable;
	private Double outstandingBillAmount;
	private Double unbilledAmount;
	private Double cashLimit;
	private Double cashLimitAvailable;
	private Double cashWithdrawn;
}
