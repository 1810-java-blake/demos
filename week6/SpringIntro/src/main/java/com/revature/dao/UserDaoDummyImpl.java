package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.revature.model.AppUser;
import com.revature.model.UserRole;

@Repository
public class UserDaoDummyImpl
		implements UserDao, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
	private Logger log = Logger.getRootLogger();

	private int dummyId = 25;

	public UserDaoDummyImpl() {
		super();
		System.out.println("instantiation");
	}

	@Override
	public AppUser findById(int id) {
		return new AppUser(dummyId, "test", "test", new UserRole(1, "admin"));
	}

	@Override
	public List<AppUser> findAll() {
		List<AppUser> users = new ArrayList<>();
		users.add(new AppUser(dummyId, "test", "test", new UserRole(1, "admin")));
		return users;
	}

	@Override
	public AppUser findByUsernameAndPassword(String username, String password) {
		return new AppUser(dummyId, "test", "test", new UserRole(1, "admin"));
	}

	public int getDummyId() {
		return dummyId;
	}

	public void setDummyId(int dummyId) {
		System.out.println("populating property dummyId with value: " + dummyId);
		this.dummyId = dummyId;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("application context aware");
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("bean factory aware");
	}

	@Override
	public void setBeanName(String arg0) {
		System.out.println("bean name aware, name is: " + arg0);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing Beans: afterPropertiesSet()");
	}

	public void customInit() {
		System.out.println("my custom init");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy");
	}

	public void customDestroy() {
		System.out.println("my custom destroy");
	}

}
