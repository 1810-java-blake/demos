package com.revature.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.dao.UserDao;
import com.revature.services.UserService;

public class SpringLauncher {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

//		UserService us = (UserService) ac.getBean("User Service");
//		System.out.println(us.findAll());
		
		UserDao ud = (UserDao) ac.getBean(UserDao.class);
		UserDao ud2 = (UserDao) ac.getBean(UserDao.class);
		
		
		System.out.println(ud == ud2);
		
		System.out.println("in use");
		
		((AbstractApplicationContext) ac).close();
		Thread.sleep(1000000);

	}
}
