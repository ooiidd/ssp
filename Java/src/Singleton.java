import java.util.HashMap;
import java.util.Map;

public class Singleton {
    private Map<String,String> urlMap = new HashMap<>();
    private Singleton(){
    }
    public static Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    public Map<String, String> getUrlMap() {
        return urlMap;
    }
}
