package com.revature.intercomm;

import java.util.List;

import org.springframework.stereotype.Component;

import com.revature.model.Account;

@Component
public class AccountClientFallback implements AccountClient {

	@Override
	public List<Account> getAccountsByOwner(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
