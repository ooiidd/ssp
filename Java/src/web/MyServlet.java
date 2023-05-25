package web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setStatus(200);

		System.out.println(req.getLocalPort());
		System.out.println(req.getMethod());//  GET POST ..
		System.out.println(req.getQueryString());//   test=test&test1=test1
		System.out.println(req.getRequestURI());//   /test/test
		System.out.println(req.getRequestURL());//   http://localhost:8080/test/test

		String body = (String)req.getReader().lines().collect(Collectors.joining());
		System.out.println(body);



		String queryString = req.getQueryString();
		if(queryString != null) {
			String[] split = queryString.split("\\&");
			for (String cur : split) {
				String[] split1 = cur.split("\\=");
				System.out.println((split1[0]+":"+ split1[1]));
			}
		}

		Enumeration<String> attributeNames = req.getAttributeNames();
		while(attributeNames.hasMoreElements()){
			System.out.println(attributeNames.nextElement());
		}

		/*Header 정보*/
		Enumeration<String> headerNames =  req.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headerName = ((String)headerNames.nextElement());
			if(headerName.startsWith("x-")) {
			}
		}


		res.getWriter().write("Hello World!");
	}
}
