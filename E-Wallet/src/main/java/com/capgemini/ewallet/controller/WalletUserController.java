package com.capgemini.ewallet.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ewallet.dto.Login;
import com.capgemini.ewallet.entity.WalletAccount;
import com.capgemini.ewallet.entity.WalletUser;
import com.capgemini.ewallet.exception.RecordNotFoundException;
import com.capgemini.ewallet.exception.WalletUserException;
import com.capgemini.ewallet.service.WalletUserService;
import com.capgemini.ewallet.service.WalletUserServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class WalletUserController {

	@Autowired
	private WalletUserService walletuserservice;
	
	
	
	

	
	@GetMapping("/viewall")
	public ResponseEntity<List<WalletUser>> getAllUser(){
		List<WalletUser> accountList= walletuserservice.getAllWalletUser();
		return new ResponseEntity<List<WalletUser>>(accountList,HttpStatus.OK);
	}
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
		@PostMapping("/add")
		public ResponseEntity<String> addAccount(@Valid @RequestBody WalletUser walletuser, BindingResult br) throws WalletUserException
		{
			String err="";
			if(br.hasErrors()) {
				List<FieldError> errors= br.getFieldErrors();
				for(FieldError error:errors)
					err +=error.getDefaultMessage() +"<br/>";
				throw new WalletUserException(err);
			}
			try {
				walletuserservice.addAccount(walletuser);
				return new ResponseEntity<String>("New WalletUser added", HttpStatus.OK);
				
			}
			catch(DataIntegrityViolationException ex) {
				throw new WalletUserException("ID already exists");
			}
		}
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/validateUser")
	public ResponseEntity<WalletUser> validateUser(@RequestBody Login userLogin) throws RecordNotFoundException {
		
		WalletUser b=walletuserservice.validateUser(userLogin);	
		return new ResponseEntity<WalletUser>(b, HttpStatus.OK);
	 }
	//return b;

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String>IdNotFoundException(RecordNotFoundException e){
	return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

	}

	
}
