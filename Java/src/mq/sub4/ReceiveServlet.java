package mq.sub4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReceiveServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MQ mq = MQ.getInstance();
		String[] urlParam = req.getPathInfo().split("\\/");
		System.out.println(req.getPathInfo());
		String queueName = urlParam[1];
		Message message = mq.receiveQueue(queueName);
		res.setStatus(200);
		if(message != null)
			res.getWriter().write("{\"Result\":\"OK\",\"MessageID\":\""+message.messageId+"\",\"Message\":\""+message.message+"\"}");
		else{
			res.getWriter().write("{\"Result\":\"No Message\"}");
		}
	}
}
