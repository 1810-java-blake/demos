package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Champion;

@Repository
public interface ChampionRepo extends JpaRepository<Champion, Integer> {

	List<Champion> findByRole(String role);

}
