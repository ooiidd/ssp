package mq.sub4;

import java.time.LocalDateTime;

public class Message {
    public String message;
    public String messageId=null;
    public LocalDateTime sendTime;
    public int failCount = 0;

    public Message(String message) {
        this.message = message;
    }

}
