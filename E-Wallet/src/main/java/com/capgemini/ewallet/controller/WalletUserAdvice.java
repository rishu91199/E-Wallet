package com.capgemini.ewallet.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.ewallet.dto.ErrorInfo;
import com.capgemini.ewallet.exception.TransactionException;

@RestControllerAdvice
public class WalletUserAdvice {
	@ExceptionHandler(value = {TransactionException.class})
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorInfo handleException1(Exception ex) {
		return new ErrorInfo(ex.getMessage());
	}
}
