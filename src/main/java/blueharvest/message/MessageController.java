package blueharvest.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    private MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @PostMapping("message/new")
    public void writeMessage(@RequestBody Message message){
        messageDao.addMessage(message);
    }

    @GetMapping("message/inbox")
    public List<Message> getMessages(long id){
        return messageDao.getMessages(id);
    }
}
