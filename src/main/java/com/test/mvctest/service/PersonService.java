package com.test.mvctest.service;

import java.util.List;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.mvctest.domain.Person;

/**
 * 
 */
@Service("PersonService")
@RemoteProxy(name="dwrService")
@Transactional 
public class PersonService {

	protected static Logger logger = LoggerFactory.getLogger(PersonService.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	@SuppressWarnings("unchecked")
	@RemoteMethod
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Person");

		// Retrieve all
		return query.list();
	}

	/**
	 * Retrieves a single person
	 */
	@RemoteMethod
	public Person get(Integer id) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person first
		Person person = (Person) session.get(Person.class, id);

		return person;
	}

	/**
	 * Adds a new person
	 */
	@RemoteMethod
	public String add(Person person) {
		
		logger.debug("Adding new person");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Save
		try {
			session.save(person);
			return "success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "failed";
		}
	}
	
	/**
	 * Adds a new person
	 */
	@RemoteMethod
	public String add(String firstName, String lastName, String balance) {
		
		logger.info("Adding new person");

		try {
			// Retrieve session from Hibernate
			Session session = sessionFactory.getCurrentSession();
	
			Person person = new Person();
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setBalance(Double.parseDouble(balance));
		
		    // Save
			session.save(person);
			return "success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "failed";
		}
	}

	/**
	 * Deletes an existing person
	 * 
	 * @param id
	 *            the id of the existing person
	 */
	@RemoteMethod
	public void delete(Integer id) {
		
		logger.debug("Deleting existing person");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person first
		Person person = (Person) session.get(Person.class, id);

		// Delete
		session.delete(person);
	}
	
	/**
	 * Deletes an existing person
	 * 
	 * @param id
	 *            the id of the existing person
	 */
	@RemoteMethod
	public void deleteAll() {
		
		logger.debug("Deleting all persons");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("delete from Person");

		// Retrieve all
		query.executeUpdate();
	}

	/**
	 * Edits an existing person
	 */
	@RemoteMethod
	public void edit(Person person) {
		
		logger.debug("Editing existing person");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, person.getId());

		// Assign updated values to this person
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		existingPerson.setBalance(person.getBalance());

		// Save updates
		session.save(existingPerson);
	}
}
