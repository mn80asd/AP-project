import java.util.ArrayList;

public class Comment {

    public int likes_num;
    public String username;
    public String com_text;
    public ArrayList<Comment> reply_comments;

    public Comment(String username,String com_text){
        setUsername(username);
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
