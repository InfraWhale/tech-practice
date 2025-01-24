package org.example.chap02.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Disabled("기능 개발 중")
class DisabledClassTest {
	private SUT systemUnderTest = new SUT("테스트 대상 시스템");;

	@Test
	void testRegularWork() {
		boolean canReceiveRegularWork =
				systemUnderTest.canReceiveRegularWork();
		// assertTrue(canReceiveRegularWork);
		assertTrue(false);
	}

	@Test
	void testAdditionalWork() {
		boolean canReceiveAdditionalWork =
				systemUnderTest.canReceiveAdditionalWork();
		//assertFalse(canReceiveAdditionalWork);
		assertFalse(true);
	}
}