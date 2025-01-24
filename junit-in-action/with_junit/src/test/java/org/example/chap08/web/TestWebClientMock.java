package org.example.chap08.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class TestWebClientMock {

	@Test
	public void testGetContentOk() throws Exception {
		MockHttpURLConnection mockConnection = new MockHttpURLConnection();

		mockConnection.setExpectedInputStream(
				new ByteArrayInputStream("It works".getBytes())
		);

		// 맨 초기 방식
		// URL 클래스가 final이므로 상속이 안된다.
//		MockURL mockURL = new MockURL();
//		mockURL.setupOpenConnection(mockConnection);
//		WebClient client = new WebClient();
//		String workingContent = client.getContent(mockURL);

		TestableWebClient client = new TestableWebClient();
		client.setHttpUrlConnection(mockConnection);

		String result = client.getContent(new URL("http://localhost"));

		assertEquals("It works", result);
	}
}