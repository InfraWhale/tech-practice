package org.example.chap07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientSkeleton {
	@BeforeAll
	public static void setUp() {
		// Jetty 서버 시작
		// http://localhost:8081/testGetContentOk 경로로 접근
		// "It works" 반환
	}

	@AfterAll
	public static void tearDown() {
		// Jetty 서버 중지
	}

	@Test
	@Disabled(value = "현재는 스켈레톤 테스트. 구동시 실패.")
	public void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String workingContent = client.getContent(new URL(
				"http://localhost:8081/testGetContentOk"));
		assertEquals("It works", workingContent);
	}
}