package com.capgemini.ewallet.service;

import com.capgemini.ewallet.entity.WalletAccount;
import com.capgemini.ewallet.entity.WalletUser;

public interface WalletAccountService {
	
	
	public double depositmoney(WalletAccount w, double amount);
	public void updateBalance(int accId, double amount);
	public WalletAccount findAccount(int accId);
	public WalletUser showBalance(int userId);
	

}
