package com.capgemini.ewallet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ewallet.dao.WalletAccountDao;
import com.capgemini.ewallet.dao.WalletTransactionDao;
import com.capgemini.ewallet.dao.WalletUserDao;
import com.capgemini.ewallet.entity.WalletAccount;
import com.capgemini.ewallet.entity.WalletUser;
import com.capgemini.ewallet.exception.TransactionException;


@Service
@Transactional
public class WalletAccountServiceImpl implements WalletAccountService{
	
	@Autowired
	WalletUserDao userdao;
	@Autowired
	WalletAccountDao accountdao;
	@Autowired
	WalletTransactionDao transferdao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBalance(int accId, double amount) {
		// TODO Auto-generated method stub
		WalletAccount w;
		Optional<WalletAccount> p = accountdao.findById(accId);
		if (p.isPresent())
			w = p.get();
		else
			throw new TransactionException("Account not found!"); 

		w.setBalance(amount);

	}
    @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public double depositmoney(WalletAccount wallet, double amount) {
		// TODO Auto-generated method stub
		if (amount >= 0) {
			double new_balance = wallet.getBalance() + amount;			
			updateBalance(wallet.getAccountId(),new_balance);
			return new_balance;
		}

		return wallet.getBalance();
	}
	
	@Override
	@Transactional(readOnly = true)
	public WalletAccount findAccount(int accId) {
		// TODO Auto-generated method stub
		Optional<WalletAccount> account = accountdao.findById(accId);
		
		if (account.isPresent()) 
			return account.get();
		else
			throw new TransactionException("AccountId  not found!"); 
		
	}
	@Override
	@Transactional(readOnly = true)
	public WalletUser showBalance(int userId) {
		// TODO Auto-generated method stub
		WalletUser user = userdao.findById(userId).orElse(new WalletUser());
		
		if(user.getId() == 0) {
			throw new TransactionException("Account not found!");
			
		}
		else {
			System.out.println(user.getWallet().getBalance()+" =================== ");
			return user;
		}

	}


}