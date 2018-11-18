package com.revature.launcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.model.Bear;
import com.revature.model.Cave;
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
		Set<Bear> bears = new HashSet<>();
		// bears.addAll(l.findAllBearsCriteria());
		// bears.addAll(l.findAllByColorCriteria("brown"));
//		bears.addAll(l.findAllByColorAndBreed("brown", "brown"));
//		bears.addAll(l.findAllBearsHQL());
//		bears.addAll(l.findAllByColorHQL("brown"));
//		bears.addAll(l.findAllByColorAndBreedHQL("brown", "teddy"));
//		bears.addAll(l.findAllByCaveId(1));
		
		
//		bears.forEach(each -> {
//			System.out.println(each);
//		});
		
//		l.playingWithPersistentObjects();
//		System.out.println(l.load(1).getBreed());
		
		
		
//		Bear b = l.get(1);
//		System.out.println(b);
//		b.setColor("color");
//		l.merge(b);
		
//		Set cubs = new HashSet<>();
//		cubs.add(new Bear(2, null, null, 0, null, null));
//		Bear b = new Bear(0, "red", "yogi", 2, 
//					new Cave(1, 0, null, null), 
//					cubs
//					);

		
//		l.save(b);
//		Bear b = l.get(8);
//		System.out.println(b);
//		b.getCubs().forEach(cub -> {
//			System.out.println(cub);
//		});
		
		// add to ones cubs
//		Bear one = l.get(1);
//		Bear eight = l.get(8);
//		one.getCubs().add(eight);
//		l.merge(one);
		Bear b = l.get(1);
		b.getCubs().forEach(cub -> {
			System.out.println(cub);
		});
	}
	
	public void save(Bear b) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		System.out.println(s.save(b));
		t.commit();
		s.close();
	}
	
	public void merge(Bear b) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		Bear b2 = (Bear) s.get(Bear.class, b.getId());
		System.out.println(b2.getColor());
		Bear persistentBear = (Bear) s.merge(b);
		System.out.println(b == persistentBear); // should be false regardless of one already being there or not
		System.out.println(b2.getColor());
		t.commit();
		s.close();
	}
	
	
	
	public void update(Bear b) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
//		s.get(Bear.class, b.getId());
		s.update(b);
		t.commit();
		s.close();
	}
	
	
	public Bear get(int id) {
		Session s = sessionFactory.openSession();
		Bear b = (Bear) s.get(Bear.class, id);
		Hibernate.initialize(b.getCave());
		b.getCubs().forEach(cub -> {
			Hibernate.initialize(cub.getCave());
		});
		
		s.close();
		return b;
	}
	
	public Bear load(int id) {
		Session s = sessionFactory.openSession();
		Bear b = (Bear) s.load(Bear.class, id);
		Hibernate.initialize(b.getCave());
		Hibernate.initialize(b.getCubs());
		s.close();
		return b;
	}

	public List<Bear> findAllBearsCriteria() {
		Session s = sessionFactory.openSession();
		Criteria criteria = s.createCriteria(Bear.class);
		List<Bear> bears = criteria.list();
		s.close();
		return bears;
	}

	public List<Bear> findAllByColorCriteria(String color) {
		Session s = sessionFactory.openSession();
		Criteria c = s.createCriteria(Bear.class);
		c.add(Restrictions.ilike("color", color));
		List<Bear> bears = c.list();
		s.close();
		return bears;
	}
	
	public List<Bear> findAllByColorAndBreed(String color, String breed) {
		Session s = sessionFactory.openSession();
		Criteria c = s.createCriteria(Bear.class);
		c.add(
				Restrictions.and(
						Restrictions.ilike("color", color),
						Restrictions.ilike("breed", breed)
				)	
		);
		List<Bear> bears = c.list();
		s.close();
		return bears;
	}
	
	public List<Bear> findAllBearsHQL() {
		Session s = sessionFactory.openSession();
		Query q = s.createQuery("FROM Bear");
		List<Bear> bears = q.list();
		s.close();
		return bears;
	}

	public List<Bear> findAllByColorHQL(String color) {
		Session s = sessionFactory.openSession();
		Query q = s.createQuery(
				"FROM Bear WHERE UPPER(color) = UPPER(:col)"
		);
		q.setParameter("col", color);
		List<Bear> bears = q.list();
		s.close();
		return bears;
	}
	
	public List<Bear> findAllByColorAndBreedHQL(String color, String breed) {
		Session s = sessionFactory.openSession();
		Query q = s.createQuery(
				"FROM Bear WHERE " +
				"UPPER(color) = UPPER(:col) " + 
				"AND UPPER(breed) = UPPER(:breed)"
		);
		q.setParameter("col", color);
		q.setParameter("breed", breed);
		List<Bear> bears = q.list();
		s.close();
		return bears;
	}
	
	public List<Bear> findAllByCaveId(int id) {
		Session s = sessionFactory.openSession();
		Query q = s.createQuery(
				"FROM Bear WHERE " +
				"cave.id = :id "
		);
		q.setParameter("id", id);
		List<Bear> bears = q.list();
		bears.forEach(bear -> {
			Hibernate.initialize(bear.getCubs());
//			Hibernate.initialize(bear.getCave());
			System.out.println(bear.getCave().getSquareFootage());
		});
//		ObjectMapper om = new ObjectMapper();
//		try {
//			String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(bears);
//			System.out.println(json);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		s.close();
		return bears;
	}
	
	public void playingWithPersistentObjects() {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery(
				"FROM Bear WHERE id = :id"
		);
		q.setParameter("id", 1);
		Bear bear = (Bear) q.uniqueResult();
		System.out.println(bear);
//		bear.setColor("yellow");
//		System.out.println(bear);
		t.commit();
		s.close();
	}
	
}
