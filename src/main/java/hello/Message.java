package hello;

public class Message {

    private final long id;
    private String message;

    public Message(){
        this.id = -1;
        this.message = null;
    }

    public Message(long id, String message){
        this.id = id;
        this.message = message;
    }

    public long getId(){
        return id;
    }

    public String getMessage(){
        return message;
    }

    public void updateMessage(String message){
        this.message = message;
    }
}
