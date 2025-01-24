package org.example.chap08.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestableWebClient extends WebClient1 {
	private HttpURLConnection connection;

	public void setHttpUrlConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	// 원래 url에서 들고와야 하는것을 미리 세팅된 모의 객체를 건내주는 것으로 오버라이드 하였다.
	public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
		return this.connection;

	}
}
