import java.util.ArrayList;
import java.util.HashMap;

public class Post {
    private TypeOfMedia type;
    private static int helpIdPost=0;
    private final String name;
    private final String description;
    private int likeNumber;
    private HashMap<String,String> comments = new HashMap<>();
    private int postId;

    public Post(String name, String description, TypeOfMedia mediaType) {
        this.name = name;
        this.description = description;
        this.type = mediaType;
        this.postId= helpIdPost++;

    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public void addLikeToPost(){
        likeNumber+=1;
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
                "\n                 like number= "+ likeNumber +
                "\n                     comments= "+ comments+
                ' ';
    }
}
