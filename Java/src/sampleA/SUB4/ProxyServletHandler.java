package sampleA.SUB4;

import org.eclipse.jetty.servlet.ServletHandler;

public class ProxyServletHandler extends ServletHandler {
    public String url;
    public ProxyServletHandler(String url){
        super();
        this.url = url;
    }
}
