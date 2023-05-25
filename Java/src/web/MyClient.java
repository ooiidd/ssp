package web;

import hong.GsonH;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import java.util.HashMap;

public class MyClient {
	public static void main(String[] args) throws Exception {
		class OutputRes{
			String a;
		}
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		String outputUrl = "http://localhost:8080";

		OutputRes outputRes = new OutputRes();
		ContentProvider con = new StringContentProvider(GsonH.toJson(outputRes));
		System.out.println(outputUrl);
		ContentResponse contentRes = httpClient.newRequest(outputUrl).method(HttpMethod.POST)
				.content(con,"utf-8")
				.send();

		httpClient.stop();

		System.out.println(contentRes.getContentAsString());

	}

}
