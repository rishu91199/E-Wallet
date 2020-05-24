package com.capgemini.ewallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.ewallet.entity.WalletAccount;



@Repository
public interface WalletAccountDao extends JpaRepository<WalletAccount, Integer> {

}