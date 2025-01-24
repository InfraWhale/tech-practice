package org.example.chap02.nested;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NestedTestsTest {
	public static final String FIRST_NAME = "John";
	public static final String LAST_NAME = "Smith";

	// 왜 굳이 이렇게 나눠서 테스트 하는거지?
	// 각 기능별로 여러개의 @Test를 묶어줄 수 있다.
	// 아래 BuilderTest 안에는 Builder 관련 테스트만 들어갈 것이다.
	// 그런데 고객 관련 다른 메서드를 여러 방면으로 테스트 해야 한다면,
	// @Nested 하나 더 파서 그 안에 여러 테스트를 넣어서 분리 가능하다.
	@Nested
	class BuilderTest {
		private String MIDDLE_NAME = "Michael";

		@Test
		void customerBuilder() throws ParseException {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date customerDate = simpleDateFormat.parse("04-21-2019");

			Customer customer = new Customer.Builder(
					Gender.MALE, FIRST_NAME, LAST_NAME)
					.withMiddleName(MIDDLE_NAME)
					.withBecomeCustomer(customerDate)
					.build();

			assertAll( () -> {
				assertEquals(Gender.MALE, customer.getGender());
				assertEquals(FIRST_NAME, customer.getFirstName());
				assertEquals(LAST_NAME, customer.getLastName());
				assertEquals(MIDDLE_NAME, customer.getMiddleName());
				assertEquals(customerDate, customer.getBecomeCustomer());
			});
		}
	}

	@Nested
	class CustomerEqualsTest {
		private String OTHER_FIRST_NAME = "John";
		private String OTHER_LAST_NAME = "Doe";

		@Test
		void testDifferentCustomers() {
			Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();
			Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME)
					.build();
			assertNotEquals(customer, otherCustomer);
		}

		@Test
		void testSameCustomer() {
			Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();
			Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();

			assertAll(() -> {
				assertEquals(customer, otherCustomer);
				assertNotSame(customer, otherCustomer);
			});
		}
	}

	@Nested
	class CustomerHashCodeTest {
		private String OTHER_FIRST_NAME = "John";
		private String OTHER_LAST_NAME = "Doe";

		@Test
		void testDifferentCustomers() {
			Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();
			Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME)
					.build();
			assertNotEquals(customer.hashCode(), otherCustomer.hashCode());
		}

		@Test
		void testSameCustomer() {
			Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();
			Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
					.build();
			assertEquals(customer.hashCode(), otherCustomer.hashCode());
		}
	}

}