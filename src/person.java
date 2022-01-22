import java.util.ArrayList;

public class person {
    public String name;
    public String bio;
    private ArrayList <Post> peronPosts = new ArrayList<>();
    private ArrayList <person> followers = new ArrayList<>();
    private ArrayList <person> followings = new ArrayList<>();
    private ArrayList <chat> allPersonChats = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPeronPosts() {
        return peronPosts;
    }

    public person(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
    public void showAllUserChats(){
        for (chat c:allPersonChats) {
            System.out.println(c);
        }
    }
    public chat getChatById(int idToGetChat){
        for (chat c:allPersonChats) {
            if(c.getId() == idToGetChat){
                return c;
            }
        }
        return null;
    }

    public ArrayList<person> getFollowings() {
        return followings;
    }

    public boolean addPersonToFollowings(person personToAddToFollowings){
        this.followings.add(personToAddToFollowings);
        return true;
    }
    public void addPostToUserPosts(Post newPost){
        peronPosts.add(newPost);
    }

    public void showAllPostsOfUser(){
        for (Post p:peronPosts) {
            System.out.println(p);
        }
    }


    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", bio=" + bio +
                '}';
    }
}
