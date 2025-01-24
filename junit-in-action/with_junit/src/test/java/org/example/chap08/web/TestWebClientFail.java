package org.example.chap08.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientFail {

	@Test
	public void testGetContentOk() throws Exception {
		MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
		MockInputStream mockStream = new MockInputStream();

		mockStream.setBuffer("It works");
		mockConnectionFactory.setData(mockStream);

		WebClient2 client = new WebClient2();
		String workingContent = client.getContent(mockConnectionFactory);

		assertEquals("It works", workingContent);
		mockStream.verify();
	}
}