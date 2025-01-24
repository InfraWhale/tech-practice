package org.example.chap08.web;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestWebClientJMock {

	// 최신버젼에선 지원 안하는듯 하다. ClassImposteriser 없이 짜는 방법으로 생각해야 할듯
	@RegisterExtension
	Mockery context = new JUnit5Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void testGetContentOk() throws Exception {
		ConnectionFactory factory = context.mock(ConnectionFactory.class);
		InputStream mockStream = context.mock(InputStream.class);

		context.checking(new Expectations() {
			 {
				 oneOf(factory).getData();
				 will(returnValue(mockStream));

				 atLeast(1).of(mockStream).read();
				 will(onConsecutiveCalls(
						 returnValue(Integer.valueOf((byte) 'W')),
						 returnValue(Integer.valueOf((byte) 'o')),
						 returnValue(Integer.valueOf((byte) 'r')),
						 returnValue(Integer.valueOf((byte) 'k')),
						 returnValue(Integer.valueOf((byte) 's')),
						 returnValue(Integer.valueOf((byte) '!')),
						 returnValue(-1)
				 ));
				 oneOf(mockStream).close();
			 }
		});

		WebClient2 client = new WebClient2();
		String workingContent = client.getContent(factory);

		assertEquals("Works!", workingContent);
	}

	@Test
	public void testGetContentCannotCloseInputStream() throws Exception {
		ConnectionFactory factory = context.mock(ConnectionFactory.class);
		InputStream mockStream = context.mock(InputStream.class);

		context.checking(new Expectations() {
			 {
				 oneOf(factory).getData();
				 will(returnValue(mockStream));
				 oneOf(mockStream).read();
				 will(returnValue(-1));
				 oneOf(mockStream).close();
				 will(throwException(new IOException("cannot close")));
			 }
		});

		WebClient2 client = new WebClient2();
		String workingContent = client.getContent(factory);
		assertNull(workingContent);
	}
}