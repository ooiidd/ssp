package sampleA.SUB3;

import java.util.List;

public class ProxyDto {
    public int port;
    List<PrefixUrl> routes;
    class PrefixUrl{
        public String pathPrefix;
        public String url;
    }
}
