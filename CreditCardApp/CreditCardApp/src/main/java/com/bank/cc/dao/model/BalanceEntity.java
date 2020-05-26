package com.bank.cc.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "balance")
public class BalanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Double cardLimit;
	private Double cardLimitAvailable;
	private Double outstandingBillAmount;
	private Double unbilledAmount;
	private Double cashLimit;
	private Double cashLimitAvailable;
	private Double cashWithdrawn;
	@OneToOne(mappedBy = "balance")
	private CreditCardEntity card;
}
