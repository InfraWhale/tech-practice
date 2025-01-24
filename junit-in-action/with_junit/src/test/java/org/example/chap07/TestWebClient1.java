package org.example.chap07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient1 {
	@BeforeAll
	public static void setUp() throws  Exception{
		URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
	}

	private static class StubStreamHandlerFactory implements URLStreamHandlerFactory {

		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpURLStreamHandler();
		}
	}

	private static class StubHttpURLStreamHandler extends URLStreamHandler {
		@Override
		protected URLConnection openConnection(URL url) {
			return new StubHttpURLConnection(url);
		}
	}

	@Test
	public void testGetContentOk() throws MalformedURLException {
		WebClient client = new WebClient();
		String workingContent = client.getContent(new URL("http://localhost/"));
		assertEquals("It works", workingContent);
	}
}