package mq.sub2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MessageQueue {
    private Queue<Object> messageQueue = new ConcurrentLinkedDeque<>();
    private int queueSize=0;

    public MessageQueue(int queueSize) {
        this.queueSize = queueSize;
    }
    public void sendQueue(Object object){
        if(messageQueue.size() >= queueSize){
            System.out.println("Queue Full");
        }
        else {
            messageQueue.add(object);
        }
    }
    public Object receiveQueue(){
        return messageQueue.poll();
    }
}
