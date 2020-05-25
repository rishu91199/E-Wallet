package com.capgemini.ewallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ewallet.entities.WalletUser;
import com.capgemini.ewallet.service.*;
@CrossOrigin(origins="*")
@RestController
public class OnlineWalletController {

	@Autowired
	private OnlineWalletService onlineWalletService;

	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
    
	@RequestMapping("/")
	public String check() {
		return "WORKING";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> regsiterUser(@RequestBody WalletUser user) {
		String loginName=onlineWalletService.registerUser(user);
		return new ResponseEntity<String>(loginName, HttpStatus.OK);

	}
	
	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<Double> addMoney(@PathVariable("userId") Integer userId, Double amount) {
		Double balance=onlineWalletService.addMoney(userId, amount);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}
	

	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = onlineWalletService.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}
	

	@GetMapping("/transactmoney/{userId}")
	public ResponseEntity<String> transactMoney(@PathVariable("userId") Integer userId, Double amount,
			String email) {
		onlineWalletService.transactMoney(userId, email, amount);
		return new ResponseEntity<String>("Transaction Completed", HttpStatus.OK);
	}
	

	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password) {
		Integer userId = onlineWalletService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
	
    
	
}
