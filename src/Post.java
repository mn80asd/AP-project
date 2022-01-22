import java.util.HashMap;

public class Post {
    private static int helpIdPost=0;
    private final String name;
    private final String description;
    private int likeNumber;
    private HashMap<String,String> comments = new HashMap<>();
    private int postId;

    public Post(String name, String description) {
        this.name = name;
        this.description = description;
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
    public void addCommentToPost(Post p, String body, String personName){
        comments.put(personName,body);
    }

    @Override
    public String toString() {
        return "Post {" +
                "post id= "+ postId+
                ", name= " + name +
                ", description= " + description +
                ", like number= "+ likeNumber +
                ", comments= "+ comments+
                '}';
    }
}
