package sampleA.SUB4;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import mq.FileReaderH;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RunManager {

	public static void main(String[] args) throws Exception {
		System.out.println(args[0]);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		JsonElement jsonElement = JsonParser.parseString(FileReaderH.readAllString(args[0]));

		Gson gson = new Gson();
		ProxyDto proxyDto = gson.fromJson(FileReaderH.readAllString( args[0]), ProxyDto.class);

		Singleton singleton = Singleton.getInstance();
		for(ProxyDto.PrefixUrl prefixUrl : proxyDto.routes){
			singleton.getUrlMap().put(prefixUrl.pathPrefix, prefixUrl.url);
		}
		File file = new File("log.txt");
		List<String> arr = new ArrayList<>();
		FileReaderH.writeAll("log.txt",arr,false);


		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(proxyDto.port);
//		http.setPort(5004);
		server.addConnector(http);
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(ProxyServlet.class, "/*");
		server.setHandler(servletHandler);
		server.start();
		server.join();
	}

}
