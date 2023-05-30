package hong;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterH {
    String name;
    int count=0;
    CounterH(String name,int count){
        this.name = name;
        this.count = count;
    }
    public void run() throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> single = Singleton.getInstance().getCountMap();
        single.computeIfAbsent(name,(a)->{
            System.out.println(Thread.currentThread().getName()+" not contains Key "+ name);
            return new AtomicInteger(0);
        });

        AtomicBoolean executeYn = new AtomicBoolean(false);
        while(!executeYn.get()) {
            single.computeIfPresent(name, (key, val) -> {
                if (val.get() >= count) {
                    System.out.println(Thread.currentThread().getName() + " not execute : count is max " + name);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    executeYn.set(true);
                    System.out.println(val.addAndGet(1));
                }
                return val;
            });
        }
        if(executeYn.get()){
            System.out.println(Thread.currentThread().getName()+" 수행 "+name);
            Thread.sleep(1000);
            single.computeIfPresent(name,(key,val)->{
                System.out.println(Thread.currentThread().getName()+" 끝 "+name);
                val.addAndGet(-1);
                return val;
            });
        }

    }
}
