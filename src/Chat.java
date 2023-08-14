import java.util.ArrayList;

public  class Chat {

    private int id;
    public ArrayList<String> Chat_messages =new ArrayList<>();
    public Person receiver;

    public Chat(Person receiver,int id) {
        this.receiver = receiver;
        this.id = id;
    }

    public Person getReceiver() {
        return receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void add_message_to_Chat(String message_to_add){
        Chat_messages.add(message_to_add);
    }
    public void show_this_Chat(){
        for (String s:Chat_messages) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Chat :" +
                "\n     id= "+ id +
                "\n         Chat messages= " + Chat_messages +
                ' ';
    }
}
