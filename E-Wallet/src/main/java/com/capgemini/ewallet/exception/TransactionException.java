package com.capgemini.ewallet.exception;

@SuppressWarnings("serial")
public class TransactionException extends RuntimeException {
	
	public TransactionException(String message)
	{
		super(message);
	}

}
