package com.capgemini.ewallet.dao;

import java.util.List;

import com.capgemini.ewallet.entities.WalletAccount;
import com.capgemini.ewallet.entities.WalletTransactions;
import com.capgemini.ewallet.entities.WalletUser;
import com.capgemini.ewallet.entities.WalletAccount.status;

public interface OnlineWalletDao {
	void saveUser(WalletUser user);

	WalletUser getUser(Integer userId);

	WalletAccount getAccount(Integer accountId);

	WalletTransactions getTransaction(Integer transactionId);

	void saveAccount(WalletAccount account);

	WalletUser getUserByEmail(String email);

	boolean checkUserByEmail(String email);

	void saveTransaction(WalletTransactions transaction);

}
