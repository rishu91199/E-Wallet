package com.capgemini.ewallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ewallet.dao.WalletAccountDao;
import com.capgemini.ewallet.dao.WalletTransactionDao;
import com.capgemini.ewallet.entity.WalletAccount;
import com.capgemini.ewallet.entity.WalletTransaction;
import com.capgemini.ewallet.exception.TransactionException;



@Service
@Transactional
public class TransactionServiceImpl implements TransactionService
{
	
	@Autowired
	WalletAccountDao accountdao;
	
	@Autowired
	WalletTransactionDao transactionDao;
	
	@Override
	@Transactional(readOnly = true)
	public WalletAccount findAccount(int accId) {
		// TODO Auto-generated method stub
		Optional<WalletAccount> a = accountdao.findById(accId);
		
		if (a.isPresent()) 
			return a.get();
		else
			throw new TransactionException("AccountId  not found!"); 
		
	}
	@Override
	@Transactional(readOnly = true)
	public List<WalletTransaction> transactionHistory(int senderId) {
		// TODO Auto-generated method stub
		List<WalletTransaction>history = transactionDao.findBySenderAccId(senderId);
		System.out.println(history.get(0));
		return history;
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean TransferAmount(WalletTransaction transfer) {
		// TODO Auto-generated method stub
		if(transfer.getAmount()<=0) {
		throw new TransactionException("Amount is too less!!! ")			;
		}
			
			
			
			WalletAccount sender = findAccount(transfer.getSenderAccId());
			WalletAccount receiver = findAccount(transfer.getReceiverAccId());
			double senderbalance = sender.getBalance()-transfer.getAmount();
			double receiverbalance = receiver.getBalance() +transfer.getAmount();
			updateBalance(sender.getAccountId(),senderbalance);
			updateBalance(receiver.getAccountId(),receiverbalance);
		
			transactionDao.save(transfer);
		return true;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateBalance(int accId, double amount) {
		// TODO Auto-generated method stub
		WalletAccount wallet;
		Optional<WalletAccount> account = accountdao.findById(accId);
		if (account.isPresent())
			wallet = account.get();
		else
			throw new TransactionException("Account not found!"); 

		wallet.setBalance(amount);

	}

	

}
