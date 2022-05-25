package mq.sub4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MQ {
//    private Queue<Object> messageQueue = new ConcurrentLinkedDeque<>();
    private Map<String, MessageQueue> queueMap = new ConcurrentHashMap<>();
    private MQ(){}
    public static MQ getInstance(){
        return LazyHolder.INSTANCE;
    }

    public boolean createQueue(String queueName, int queueSize, int processTimeout, int maxFailCount, int waitTime) {
        if(queueMap.containsKey(queueName)){
            System.out.println("Queue Exist");
            return false;
        }
        MessageQueue messageQueue = new MessageQueue(queueSize,queueName,processTimeout,maxFailCount,waitTime);
        queueMap.put(queueName,messageQueue);
        return true;
    }

    private static class LazyHolder {
        private static final MQ INSTANCE = new MQ();
    }
    public boolean createQueue(String name, int queueSize){
        if(queueMap.containsKey(name)){
            System.out.println("Queue Exist");
            return false;
        }
        MessageQueue messageQueue = new MessageQueue(queueSize,name);
        queueMap.put(name,messageQueue);
        return true;
    }
    public boolean sendQueue(String name ,Object message){
        return queueMap.get(name).sendQueue(message);
    }
    public Message receiveQueue(String name){
        return queueMap.get(name).receiveQueue();
    }
    public void failOver(String queueName, String messageId){
        queueMap.get(queueName).failOver(messageId);
    }
    public void ack(String queueName, String messageId){
        queueMap.get(queueName).ack(messageId);
    }
    public void processTimeOut(String queueName,String messageId){
        queueMap.get(queueName).processTimeout(messageId);
    }
}
