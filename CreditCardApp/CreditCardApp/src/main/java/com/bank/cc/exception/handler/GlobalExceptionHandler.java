package com.bank.cc.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.cc.exception.CreditCardApplicationException;
import com.bank.cc.exception.CreditCardTransactionException;
import com.bank.cc.model.ApplicationResponse;
import com.bank.cc.model.CreditCardTransactionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ CreditCardApplicationException.class, Exception.class })
	public ResponseEntity<ApplicationResponse> handle(Exception e) {

		return new ResponseEntity<>(ApplicationResponse.builder().status("Failed").message(e.getMessage())
				.errorType(e.getClass().getName()).build(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ CreditCardTransactionException.class })
	public ResponseEntity<CreditCardTransactionResponse> handle(CreditCardTransactionException e) {

		return new ResponseEntity<>(CreditCardTransactionResponse.builder().status("Failed").message(e.getMessage())
				.errorType(e.getClass().getName()).build(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
