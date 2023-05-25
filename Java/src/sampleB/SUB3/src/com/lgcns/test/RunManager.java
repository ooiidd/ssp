package com.lgcns.test;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;

public class RunManager {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		ContentResponse contentRes = httpClient.newRequest("http://127.0.0.1:8080/queueInfo").method(HttpMethod.GET)
				.send();
		String str = contentRes.getContentAsString();
		Gson gson = new Gson();

		ServerInfoDto serverInfo = gson.fromJson(str, ServerInfoDto.class);
		for(int i=0;i<serverInfo.inputQueueURIs.size();i++) {
			MutexThread thread1 = new MutexThread(String.valueOf(i),serverInfo.inputQueueURIs.get(i),
					serverInfo.outputQueueURI);
			thread1.start();
		}
	}

}
