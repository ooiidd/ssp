package mq.sub4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AckServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MQ mq = MQ.getInstance();
		String[] urlParam = req.getPathInfo().split("\\/");
		System.out.println(req.getPathInfo());
		String queueName = urlParam[1];
		String messageId = urlParam[2];
		mq.ack(queueName,messageId);
		res.setStatus(200);
//		if(isOk)
			res.getWriter().write("{\"Result\":\"OK\"}");
//		else{
//			res.getWriter().write("{\"Result\":\"Queue Exist\"}");
//		}
	}
}
