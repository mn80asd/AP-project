public class privateChat extends chat{

    private person reciever;

    public privateChat(person reciever) {
        this.reciever = reciever;
    }
    @Override
    public String toString() {
        return "Good{" +
                "receiver=" + reciever +
                '}';
    }
}

