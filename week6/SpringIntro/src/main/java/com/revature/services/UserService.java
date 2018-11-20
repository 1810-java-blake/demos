package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.model.AppUser;

public interface UserService {

	AppUser findById(int id);

	List<AppUser> findAll();

}
