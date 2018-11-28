package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.AppUser;
import com.revature.services.UserService;


@RestController
@RequestMapping(path = "users")
public class UserController {

	@Autowired
	private UserService us;

	@GetMapping
	public List<AppUser> findAll() {
		return us.findAll();
	}

	@GetMapping("{id}")
	public AppUser findById(@PathVariable int id) {
		return us.findById(id);
	}

	@PutMapping
	public AppUser update(@Valid @RequestBody AppUser user) {
		return us.save(user);
		
	}
}
