import java.util.ArrayList;

public class Person {
    public String name;
    public String bio;
    private ArrayList <Post> peronPosts = new ArrayList<>();
    private ArrayList <Person> followers = new ArrayList<>();
    private ArrayList <Person> followings = new ArrayList<>();
    private ArrayList <chat> allPersonChats = new ArrayList<>();
    private ArrayList <Person> blockedUsers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Person> getBlockedUsers() {
        return blockedUsers;
    }

    public ArrayList<Post> getPeronPosts() {
        return peronPosts;
    }

    public Person(String name, String bio) {
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

    public boolean addPersonToBlockList(Person personToBlock){
        this.blockedUsers.add(personToBlock);
        return true;
    }
    public boolean removePersonFromBlockList(Person personToUnBlock){
        this.blockedUsers.remove(personToUnBlock);
        return true;
    }
    public ArrayList<Person> getFollowings() {
        return followings;
    }

    public boolean addPersonToFollowings(Person personToAddToFollowings){
        this.followings.add(personToAddToFollowings);
        return true;
    }
    public boolean addPersonToFollowers(Person personToAddToFollowers){
        this.followers.add(personToAddToFollowers);
        return true;
    }
    public boolean removePersonFromFollowers(Person personToRemoveFromFollowers){
        this.followers.remove(personToRemoveFromFollowers);
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
