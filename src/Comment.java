import java.util.ArrayList;

public class Comment {

    public int likes_num ;
    public Person sender;
    public String com_text;
    public ArrayList <Comment> reply_comments;


    public Comment(Person sender, String comment_body) {
        this.sender = sender;
        this.com_text = comment_body;
        this.likes_num = 0;
    }

    public void addLikeToComment(){
        likes_num+=1;
    }
}
