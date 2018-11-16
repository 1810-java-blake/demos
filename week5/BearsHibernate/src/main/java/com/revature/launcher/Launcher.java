package com.revature.launcher;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.model.Bear;
import com.revature.util.SessionFactoryUtil;

public class Launcher {
	/**
	 * Hibernate utilizes sessions for connections to the database. JDBC used
	 * connections.
	 * 
	 * Hibernate will implement connection pooling. 1. Connections will be
	 * maintained. 2. We can have 1-n connections to the database.
	 * 
	 * Spinning up a new connection to the database is expensive. By not having to
	 * spin up new connections for every transaction, you save time and resources.
	 * 
	 * Secondly, the connections will be shared across transactions over time. It's
	 * a session because our transaction is utilizing a connection for a given
	 * period. And will release that session such that other transaction can use it.
	 * 
	 * Finally, hibernate will maintain a pool of connections, so concurrent
	 * transactions can occur without having to wait for a connection to open up.
	 */
	private SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

	public static void main(String[] args) {
		Launcher l = new Launcher();
		System.out.println(l.findAllBears());

	}

	public List<Bear> findAllBears() {
		Session s = sessionFactory.openSession();
		Criteria criteria = s.createCriteria(Bear.class);
		List<Bear> bears = criteria.list();
		s.close();
		return bears;
	}

}
