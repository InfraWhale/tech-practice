package org.example.chap02.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("repository")
public class CustomersRepositoryTest {
	private String CUSTOMER_NAME = "John Smith";
	private CustomersRepository repository = new CustomersRepository();

	@Test
	void testNonExistence() {
		boolean exists = repository.contains("John Smith");

		assertFalse(exists);
	}

	@Test
	void testCustomerPersistence() {
		repository.persist(new Customer(CUSTOMER_NAME));

		assertTrue(repository.contains("John Smith"));
	}
}