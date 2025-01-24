package org.example.chap02.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SystemUnderTest {
	private static ResourceForAllTests resourceForAllTests;
	private SUT systemUnderTest;

	@BeforeAll
	static void setUpClass() {
		resourceForAllTests =
				new ResourceForAllTests("테스트를 위한 리소스");
	}

	@AfterAll
	static void testDownClass(){
		resourceForAllTests.close();
	}

	@BeforeEach
	void setUp() {
		systemUnderTest = new SUT("테스트 대상 시스템");
	}

	@AfterEach
	void tearDown() {
		systemUnderTest.close();
	}

	@Test
	void testRegularWork() {
		boolean canReceiveRegularWork =
				systemUnderTest.canReceiveRegularWork();
		assertTrue(canReceiveRegularWork);
	}

	@Test
	void testAdditionalWork() {
		boolean canReceiveAdditionalWork =
				systemUnderTest.canReceiveAdditionalWork();
		assertFalse(canReceiveAdditionalWork);
	}
}