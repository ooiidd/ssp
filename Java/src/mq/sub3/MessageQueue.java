package mq.sub3;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MessageQueue {
    private Deque<Message> messageQueue = new ConcurrentLinkedDeque<>();
    private int queueSize=0;
    private String queueName=null;
    private int id=0;

    public MessageQueue(int queueSize,String queueName) {
        this.queueSize = queueSize;
        this.queueName = queueName;
    }
    public boolean sendQueue(Object object){
        if(messageQueue.size() >= queueSize){
            System.out.println("Queue Full");
            return false;
        }
        else {
            Message message = new Message((String)object);
            messageQueue.add(message);
            return true;
        }
    }
    public Message receiveQueue(){
        for( Message cur : messageQueue){
            if(cur.messageId ==null){
                cur.messageId=queueName+id;
                ++id;
                return cur;
            }
        }
        return null;
    }
    public void failOver(String messageId){
        for( Message cur : messageQueue){
            if(cur.messageId != null && cur.messageId.equals(messageId)){
                cur.messageId=null;
                break;
            }
        }
    }

    public void ack(String messageId) {
        for( Message cur : messageQueue){
            if(cur.messageId != null && cur.messageId.equals(messageId)){
                messageQueue.remove(cur);
                break;
            }
        }
    }
}
