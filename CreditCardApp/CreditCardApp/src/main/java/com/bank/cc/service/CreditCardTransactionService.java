package com.bank.cc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.cc.builder.CreditCardTransactionBuilder;
import com.bank.cc.dao.model.BalanceEntity;
import com.bank.cc.dao.model.CreditCardEntity;
import com.bank.cc.exception.CreditCardTransactionException;
import com.bank.cc.jpa.repository.CreditCardRepo;
import com.bank.cc.jpa.repository.CreditCardTransactionRepo;
import com.bank.cc.model.CreditCard;
import com.bank.cc.model.CreditCardOperation;
import com.bank.cc.model.CreditCardTransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CreditCardTransactionService {

	@Autowired
	private CreditCardRepo creditCardRepo;

	@Autowired
	private CreditCardTransactionRepo creditCardTransactionRepo;

	@Autowired
	private CreditCardTransactionBuilder creditCardTransactionBuilder;
	
	@Autowired
	private ObjectMapper jsonMapper;

	public CreditCard processTransaction(CreditCardTransactionRequest request) {
		List<CreditCardEntity> creditCardList = creditCardRepo.findByCardNumberAndExpiryMonthAndExpiryYear(
				request.getCardNumber(), request.getExpiryMonth(), request.getExpiryYear());
		if (creditCardList.isEmpty()) {
			log.error("Invalid credit card details found:{}", request.getRequestTransactonId());
			throw new CreditCardTransactionException("Invaid Credit Card");
		} else if (request.getOperation().compareTo(CreditCardOperation.TRANSACT) == 0) {
			CreditCardEntity processTrasact = processTrasact(request, creditCardList.get(0));
			return creditCardTransactionBuilder.buildCreditCardFromCreditCardEntity(processTrasact);
		} else if (request.getOperation().compareTo(CreditCardOperation.WITHDRAW) == 0) {
			CreditCardEntity processWithdraw = processWithdraw(request, creditCardList.get(0));
			return creditCardTransactionBuilder.buildCreditCardFromCreditCardEntity(processWithdraw);
		} else {
			log.error("Invalid operation:{}", request.getRequestTransactonId());
			throw new CreditCardTransactionException("Invalid Operation");
		}
	}

	private CreditCardEntity processTrasact(CreditCardTransactionRequest request, CreditCardEntity entity) {

		if (entity.getBalance().getCardLimitAvailable() >= request.getAmount()) {
			BalanceEntity bal = entity.getBalance();
			bal.setCardLimitAvailable(bal.getCardLimitAvailable() - request.getAmount());
			bal.setUnbilledAmount(bal.getUnbilledAmount() + request.getAmount());
			return process(entity, request);
		} else {
			log.error("Transaction amount exceeded available credit limit:{}", request.getRequestTransactonId());
			throw new CreditCardTransactionException("Transaction amount exceeded available credit limit");
		}

	}

	private CreditCardEntity processWithdraw(CreditCardTransactionRequest request, CreditCardEntity entity) {
		if (entity.getBalance().getCashLimitAvailable() >= request.getAmount()) {
			BalanceEntity bal = entity.getBalance();
			bal.setCashLimitAvailable(bal.getCashLimitAvailable() - request.getAmount());
			bal.setCashWithdrawn(bal.getCashWithdrawn() != null ? (bal.getCashWithdrawn() + request.getAmount())
					: request.getAmount());
			return process(entity, request);
		} else {
			log.error("Transaction amount exceeded available cash withdrawal limit:{}",
					request.getRequestTransactonId());
			throw new CreditCardTransactionException("Transaction amount exceeded available cash withdrawal limit");
		}
	}

	private CreditCardEntity process(CreditCardEntity entity, CreditCardTransactionRequest request) {
		
		creditCardTransactionRepo
				.save(creditCardTransactionBuilder.buildCreditCardTransactionFromRequest(request, entity));
		return creditCardRepo.save(entity);
	}

}
