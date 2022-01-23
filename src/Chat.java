import java.util.ArrayList;

public  class Chat {

    private int id;
    public ArrayList<String> chatMessages =new ArrayList<>();
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
    public void addMessageToChat(String messageToAdd){
        chatMessages.add(messageToAdd);
    }
    public void showThisChat(){
        for (String s:chatMessages) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Chat :" +
                "\n     id= "+ id +
                "\n         Chat messages= " + chatMessages +
                ' ';
    }
}
