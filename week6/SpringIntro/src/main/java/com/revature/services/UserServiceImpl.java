package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserDao;
import com.revature.model.AppUser;

@Service
public class UserServiceImpl implements UserService {

	private UserDao ud;

	public UserServiceImpl() {
		super();
		System.out.println("called empty constructor");
		// TODO Auto-generated constructor stub
	}

	@Autowired // or you can use @Inject
	// @Qualifier("User Dao") inject by name as opposed to by type
	public UserServiceImpl(UserDao ud) {
		super();
		System.out.println("called constructor that sets the user dao");
		this.ud = ud;
	}

	@Override
	public AppUser findById(int id) {
		return ud.findById(id);
	}

	@Override
	public List<AppUser> findAll() {
		return ud.findAll();
	}

}
