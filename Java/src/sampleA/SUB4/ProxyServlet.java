package sampleA.SUB4;

import mq.FileReaderH;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ProxyServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
//                    System.out.println(headerName+" "+req.getHeader(headerName));
                }
            }
            File file = new File("log.txt");
            List<String> strList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            //현재 주소
//            sb.append("http://");
//            sb.append(req.getLocalAddr()).append(":").append(req.getLocalPort()).append(req.getRequestURI());
//            sb.append(" ");
//            if(req.getHeader("x-prev") != null){
//                //이전 요청되었던 주소
//                sb.append(req.getHeader("x-prev"));
//                sb.append(" ");
//                //다음 주소
//                sb.append(url).append(path);
//                sb.append(" ");
//            }
//            else{
//                sb.append(url).append(path);
//            }
//            request.header("x-prev",url+path);
//            System.out.println("log right : "+sb.append("\n").toString());
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


            request.header("x-requestTarget",url+path);
            Date date = new Date();
            int traceId = Objects.hashCode(date);;
            request.getHeaders().remove("x-traceId");
            request.header("x-traceId",String.valueOf(traceId));
            int depth = 1;
            if(req.getHeader("x-depth")!= null){
                depth = Integer.parseInt(req.getHeader("x-depth"))+1;
                request.getHeaders().remove("x-depth");
            }
            request.header("x-depth",String.valueOf(depth));
            System.out.println("------"+"http://"+req.getLocalAddr()+":"+req.getLocalPort()+req.getPathInfo()+" "+url+path+" "+traceId +" "+
                    req.getHeader("x-traceId") +" "+ req.getHeader("x-depth"));
            System.out.println("++++++"+req.getHeader("x-prev")+" "+req.getHeader("x-requestTarget")+" "+req.getHeader("x-traceId"));
            contentRes = request
                    .send();

            Iterator<HttpField> iterator = contentRes.getHeaders().stream().iterator();
//            for (Iterator<HttpField> it = iterator; it.hasNext(); ) {
//                HttpField cur = it.next();
////                System.out.println("iter : "+cur.getName()+" "+cur.getValue());
//                if(cur.getName().startsWith("x-")){
//                    res.setHeader(cur.getName(),cur.getValue());
//                }
//            }

            String requestId = contentRes.getHeaders().get("x-requestId");

            sb.append(requestId + " "+" " + contentRes.getStatus() +"\n");
//            sb.append("request : ").append(url).append(path).append(" ").append(contentRes.getStatus()).append("\n");
            strList.add(sb.toString());
            FileReaderH.writeAll("log.txt",strList,true);
            res.setStatus(contentRes.getStatus());

//            System.out.println(url+path+" "+contentRes.getMediaType()+" "+contentRes.getStatus()+" "+queryString);
            res.setContentType(contentRes.getMediaType());
            res.getWriter().write(contentRes.getContentAsString());
            httpClient.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}