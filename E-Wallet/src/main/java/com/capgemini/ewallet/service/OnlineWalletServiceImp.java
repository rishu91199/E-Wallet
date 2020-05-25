package com.capgemini.ewallet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ewallet.dao.*;
import com.capgemini.ewallet.entities.*;
import com.capgemini.ewallet.entities.WalletAccount.status;
import com.capgemini.ewallet.entities.WalletUser.type;
import com.capgemini.ewallet.exceptions.*;

@Transactional
@Service
public class OnlineWalletServiceImp implements OnlineWalletService {

	public OnlineWalletServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletDao onlineWalletDao;

	

	@Override
	public Integer login(String email, String password) {
		if(!onlineWalletDao.checkUserByEmail(email))
			throw new UnauthorizedAccessException("No User exist for this email address. Kindly Register");
		WalletUser user = onlineWalletDao.getUserByEmail(email);
		WalletAccount account = user.getAccountDetail();
		
		
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		return user.getUserID();
	}


	

	@Override
	public String registerUser(WalletUser user) {
		// TODO Auto-generated method stub
		if (onlineWalletDao.checkUserByEmail(user.getEmail()) == true)
			throw new UnauthorizedAccessException("A User already exist with same email address");
		WalletAccount account = new WalletAccount(0.00, null, status.active);
		user.setAccountDetail(account);
		onlineWalletDao.saveAccount(account);
		onlineWalletDao.saveUser(user);
		return user.getEmail();
	}

	
	@Override
	public Double addMoney(Integer userId, Double Amount) {
		WalletUser user = onlineWalletDao.getUser(userId);
		Integer accountId = user.getAccountDetail().getAccountID();
		WalletAccount account = onlineWalletDao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);
		return account.getAccountBalance();
	}

	
	@Override
	public Double showBalance(Integer userId) {
		WalletUser user = onlineWalletDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account.getAccountBalance();
	}

	
	@Override
	public void transactMoney(Integer userId, String beneficiaryEmail, Double amount) {
		if (onlineWalletDao.checkUserByEmail(beneficiaryEmail) == false)
			throw new InvalidException("The Beneficary doesn't exist");
		WalletUser beneficiary = onlineWalletDao.getUserByEmail(beneficiaryEmail);
		
		WalletUser user = onlineWalletDao.getUser(userId);
		if (user.getAccountDetail().getAccountBalance() < amount)
			throw new WrongValueException("The Amount cannot be greater then available Balance");
		Integer beneficiaryId = beneficiary.getUserID();
		Double beneficiaryBalance = beneficiary.getAccountDetail().getAccountBalance();
		beneficiary.getAccountDetail().setAccountBalance(beneficiaryBalance + amount);
		Double userBalance = user.getAccountDetail().getAccountBalance();
		user.getAccountDetail().setAccountBalance(userBalance - amount);
		createTransaction(userId, "Amount has been tranfered. Balance has been updated", amount);
		createTransaction(beneficiaryId, "Amount credited to your account. Balance has been updated", amount);
	}

	public void createTransaction(Integer userId, String description, Double amount) {
		WalletUser user = onlineWalletDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		WalletTransactions transaction = new WalletTransactions(description, LocalDateTime.now(), amount, balance);
		List<WalletTransactions> transactionList = account.getTransactionList();
		if (transactionList == null)
			transactionList = new ArrayList<WalletTransactions>();
		transactionList.add(transaction);
		onlineWalletDao.saveTransaction(transaction);
	}




	@Override
	public Integer loginAdmin(String loginName, String password) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<String> getUserList(Integer userId, String userStatus) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String changeUserStatus(Integer adminId, String loginName, String userStatus) {
		// TODO Auto-generated method stub
		return null;
	}
}
