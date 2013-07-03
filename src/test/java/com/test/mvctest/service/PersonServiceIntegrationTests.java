package com.test.mvctest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.test.mvctest.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
// @DirtiesContext(classMode=ClassMode.AFTER_CLASS)
@ContextConfiguration(locations = {"classpath:/META-INF/spring/test-root-context.xml",
		                           "classpath:/META-INF/spring/test-datasource-config.xml",
		                           "classpath:/META-INF/test-hibernate-context.xml"})
public class PersonServiceIntegrationTests {

	@Autowired
	private PersonService personService;
	
	@Test
	@Transactional
	public void testlistAll() {
		
		List<Person> result = personService.getAll();

		// Retrieve all
		assertNotNull(result);
		assertEquals(result.size(), 1);
	}
	
	@Test
	@Transactional
	public void testGetPerson() {

		Person result = personService.get(1);

		// Retrieve all
		assertNotNull(result);
		assertEquals(result.getBalance(), new Double(100));
		assertEquals(result.getFirstName(), "john");
		assertEquals(result.getLastName(), "doe");
	}
	
	@Test
	@Transactional
	public void testAddPerson() {

		Person jackSparrow = new Person();
		jackSparrow.setBalance(10D);
		jackSparrow.setFirstName("Jack");
		jackSparrow.setLastName("Sparrow");
		
		personService.add(jackSparrow);

		List<Person> result = personService.getAll();
		assertNotNull(result);
		assertEquals(result.size(), 2);
		
		Person jack = personService.get(result.get(1).getId());
		
		assertEquals(jack.getLastName(), "Sparrow");
	}
	
	@Test
	@Transactional
	public void testUpdatePerson() {
		
		Person johnDoe = new Person();
		johnDoe.setId(1);
		johnDoe.setBalance(200D);
		johnDoe.setFirstName("john");
		johnDoe.setLastName("doe");
		
		personService.edit(johnDoe);
		
		Person john = personService.get(1);
		assertEquals(john.getBalance(), new Double(200));
	}
	
	@Test
	@Transactional
	public void testDeletePerson()  {
		
		personService.delete(1);
		
		List<Person> result = personService.getAll();

		// Retrieve all
		assertNotNull(result);
		assertEquals(result.size(), 0);
	}
}
