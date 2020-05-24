package com.capgemini.ewallet.service;

import java.util.List;

import com.capgemini.ewallet.entity.CustomerSupport;





public interface CustomerSupportService {
	
	boolean addIssue(CustomerSupport customersupport);
	List<CustomerSupport> getAllIssue();
	
	CustomerSupport saveIssue(CustomerSupport customersupport);

}
	