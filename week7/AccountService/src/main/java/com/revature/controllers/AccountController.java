package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Account;

@RestController
@RequestMapping("accounts")
public class AccountController {
	private List<Account> accounts = new ArrayList<>();
	{
		accounts.add(new Account(1, 100, 1));
		accounts.add(new Account(2, 2, 1));
		accounts.add(new Account(3, 0, 2));
		accounts.add(new Account(4, 2000, 4));
		accounts.add(new Account(5, 2314312, 2));
		accounts.add(new Account(6, 123, 1));
		accounts.add(new Account(7, 15, 1));
		accounts.add(new Account(8, 3242, 2));
	}
	
	@GetMapping
	public List<Account> findAll() {
		return accounts;
	}
	
	@GetMapping("{id}")
	public Account findById(@PathVariable int id) {
		return accounts.parallelStream()
				.filter(account -> account.getId() == id)
				.findFirst()
				.get();
	}
	
	@GetMapping("owner/{ownerId}")
	public List<Account> findByOwner(@PathVariable int ownerId) {
		return accounts.parallelStream()
				.filter(account -> account.getOwner() == ownerId)
				.collect(Collectors.toList());
	}
	
	
}
