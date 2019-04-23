package blueharvest.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("message/new")
    public void writeMessage(@RequestBody Message message){
        messageService.addMessage(message);
    }

    @GetMapping("message/inbox")
    public List<Message> getInboxMessages(String toName){
        return messageService.getInboxMessages(toName);
    }
    @GetMapping("message/outbox")
    public List<Message> getOutboxMessages(String fromName){
        return messageService.getOutboxMessages(fromName);
    }
}
