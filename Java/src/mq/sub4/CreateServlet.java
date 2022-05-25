package mq.sub4;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MQ mq = MQ.getInstance();
		String[] urlParam = req.getPathInfo().split("\\/");
		System.out.println(req.getPathInfo());
		String queueName = urlParam[1];
		String body = Util.getBody(req);
		JsonObject jsonObject = (JsonObject)JsonParser.parseString(body);
		int queueSize = jsonObject.get("QueueSize").getAsInt();
		int processTimeout = jsonObject.get("ProcessTimeout").getAsInt();
		int maxFailCount = jsonObject.get("MaxFailCount").getAsInt();
		int waitTime = jsonObject.get("WaitTime").getAsInt();
		boolean isOk = mq.createQueue(queueName,queueSize,processTimeout,maxFailCount,waitTime);
		res.setStatus(200);
		if(isOk)
			res.getWriter().write("{\"Result\":\"OK\"}");
		else{
			res.getWriter().write("{\"Result\":\"Queue Exist\"}");
		}
	}
}
