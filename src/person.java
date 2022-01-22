import java.util.ArrayList;

public class person {
    public String name;
    public String bio;
    private ArrayList <post> peronPosts = new ArrayList<>();
    private ArrayList <person> followers = new ArrayList<>();
    private ArrayList <person> followings = new ArrayList<>();
    private ArrayList <chat> allPersonChats = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<post> getPeronPosts() {
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
    public void addPostToUserPosts(post newPost){
        peronPosts.add(newPost);
    }

    public void showAllPostsOfUser(){
        for (post p:peronPosts) {
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
