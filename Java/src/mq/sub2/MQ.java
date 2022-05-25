package mq.sub2;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MQ {
//    private Queue<Object> messageQueue = new ConcurrentLinkedDeque<>();
    private Map<String,MessageQueue> queueMap = new ConcurrentHashMap<>();
    private MQ(){}
    public static MQ getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder {
        private static final MQ INSTANCE = new MQ();
    }
    public void createQueue(String name, int queueSize){
        if(queueMap.containsKey(name)){
            System.out.println("Queue Exist");
            return;
        }
        MessageQueue messageQueue = new MessageQueue(queueSize);
        queueMap.put(name,messageQueue);
    }
    public void sendQueue(String name ,Object message){
        queueMap.get(name).sendQueue(message);
    }
    public Object receiveQueue(String name){
        return queueMap.get(name).receiveQueue();
    }
}
