package sampleA.SUB3;

import mq.FileReaderH;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ProxyServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpClient httpClient = new HttpClient();
        String path = req.getPathInfo();

        ContentResponse contentRes = null;
        Singleton singleton = Singleton.getInstance();
        try {
            httpClient.start();
            String[] pathSplit = path.split("\\/");
            if(pathSplit[1].equals("trace")){

                return;
            }
            String url = singleton.getUrlMap().get("/"+pathSplit[1]);

            System.out.println(path);

            Request request = httpClient.newRequest(url + path).method(req.getMethod());
            Enumeration headerNames =  req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String headerName = ((String)headerNames.nextElement());
                if(headerName.startsWith("x-")) {
                    request.header(headerName, req.getHeader(headerName));
                }
            }
            File file = new File("log.txt");
            List<String> strList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            if(req.getHeader("x-prev") != null){
                sb.append(req.getHeader("x-prev"));
                sb.append(" ");
                sb.append(url).append(path);
                sb.append(" ");
            }
            else{
                sb.append(url).append(path);
            }
            request.header("x-prev",url+path);
            strList.add(sb.toString());
            FileReaderH.writeAll("log.txt",strList,false);
            System.out.println("log right : "+sb.toString());
            String queryString = req.getQueryString();

//            Set<String> keys = parameterMap.keySet();
//            for(String key : keys){
//                request.param(key,parameterMap.get(key));
//            }
            if(queryString != null) {
                String[] split = queryString.split("\\&");
                for (String cur : split) {
                    String[] split1 = cur.split("\\=");
                    request.param(split1[0], split1[1]);
                }
            }


            contentRes = request
                    .send();
            res.setStatus(contentRes.getStatus());

            System.out.println(url+path+" "+contentRes.getMediaType()+" "+contentRes.getStatus()+" "+queryString);
            res.setContentType(contentRes.getMediaType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        res.getWriter().write(contentRes.getContentAsString());
    }
}