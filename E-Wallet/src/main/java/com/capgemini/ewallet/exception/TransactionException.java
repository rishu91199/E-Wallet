package com.capgemini.ewallet.exception;

public class TransactionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
     
	public TransactionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TransactionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
    
}