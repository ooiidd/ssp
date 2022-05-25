package mq.sub4;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MessageQueue {
    private Deque<Message> messageQueue = new ConcurrentLinkedDeque<>();
    private int queueSize=0;
    private String queueName=null;
    private int id=0;
    private int processTimeOut=0;
    private int maxFailCount;
    private int waitTime;

    public MessageQueue(int queueSize,String queueName) {
        this.queueSize = queueSize;
        this.queueName = queueName;
    }

    public MessageQueue(int queueSize, String queueName, int processTimeout, int maxFailCount, int waitTime) {
        this.queueSize = queueSize;
        this.queueName = queueName;
        this.processTimeOut = processTimeout;
        this.maxFailCount = maxFailCount;
        this.waitTime = waitTime;
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
                cur.sendTime = LocalDateTime.now();
                ++id;
                if(processTimeOut > 0) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    MQ mq = MQ.getInstance();
                                    mq.processTimeOut(queueName,cur.messageId);
                                }
                            },
                            processTimeOut*1000
                    );
                }
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

    public void processTimeout(String messageId) {
        for( Message cur : messageQueue){
            if(cur.messageId != null && cur.messageId.equals(messageId)){
                cur.sendTime = null;
                cur.messageId = null;
                break;
            }
        }
    }
}
