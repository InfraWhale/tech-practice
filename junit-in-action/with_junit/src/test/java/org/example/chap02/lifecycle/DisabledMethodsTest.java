package org.example.chap02.lifecycle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisabledMethodsTest {
	private SUT systemUnderTest = new SUT("테스트 대상 시스템");;

	@Test
	@Disabled()
	void testRegularWork() {
		boolean canReceiveRegularWork =
				systemUnderTest.canReceiveRegularWork();
		// assertTrue(canReceiveRegularWork);
		assertTrue(false);
	}

	@Test
	@Disabled("기능 개발 중")
	void testAdditionalWork() {
		boolean canReceiveAdditionalWork =
				systemUnderTest.canReceiveAdditionalWork();
		//assertFalse(canReceiveAdditionalWork);
		assertFalse(true);
	}
}