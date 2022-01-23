import java.util.ArrayList;

public class Person {
    public String name;
    public String bio;
    public ArrayList<Post> personPosts = new ArrayList<>();
    public ArrayList<Person> followers = new ArrayList<>();
    public ArrayList<Person> followings = new ArrayList<>();
    public ArrayList<Chat> allPersonChats = new ArrayList<>();
    public ArrayList<Person> blockedUsers = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<Person> getBlockedUsers() {
        return blockedUsers;
    }

    public ArrayList<Post> getPeronPosts() {
        return personPosts;
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

    public boolean addPersonToBlockList(Person personToBlock){
        this.blockedUsers.add(personToBlock);
        return true;
    }
    public boolean removePersonFromBlockList(Person personToUnBlock){
        this.blockedUsers.remove(personToUnBlock);
        return true;
    }
    public ArrayList<Person> getFollowings()throws Exception {
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
        personPosts.add(newPost);
    }

    public void showAllPostsOfUser(){
        for (Post p:personPosts) {
            System.out.println(p);
        }
    }


    @Override
    public String toString() {
        return "Person: " +
                "\n     name=" + name +
                "\n         bio=" + bio +
                ' ';
    }
}
