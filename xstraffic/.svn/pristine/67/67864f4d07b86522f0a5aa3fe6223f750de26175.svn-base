package com.traffic.factory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpFactory {
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	// Since the Configuration has not yet been setted,
	// then an unconfigured client is returned.
	private static HttpClient client = new HttpClient(connectionManager);
	private static HttpFactory http = new HttpFactory();

	public static synchronized HttpClient getClient() {
		return client;
	}

	public HttpFactory() {
		 configureClient();
	}

	public static HttpFactory getInstance() {
		return http;
	}

	private void configureClient() {
		int maxThreadsTotal = 30;
		int maxThreadsPerHost = 3;
		// Set up an HTTPS socket factory that accepts self-signed certs.
		/*Protocol dummyhttps = new Protocol("https",
				new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", dummyhttps);*/

		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setConnectionTimeout(100 * 1000);
		params.setSoTimeout(100 * 1000);
		
		params.setMaxTotalConnections(maxThreadsTotal);
		
		if (maxThreadsTotal > maxThreadsPerHost) {

			params.setDefaultMaxConnectionsPerHost(maxThreadsPerHost);

		} else {

			params.setDefaultMaxConnectionsPerHost(maxThreadsTotal);

		}
		
		
		
		
	}
}