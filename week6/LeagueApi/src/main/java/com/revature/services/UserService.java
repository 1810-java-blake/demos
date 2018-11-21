package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.AppUser;
import com.revature.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public List<AppUser> findAll() {
		return userRepo.findAll();
	}

	public AppUser findById(int id) {
		return userRepo.getOne(id);
	}

	public AppUser save(AppUser user) {
		return userRepo.save(user);
	}

}
