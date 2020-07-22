package com.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import entity.Actor;
import entity.Movie;

@Service("saveDataDao")
public class SaveDataDaoImpl implements SaveDataDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	//@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	@Transactional
	public void saveDataToDatabase() {
		/*Query query = sessionFactory.getCurrentSession().createQuery("select m from Movie m where m.name='American Hustle'" );
		List list  = query.list();*/
		Movie movie1 = new Movie("American Hustle");
		Movie movie2 = new Movie("The Prestige");

		Actor actor1 = new Actor("Christian Bale");
		Actor actor2 = new Actor("Hugh Jackman");

		movie1.getActors().add(actor1);

		movie2.getActors().add(actor1);
		movie2.getActors().add(actor2);
		
		sessionFactory.getCurrentSession().persist(movie1);
		sessionFactory.getCurrentSession().persist(movie2);
		System.out.println("wait here first thread");
	}

	@Override
	//@Transactional()
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public void readFromDatabase() {
		Query query = sessionFactory.getCurrentSession().createQuery("select m from Movie m where m.name='American Hustle'" );
		List list  = query.list();
		System.out.println("wait here second thread");
	}
}
