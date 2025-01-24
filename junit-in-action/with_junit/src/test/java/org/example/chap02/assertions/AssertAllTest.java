package org.example.chap02.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertAllTest {

	@Test
	@DisplayName("기본적으로 테스트 대상 시스템은 검증하지 않는다.")
	void testSystemNotVerified() {
		SUT systemUnderTest = new SUT("테스트 대상 시스템");

		assertAll("테스트 대상 시스템을 검증하지 않았는지 확인",
				() -> assertEquals("테스트 대상 시스템", systemUnderTest.getSystemName()),
				() -> assertFalse(systemUnderTest.isVerified()));
	}

	@Test
	@DisplayName("테스트 대상 시스템을 검증한다.")
	void testSystemVerification() {
		SUT systemUnderTest = new SUT("테스트 대상 시스템");

		systemUnderTest.verify();

		assertAll("테스트 대상 시스템을 검증했는지 확인",
				() -> assertEquals("테스트 대상 시스템",
						systemUnderTest.getSystemName()),
				() -> assertTrue(systemUnderTest.isVerified()));
	}
}