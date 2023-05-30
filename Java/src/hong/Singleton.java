package hong;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Singleton {
    private ConcurrentHashMap<String, AtomicInteger> countMap = new ConcurrentHashMap<>();
    private Singleton(){
    }
    public static Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    public ConcurrentHashMap<String, AtomicInteger> getCountMap() {
        return countMap;
    }
}
