package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Fruit;
import com.revature.services.FruitService;

public class FruitServlet extends HttpServlet {

	private FruitService fs = FruitService.currentImplementation;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse resp) throws ServletException, IOException {
		super.service(arg0, resp);
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("retreiving all fruit");
//		List<Fruit> fruits = fs.findAll();
		List<Fruit> fruits = new ArrayList<Fruit>();
		fruits.add(new Fruit(1, "Mango", "Orange", 8));
		fruits.add(new Fruit(2, "Pineapple", "Yellow", 10));

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(fruits);
		resp.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		Fruit f = om.readValue(req.getReader(), Fruit.class);

		int id = fs.save(f);
		System.out.println(id);

		resp.setStatus(201);
		PrintWriter pw = resp.getWriter();
		pw.write("" + id);
		pw.flush();

	}

}
