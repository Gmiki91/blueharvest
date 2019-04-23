package blueharvest.message;

import java.time.LocalDateTime;

public class Message {
    private String to;
    private String from;
    private String subject;
    private String content;
    private LocalDateTime timeSent;

    public Message(String from, String to, String subject, String content, LocalDateTime timeSent) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;
        this.timeSent=timeSent;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
