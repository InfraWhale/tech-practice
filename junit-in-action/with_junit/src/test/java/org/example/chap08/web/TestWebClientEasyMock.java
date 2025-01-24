package org.example.chap08.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// 2번방식
public class TestWebClientEasyMock {
	private ConnectionFactory factory;
	private InputStream stream;

	@BeforeEach
	public void setup() {
		factory = createMock("factory", ConnectionFactory.class);
		stream = createMock("stream", InputStream.class); // 원칙적으로 클래스는 모의가 안된다.
	}

	@Test
	public void testGetContentOk() throws Exception {
		expect(factory.getData()).andReturn(stream);
		expect(stream.read()).andReturn(Integer.valueOf((byte) 'W'));
		expect(stream.read()).andReturn(Integer.valueOf((byte) 'o'));
		expect(stream.read()).andReturn(Integer.valueOf((byte) 'r'));
		expect(stream.read()).andReturn(Integer.valueOf((byte) 'k'));
		expect(stream.read()).andReturn(Integer.valueOf((byte) 's'));
		expect(stream.read()).andReturn(Integer.valueOf((byte) '!'));
		expect(stream.read()).andReturn(-1);
		stream.close();

		replay(factory);
		replay(stream);

		WebClient2 client = new WebClient2();
		String workingContent = client.getContent(factory);

		assertEquals("Works!", workingContent);
	}

	@Test
	public void testGetContentCannotCloseInputStream() throws Exception {
		expect(factory.getData()).andReturn(stream);
		expect(stream.read()).andReturn(-1);
		stream.close();
		expectLastCall().andThrow(new IOException("cannot close"));

		replay(factory);
		replay(stream);

		WebClient2 client = new WebClient2();
		String workingContent = client.getContent(factory);

		assertNull(workingContent);
	}

	@AfterEach
	public void tearDown() {
		verify(factory);
		verify(stream);
	}
}