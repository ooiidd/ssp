package mq.sub4;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class MyServer {

	public void start() throws Exception {
		Server server = new Server();
		ServerConnector http = new ServerConnector(server);
		http.setHost("127.0.0.1");
		http.setPort(8080);
		server.addConnector(http);
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(CreateServlet.class, "/CREATE/*");
		servletHandler.addServletWithMapping(SendServlet.class, "/SEND/*");
		servletHandler.addServletWithMapping(ReceiveServlet.class, "/RECEIVE/*");
		servletHandler.addServletWithMapping(FailServlet.class, "/FAIL/*");
		servletHandler.addServletWithMapping(AckServlet.class, "/ACK/*");
		server.setHandler(servletHandler);
		server.start();
		server.join();
	}
}
