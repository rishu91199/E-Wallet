package com.capgemini.ewallet.service;

import java.util.List;

import com.capgemini.ewallet.entity.WalletAccount;
import com.capgemini.ewallet.entity.WalletTransaction;

public interface TransactionService 
{
	public Boolean TransferAmount(WalletTransaction transfer);
	public List<WalletTransaction> transactionHistory(int id);
	public void updateBalance(int accId, double amount);
	public WalletAccount findAccount(int accId);
}
