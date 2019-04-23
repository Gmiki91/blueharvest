package blueharvest.message;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    public void addMessage(Message message){
        messageDao.addMessage(message);
    }

    public List<Message> getInboxMessages(String toName){
        return messageDao.getInboxMessages(toName);
    }
    public List<Message> getOutboxMessages(String fromName){
        return messageDao.getOutboxMessages(fromName);
    }
}
