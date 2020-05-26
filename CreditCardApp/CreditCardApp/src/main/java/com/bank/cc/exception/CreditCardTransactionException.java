package com.bank.cc.exception;

public class CreditCardTransactionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6196034222562091565L;

	public CreditCardTransactionException(String msg) {
		super(msg);
	}
}
