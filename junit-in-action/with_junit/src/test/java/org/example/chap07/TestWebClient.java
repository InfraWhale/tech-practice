package org.example.chap07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {
	private WebClient client = new WebClient();

	@BeforeAll
	public static void setUp() throws  Exception{
		Server server = new Server(8081);
		Context contentOkContext = new Context(server, "/testGetContentOk");
		contentOkContext.setHandler(new TestGetContentOKHandler());

		server.setStopAtShutdown(true);
		server.start();
	}

	@AfterAll
	public static void tearDown() {
		// Jetty 서버 중지
	}


	@Test
	public void testGetContentOk() throws MalformedURLException {
		String workingContent = client.getContent(new URL(
				"http://localhost:8081/testGetContentOk"));
		assertEquals("It works", workingContent);
	}

	private static class TestGetContentOKHandler extends AbstractHandler {
		@Override
		public void handle(String target,
						   HttpServletRequest request,
						   HttpServletResponse response,
						   int dispatch) throws IOException, ServletException {
			OutputStream out = response.getOutputStream();
			ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
			writer.write("It works");
			writer.flush();
			response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
			writer.writeTo(out);
			out.flush();
		}
	}
}