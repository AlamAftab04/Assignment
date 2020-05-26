package com.bank.cc.dao.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bank.cc.model.CardStatus;
import com.bank.cc.model.CreditCategory;

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
@Entity(name = "creditcard")
public class CreditCardEntity {

	@Id
	private long cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvv;
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	private String creditCategory;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "balanceId", referencedColumnName = "id")
	private BalanceEntity balance;
	private String status;
}
