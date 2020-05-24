package com.capgemini.ewallet.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ewallet.dao.WalletUserDao;
import com.capgemini.ewallet.dto.Login;
import com.capgemini.ewallet.entity.WalletUser;
import com.capgemini.ewallet.exception.TransactionException;
import com.capgemini.ewallet.service.WalletUserService;

@Service
@Transactional
public class WalletUserServiceImpl implements WalletUserService {

	
	@Autowired
	private WalletUserDao walletuserdao;

	@Override
	public List<WalletUser> getAllWalletUser() {
		// TODO Auto-generated method stub
		return walletuserdao.findAll();
	}
	
	@Override
	public WalletUser saveUser(WalletUser user) {
		return walletuserdao.save(user);
	}

	@Override
	public boolean addAccount(WalletUser walletuser) {
	
		WalletUser accountResult= walletuserdao.save(walletuser);
		if(accountResult!=null) {
			return true;
		}
		else
			return false;
	}
	
	@Override
	public WalletUser validateUser(Login userLogin) throws TransactionException{
		
		Optional<WalletUser> optionalUser= walletuserdao.LoginDetails(userLogin.getUsername());
		WalletUser user;
		if(optionalUser.isPresent()) {
			user=optionalUser.get();
			if(user.getPass().equals(userLogin.getPass())){
				return user;
			}
			throw new TransactionException("Incorrect Password");
			
		}
		else
			throw new TransactionException("Incorrect User Name");
			
		

	}

	

	
	
}
