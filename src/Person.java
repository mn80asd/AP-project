import java.util.ArrayList;

public class Person {
    public String name;
    public String bio;
    private ArrayList <Post> peronPosts = new ArrayList<>();
    private ArrayList <Person> followers = new ArrayList<>();
    private ArrayList <Person> followings = new ArrayList<>();
    private ArrayList <Chat> allPersonChats = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPeronPosts() {
        return peronPosts;
    }

    public Person(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
    public void showAllUserChats(){
        for (Chat c:allPersonChats) {
            System.out.println(c);
        }
    }
    public Chat getChatById(int idToGetChat){
        for (Chat c:allPersonChats) {
            if(c.getId() == idToGetChat){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Person> getFollowings() {
        return followings;
    }

    public boolean addPersonToFollowings(Person personToAddToFollowings){
        this.followings.add(personToAddToFollowings);
        return true;
    }
    public boolean removePersonFromFollowings(Person personToRemoveFromFollowings){
        this.followings.remove(personToRemoveFromFollowings);
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
