import java.util.ArrayList;

public  class Chat {

    private int id;
    public ArrayList<String> chat_messages =new ArrayList<>();
    public Person receiver;

    public Chat(Person receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void add_message_to_chat(String message_to_add){
        chat_messages.add(message_to_add);
    }
    public void show_this_chat(){
        for (String s:chat_messages) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Chat :" +
                "\n     id= "+ id +
                "\n         Chat messages= " + chat_messages +
                ' ';
    }
}
