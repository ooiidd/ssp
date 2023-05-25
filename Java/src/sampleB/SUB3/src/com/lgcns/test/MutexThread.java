package com.lgcns.test;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;

public class MutexThread extends Thread{
	static ReentrantLock lock = new ReentrantLock();
	
	private String threadName;
	Worker worker;
	String url;
	String outputUrl;
	public MutexThread(String name,String url,String outputUrl) {
		this.threadName = name;
		worker = new Worker(Integer.parseInt(name));
		this.url = url;
		this.outputUrl = outputUrl;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpClient httpClient = new HttpClient();
			try {
//				lock.lock();
				httpClient.start();
				ContentResponse contentRes = httpClient.newRequest(url).method(HttpMethod.GET)
						.send();
				String str = contentRes.getContentAsString();
				Gson gson = new Gson();
				InputRes input = gson.fromJson(str, InputRes.class);
				httpClient.stop();
				System.out.println(threadName + " " + input.timestamp+" "+input.value);
				PrintNums(input);
//				lock.unlock();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	void PrintNums(InputRes input) throws Exception {
		Queue<String> queue = SingletonQueue.getInstance().getQueue();
//		if(!queue.isEmpty()) {
//			String cur = str;
//			String[] split=cur.split(" ");
//			if(split[1].equals(this.threadName)){
				String ret = worker.run(input.timestamp,input.value);
				if(ret != null) {
					System.out.println(ret);
					HttpClient httpClient = new HttpClient();
					httpClient.start();
					Gson gson = new Gson();
					OutputRes outputRes = new OutputRes();
					outputRes.result = ret;
					
					
//					OutputRes outputRes = gson.fromJson(str, OutputRes.class);
					ContentProvider con = new StringContentProvider(gson.toJson(outputRes));
					System.out.println(outputUrl);
					ContentResponse contentRes = httpClient.newRequest(outputUrl).method(HttpMethod.POST)
							.content(con,"utf-8")
							.send();
					String retstr = contentRes.getContentAsString();
					httpClient.stop();
					//Http 응답으로 변경
				}
			}
//		}
//	}
}
