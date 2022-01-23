import java.util.ArrayList;

public class Comment {

    public int likes_num;
    public Person sender;
    public String com_text;
    public ArrayList<Comment> reply_comments;


    public Comment(Person sender,String com_text){
        setSender(sender);
        setCom_text(com_text);
        int initial_like_num = 0;
        setLikes_num(initial_like_num);
        setReply_comments(new ArrayList<>());
    }


    public String getCom_text() {
        return com_text;
    }

    public void setCom_text(String com_text) {
        this.com_text = com_text;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public ArrayList<Comment> getReply_comments() {
        return reply_comments;
    }

    public void setReply_comments(ArrayList<Comment> reply_comments) {
        this.reply_comments = reply_comments;
    }

    public int getLikes_num() {
        return likes_num;
    }

    public void setLikes_num(int likes_num) {
        this.likes_num = likes_num;
    }
}
