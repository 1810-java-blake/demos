package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercomm.AccountClient;
import com.revature.model.Account;
import com.revature.model.AppUser;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private AccountClient ac;
	
	private List<AppUser> users = new ArrayList<>();
	{
		users.add(new AppUser(1, "blake", "pass", 2, null));
		users.add(new AppUser(2, "An", "pass", 0, null));
		users.add(new AppUser(3, "Jose", "password", 4, null));
		users.add(new AppUser(4, "Kyle","password", 1, null));
	}
	
	@GetMapping
	public List<AppUser> findAll() {
		List<AppUser> users = this.users;
		users.forEach(user -> user.setAccounts(ac.getAccountsByOwner(user.getId())));
		return users;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AppUser> findById(@PathVariable int id) {
		AppUser user = users.parallelStream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.get();
		// can use rest template or feign client
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<List<Account>> resp= rt.exchange("http://localhost:8089/accounts/owner/" +user.getId(),
//				HttpMethod.GET, 
//				null, 
//				new ParameterizedTypeReference<List<Account>>(){}
//				);
//		user.setAccounts(resp.getBody());
		
		List<Account> accounts = ac.getAccountsByOwner(user.getId());
		user.setAccounts(accounts);
		if(accounts == null) {
			return new ResponseEntity<>(user, HttpStatus.PARTIAL_CONTENT);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
	
}
