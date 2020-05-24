package com.capgemini.ewallet.service;

import com.capgemini.ewallet.dto.Login;
import com.capgemini.ewallet.entity.WalletUser;

import java.util.List;



public  interface WalletUserService {
		boolean addAccount(WalletUser walletuser);
		List<WalletUser> getAllWalletUser();
		
		WalletUser saveUser(WalletUser user);
		WalletUser validateUser(Login userLogin);
		
	
}

