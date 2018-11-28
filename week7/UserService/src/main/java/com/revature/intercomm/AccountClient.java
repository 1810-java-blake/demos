package com.revature.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.model.Account;

@FeignClient("account-service")
public interface AccountClient {
	@GetMapping("/accounts/owner/{id}")
	List<Account> getAccountsByOwner(@PathVariable("id") int id);
	
	@GetMapping
	List<Account> findAll();
}
