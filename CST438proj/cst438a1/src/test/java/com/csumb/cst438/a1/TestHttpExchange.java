package com.csumb.cst438.a1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpPrincipal;

/**
 * Mockup for HttpExchange used in unit testing.
 * 
 * @author David Wisneski
 * @version 1.0	
 * 
 * Example:  starting a new game 
 * 
 * final String expectedBody = "<!DOCTYPE html><html><head><title>MyHttpServer</title></head>"+
 *    "<body><h2>Hangman</h2><img src=\"h1.gif\"><h2 style=\"font-family:'Lucida Console', monospace\">"+
 *    *    " _ _ _ _ _ </h2><form action=\"/\" method=\"get\">  Guess a character <input type=\"text\" name=\"guess\">"+
 *    "<br><input type=\"submit\" value=\"Submit\"></form></body></html>";
 * 
 * Headers header = new Headers();
 * TestHttpExchange t = new TestHttpExchange("/", header);
 * MyHttpServer.MyHandler handler = new MyHttpServer.MyHandler();
 * handler.handle(t);
 * // check response for cookie returned, response code=200, and expected response body 
 * Headers response = t.getResponseHeaders();
 * String cookie1 = response.getFirst("Set-cookie");
 * assertEquals("Bad content type", "text/html", response.getFirst("Content-type"));
 * assertNotNull("No cookie returned", cookie1);
 * assertEquals("Bad response code.",200, t.getResponseCode());
 * assertEquals("Bad response body.",expectedBody, t.getOstream().toString());
 * 
 * Example:  Testing a guess of character c 
 * 
 * Headers header = new Headers();
 * header.add("Cookie", cookie1);  // cookie from previous response
 * TestHttpExchange t = new TestHttpExchange("/?guess=c", header);
 * MyHttpServerSolution.MyHandler handler = new MyHttpServerSolution.MyHandler();
 * handler.handle(t);
 *  // check response for response code, content-type and response body 
 * Headers response = t.getResponseHeaders();
 * String cookie1 = response.getFirst("Set-cookie");
 * assertEquals("Bad content type", "text/html", response.getFirst("Content-type"));
 * assertEquals("Bad response code.",200, t.getResponseCode());
 * assertEquals("Bad response body.",expectedBody2, t.getOstream().toString());
 * 
 * test request to download gif file 
 * Headers header = new Headers();
 * TestHttpExchange t = new TestHttpExchange("/h1.gif", header);
 * MyHttpServerSolution.MyHandler handler = new MyHttpServerSolution.MyHandler();
 * handler.handle(t);
 * // check response for expect output
 * Headers response = t.getResponseHeaders();
 * assertEquals("Bad content type", "image/gif", response.getFirst("Content-type"));
 * assertEquals("Bad response code.",200, t.getResponseCode());
 * // check that length of response body is 8581 bytes. 
 * assertEquals("Bad response length.","8581", response.getFirst("Content-length"));
 * 
 * 
 */
public class TestHttpExchange extends com.sun.net.httpserver.HttpExchange {
	
	 URI uri;
	 Headers responseHeaders;
	 Headers requestHeaders;
	 int responseCode;
	 ByteArrayOutputStream ostream;
	 
	 // the following methods are added for testing convenience.  
	 // they are not overrides of methods in HttpExchange.
	 
	 public URI getUri() {
		return uri;
	}

	public ByteArrayOutputStream getOstream() {
		return ostream;
	}

	// constructor 
	
	public TestHttpExchange(String uriString, Headers requestHeaders) throws Exception {
		uri = new URI(uriString);
		this.requestHeaders = requestHeaders;
		responseHeaders = new Headers();
	}
	
	// the following are all overrides.
	// methods are not used by MyHandler throws UnsupportedOperationException
	// methods that are used, have mockup code.
	
	@Override
	public Headers getRequestHeaders() {
		// stub method for testing
		return requestHeaders;
	}

	@Override
	public URI getRequestURI() {
		// stub method for testing
		return uri;
	}

	@Override
	public OutputStream getResponseBody() {
		// stub method for testing
		ostream = new ByteArrayOutputStream();
		return ostream;
	}

	@Override
	public int getResponseCode() {
		// stub for testing
		return responseCode;
	}

	@Override
	public Headers getResponseHeaders() {
		// stub method for testing
		return responseHeaders;
	}

	@Override
	public void sendResponseHeaders(int arg0, long arg1) throws IOException {
		// stub method for testing
		responseCode = arg0;
		responseHeaders.add("Content-length",  Long.toString(arg1));
	}

	@Override
	public HttpContext getHttpContext() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InetSocketAddress getRemoteAddress() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public HttpPrincipal getPrincipal() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getProtocol() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getRequestMethod() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void close() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getAttribute(String arg0) {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setStreams(InputStream arg0, OutputStream arg1) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getRequestBody() {
		// required for compilation
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		throw new java.lang.UnsupportedOperationException();
	}
	
}
