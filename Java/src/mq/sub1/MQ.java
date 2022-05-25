package mq.sub1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MQ {
    private Queue<Object> messageQueue = new ConcurrentLinkedDeque<>();
    private Map<String,Object> queueMap = new ConcurrentHashMap<>();
    private MQ(){}
    public static MQ getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder {
        private static final MQ INSTANCE = new MQ();
    }
    public void sendQueue(Object object){
        messageQueue.add(object);
    }
    public Object receiveQueue(){
        return messageQueue.poll();
    }
}
