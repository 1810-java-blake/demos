package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Champion;
import com.revature.repos.ChampionRepo;

@Service
public class ChampionService {
	@Autowired
	private ChampionRepo champRepo;

	public List<Champion> findAll() {
		return champRepo.findAll();
	}

	public Champion findById(int id) {
		return champRepo.getOne(id); // there is none
	}

	public List<Champion> findByRole(String role) {
		return champRepo.findByRole(role);
	}

	public int save(Champion c) {
		champRepo.save(c);
		return c.getId();
	}
}
