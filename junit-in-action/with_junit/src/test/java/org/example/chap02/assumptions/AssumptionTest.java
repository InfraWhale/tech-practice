package org.example.chap02.assumptions;

import org.example.chap02.assumptions.environment.JavaSpecification;
import org.example.chap02.assumptions.environment.OperationSystem;
import org.example.chap02.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AssumptionTest {
	private static String EXPECTED_JAVA_VERSION = "1.8";
	private TestsEnvironment environment = new TestsEnvironment(
			new JavaSpecification(
					System.getProperty("java.vm.specification.version")
			),
			new OperationSystem(
					System.getProperty("os.name"),
					System.getProperty("os.arch")
			)
	);

	private SUT systemUnderTest = new SUT();

	@BeforeEach
	void setUp() {
		assumeTrue(environment.isWindows());
	}

	@Test
	void testNoJobToRun() {

		assumingThat(
				() -> environment.getJavaVersion().equals("EXPECTED_JAVA_VERSION"),
				() -> assertFalse(systemUnderTest.hasJobToRun()));
	}

	@Test
	void testJobToRun() {
		assumeTrue(environment.isAmd64Architecture());
		systemUnderTest.run(new Job());
		assertTrue(systemUnderTest.hasJobToRun());
	}
}