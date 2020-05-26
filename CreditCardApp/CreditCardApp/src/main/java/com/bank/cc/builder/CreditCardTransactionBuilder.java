package com.bank.cc.builder;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.bank.cc.dao.model.BalanceEntity;
import com.bank.cc.dao.model.CreditCardEntity;
import com.bank.cc.dao.model.CreditCardTransactionEntity;
import com.bank.cc.model.Balance;
import com.bank.cc.model.CreditCard;
import com.bank.cc.model.CreditCardTransactionRequest;

@Component
public class CreditCardTransactionBuilder {

	public CreditCardTransactionEntity buildCreditCardTransactionFromRequest(CreditCardTransactionRequest request,
			CreditCardEntity entity) {

		return CreditCardTransactionEntity.builder().requestTransactonId(request.getRequestTransactonId())
				.transactionAmount(request.getAmount()).transactionType(request.getOperation().toString())
				.transactionDate(Timestamp.valueOf(request.getTransactionTimeStamp()))
				.cardNumber(entity.getCardNumber()).build();
	}

	public CreditCard buildCreditCardFromCreditCardEntity(CreditCardEntity entity) {
		CreditCard creditCard = CreditCard.builder().cardNumber(entity.getCardNumber()).build();
		creditCard.setBalance(buildBalanceFromBalanceEntity(entity.getBalance()));
		return creditCard;
	}

	private Balance buildBalanceFromBalanceEntity(BalanceEntity balance) {
		return Balance.builder().cardLimitAvailable(balance.getCardLimitAvailable()).cardLimit(balance.getCardLimit())
				.cashLimit(balance.getCashLimit()).cashLimitAvailable(balance.getCashLimit())
				.unbilledAmount(balance.getUnbilledAmount()).build();
	}
}
