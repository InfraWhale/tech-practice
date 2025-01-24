package org.example.chap02.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertAllTest2 {

	SUT systemUnderTest = new SUT("테스트 대상 시스템");
	
	@Test
	@DisplayName("테스트 대상 시스템을 검증한다.")
	void testSystemVerification() {

		systemUnderTest.verify();
		assertTrue(systemUnderTest.isVerified(),
				() -> "테스트 대상 시스템을 검증했는지 확인");
	}

	@Test
	@DisplayName("테스트 대상 시스템을 검증하지 않았다.")
	void testSystemNotUnderVerification() {

		assertFalse(systemUnderTest.isVerified(),
			() -> "테스트 대상 시스템을 검증하지 않았는지 확인");
	}

	@Test
	@DisplayName("현재 테스트 대상 시스템은 작업이 없다.")
	void testNoJob() {

		assertNull(systemUnderTest.getCurrentJob(),
				() -> "테스트 대상 시스템은 현재 작업이 없는지 확인");
	}
}