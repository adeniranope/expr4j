package com.ericsson.expr4j.service.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ericsson.expr4j.core.constants.HttpMethod;
import com.ericsson.expr4j.service.JsonHttpService;
import com.ericsson.expr4j.service.JsonService;

/**
 * 
 * JSON Http Service Implementation to send JSON requests to URL
 * 
 * @author adeniranope
 *
 */
@Service(value = "jsonHttpService")
public class JsonHttpServiceBean implements JsonHttpService {

	@Autowired
	@Qualifier(value = "jsonService")
	JsonService jsonService;

	/**
	 * Initiate a SSL connection to the URL
	 * @return
	 * @throws Exception
	 */
	private static CloseableHttpClient registerHttps() throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true)
				.build();

		CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(new AllowAllHostnameVerifier()).build();

		return client;
	}

	/**
	 * 
	 * Send a json request to the URL, with a defined http method and protocol
	 * 
	 */
	@Override
	public String send(HttpMethod httpMethod, String url, Object request) throws Exception {
		CloseableHttpClient closeableHttpClient = null;
		if (url.startsWith("https")) {
			closeableHttpClient = registerHttps();
		} else {
			closeableHttpClient = HttpClients.createDefault();
		}

		HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = null;
		HttpRequestBase httpRequestBase = null;
		if (httpMethod.equals(HttpMethod.GET)) {
			httpRequestBase = new HttpGet(url);
		}
		if (httpMethod.equals(HttpMethod.DELETE)) {
			httpRequestBase = new HttpDelete(url);
		}
		if (httpMethod.equals(HttpMethod.POST)) {
			httpEntityEnclosingRequestBase = new HttpPost(url);
		}
		if (httpMethod.equals(HttpMethod.PUT)) {
			httpEntityEnclosingRequestBase = new HttpPut(url);
		}
		if (httpMethod.equals(HttpMethod.TRACE)) {
			httpRequestBase = new HttpTrace();
		}

		CloseableHttpResponse closeableHttpResponse = null;
		if (httpEntityEnclosingRequestBase != null) {
			if (request.toString().length() > 0) {
				StringEntity entity = new StringEntity(jsonService.toJson(request));
				entity.setContentType("application/json");
				httpEntityEnclosingRequestBase.setEntity(entity);
			}
			closeableHttpResponse = closeableHttpClient.execute(httpEntityEnclosingRequestBase);
		}
		if (httpRequestBase != null) {
			closeableHttpResponse = closeableHttpClient.execute(httpRequestBase);
		}

		if (closeableHttpResponse != null) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(closeableHttpResponse.getEntity().getContent(),Charset.forName("UTF-8")));

			StringBuilder stringBuilder = new StringBuilder();
			String response = null;
			while ((response = br.readLine()) != null) {
				stringBuilder.append(response);
			}
			br.close();
			closeableHttpClient.getConnectionManager().shutdown();

			if (stringBuilder.toString().length() > 0) {
				return stringBuilder.toString();
			}
		}
		return null;
	}

}
