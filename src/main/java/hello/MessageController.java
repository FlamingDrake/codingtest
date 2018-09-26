package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("message")
public class MessageController {

    private ArrayList<Message> messageList = new ArrayList<>();


    @GetMapping("/status/check")
    public String status() {
        return "Working as intended";
    }

    @GetMapping("/{messageId}")
    public Message getMessage(@PathVariable int messageId) {
        Message result = null;
        for (Message message :
                messageList) {
            if (messageId == message.getId())
                result = message;
        }
        if (result == null) {
            throw new MessageNotFoundException();
        }
        return result;
    }

    @GetMapping
    public ArrayList all() {
        return messageList;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message createMessage(@RequestParam("message") String message) {
        Message result = new Message(messageIdTicker(), message);
        messageList.add(result);
        return result;
    }


    @DeleteMapping("/{messageId}")
    public String deleteMessage(@PathVariable int messageId) {
        boolean found = false;
        for (Message message :
                messageList) {
            if (messageId == message.getId()) {
                messageList.remove(message);
                found = true;
                break;
            }
        }
        if (found) {
            return "Message was deleted";
        } else {
            throw new MessageNotFoundException();
        }
    }

    @PutMapping(path = "/{messageId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message updateMessage(@PathVariable int messageId, @RequestParam("message") String message) {
        boolean found = false;
        Message updatedMessage = null;
        for (Message tempMessage :
                messageList) {
            if (messageId == tempMessage.getId()) {
                tempMessage.updateMessage(message);
                found = true;
                updatedMessage = tempMessage;
            }
        }
        if (found) {
            return updatedMessage;
        } else {
            throw new MessageNotFoundException();
        }
    }

    private long messageIdTicker = 0;

    private long messageIdTicker() {
        messageIdTicker++;
        return messageIdTicker - 1;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class MessageNotFoundException extends RuntimeException {

    }
}