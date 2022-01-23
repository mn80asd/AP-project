import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    private final TypeOfMedia type;
    private static int help_id_post = 0;
    private final String name;
    private final String description;
    private int likes_num;
    public HashMap<String,String> comments = new HashMap<>();
    private int postId;

    public Post(String name, String description, TypeOfMedia media_type) {
        this.name = name;
        this.description = description;
        this.type = media_type;
        this.postId = help_id_post++;

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public void addLikeToPost(){
        likes_num+=1;
    }
    public void addCommentToPost(Post p, String body,String personName){
        comments.put(personName,body);
    }
    @Override
    public String toString() {
        return "Post " +
                "\nid= "+ postId+
                "\n     name= " + name +
                "\n         type= " + type +
                "\n             description= " + description +
                "\n                 like number= "+ likes_num +
                "\n                     comments= "+ comments+
                ' ';
    }
}
