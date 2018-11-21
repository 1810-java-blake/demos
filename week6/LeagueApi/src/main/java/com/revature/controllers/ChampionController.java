package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator;
import com.revature.model.Champion;
import com.revature.services.ChampionService;

@RestController
@RequestMapping("champions")
public class ChampionController {

	private ChampionService cs;

	public ChampionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ChampionController(ChampionService cs) {
		super();
		this.cs = cs;
	}

	@GetMapping
	public List<Champion> findAll() {
		return cs.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Champion> findById(@PathVariable int id, HttpServletRequest req) {
//		req.getSession().setAttribute("test", "test"); // you can do this if you want sessions
		
		// user response entity if you want finer controller over the resp
		// such as conditionally setting the status code
		ResponseEntity<Champion> resp = new ResponseEntity<>(cs.findById(id), HttpStatus.PARTIAL_CONTENT);
		return resp;
//		throw new NullPointerException();
	}

	@GetMapping("role/{role}")
	public List<Champion> findByRole(@PathVariable String role) {
		return cs.findByRole(role);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public int save(@RequestBody Champion c) {
		return cs.save(c);
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> exceptionHandler() {
		return new ResponseEntity<String>("An error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
