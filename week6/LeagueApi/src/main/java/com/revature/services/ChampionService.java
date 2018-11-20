package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.model.Champion;

@Service
public class ChampionService {
	

	private List<Champion> champions = new ArrayList<>();

	{
		champions.add(new Champion(1, "Miss Fortune", "ADC"));
		champions.add(new Champion(2, "Elise", "Jungle"));
		champions.add(new Champion(3, "Blitzpull", "Support"));
	}

	public List<Champion> findAll() {
		return champions;
	}

	public Champion findById(int id) {
		return champions.parallelStream()
				.filter(champ -> champ.getId() == id)
				.findFirst()
				.get(); // will throw an
																									// exception if
																									// there is none
	}
	
	public List<Champion> findByCostAndRole(int cost, String role) {
		System.out.println("cost = " + cost);
		System.out.println("role = " + role);
		return champions.parallelStream()
				.filter(champ -> champ.getRole().equals(role))
				.collect(Collectors.toList());	
	}
	
	public int save(Champion c) {
		int id = (int) Math.floor(Math.random()*1000000 + 1);
		c.setId(id);
		champions.add(c);
		return id;
	}
}
