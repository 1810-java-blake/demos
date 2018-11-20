package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.AppUser;
import com.revature.model.Champion;
import com.revature.model.UserRole;

@Controller
public class TestController {

	public TestController() {
		super();
		System.out.println("created");
		// TODO Auto-generated constructor stub
	}

	@GetMapping("test")
	@ResponseBody
	public String test() {
		System.out.println("test");
		return "test";
	}

	@GetMapping("/users")
	@ResponseBody
	public List<AppUser> findAll() {
		List<AppUser> users = new ArrayList<>();
		users.add(new AppUser(1, "blake", "pass", new UserRole(1, "admin")));
		return users;
	}

	@GetMapping("/champions/{id}")
	@ResponseBody
	public Champion findById(@PathVariable int id) {
		return new Champion(id, "Ashe", "Support");
	}

	@GetMapping("champions")
	@ResponseBody
	public List<Champion> findAllChampions() {
		List<Champion> champions = new ArrayList<>();
		champions.add(new Champion(1, "Teemo", "Being Small"));
		champions.add(new Champion(2, "Nekko", "Looking Like Hecarim"));
		return champions;
	}

	@PostMapping("champions")
	@ResponseBody
	public int saveChampion(@RequestBody Champion c) {
		System.out.println(c);
		return (int) Math.floor(Math.random() * 100);
	}

}
