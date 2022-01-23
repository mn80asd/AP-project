import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.*;

public class Media {
    HashMap<String,Integer> UserPassMap = new HashMap<>();
    HashMap<String,Integer> NamePassGroupMap = new HashMap<>();
    static Person currentUser;
    public ArrayList<Person> allPeople = new ArrayList<>();
    public ArrayList<Post> allPosts = new ArrayList<>();
    public static int helpId = 0;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Media myInsta = new Media();
        myInsta.beginPerson();
    }
    private void beginPerson(){
        while (true){
            System.out.println("Welcome to Instagram\nEnter 1 if you already have an account \nEnter 2 to SignUp\n");
            int loginOrSignUp = scanner.nextInt();

            if (loginOrSignUp == 1){
                loginPerson();
                break;
            }
            else if (loginOrSignUp == 2){
                signUpPerson();
                break;
            }
            else {
                System.out.println("you should enter a number from 1 to 2");
            }
        }
    }
    private void loginPerson(){
        while (true) {
            System.out.println("enter your username.\nEnter <logout> to logout");
            String userName = scanner.nextLine();
            if(userName.equals("logout")){
                beginPerson();
                break;
            }
            if (UserPassMap.containsKey(userName)){
                System.out.println("Enter your password.");
                int password = scanner.nextInt();
                if(password == UserPassMap.get(userName)){
                    System.out.println("you loggedIn successfully.");
                    for (Person p: allPeople) {
                        if(p.name.equals(userName)){
                            currentUser = p;
                        }

                    }
                    mainMenu();
                    break;
                }
                else {
                    System.out.println("this password is incorrect. try again.");
                }
            }
            else {
                System.out.println("this UserName is incorrect. try again.");
            }
        }
    }
    private void signUpPerson(){
        while (true){
            System.out.println("Enter your username.\nEnter <back> to back.");
            String fullName = scanner.nextLine();
            if (fullName.equals("back")){
                beginPerson();
                break;
            }
            if (!UserPassMap.containsKey(fullName)){
                System.out.println("Enter a password (contains just numbers)");
                int password = scanner.nextInt();
                scanner.nextLine();
                UserPassMap.put(fullName,password);

                System.out.println("Enter a biography you want to show others who see your page.");
                String setBio = scanner.nextLine();

                Person newPerson = new Person(fullName,setBio);
                allPeople.add(newPerson);
                System.out.println("you signedUp successfully.now you can login.");
                loginPerson();
                break;
            }
            else{
                System.out.println("This name is taken before. choose another name.");
            }
        }
    }
    private void mainMenu(){
        while (true){
            System.out.println("Enter the number of what you want. enter 0 to back.\n" +
                    "1- Home page\n" +
                    "2- My page\n" +
                    "3- My chats\n");
            int menoInt = scanner.nextInt();
            if (menoInt == 0){
                loginPerson();
                break;
            }
            else if (menoInt == 1){
                homePage();
                break;
            }
            else if (menoInt == 2){
                userPage();
                break;
            }
            else if (menoInt == 3){
                userChatsPage();
                break;
            }
            else{
                System.out.println("You should choose a number from 0 to 3");
            }
        }
    }
    private void homePage(){
        while (true){
            System.out.println("1- Search for a Person page\n" +
                    "2- Like a post\n" +
                    "3- Comment on a post\n" +
                    "0- back");
            showLastPosts();
            int homePageInt = scanner.nextInt();
            if(homePageInt == 0){
                mainMenu();
                break;
            }
            else if (homePageInt == 1){
                searchForAPersonPage();
                break;
            }
            else if (homePageInt == 2){
                likeAPost();
                break;
            }
            else if (homePageInt == 3){
                commentOnAPost();
                break;
            }
        }
    }
    private void showLastPosts(){
        for (Person p:currentUser.getFollowings()) {
            System.out.println(p.getPeronPosts().get(p.getPeronPosts().size()-1));
        }
    }
    private void searchForAPersonPage(){
        while (true){
            System.out.println("Enter the name of the Person you want.\n Enter <back> to back.");
            String nameToSearch = scanner.nextLine();
            if (nameToSearch.equals("back")){
                homePage();
                break;
            }
            else {
                if(UserPassMap.containsKey(nameToSearch)){
                    System.out.println("user found.");
                    for (Person p: allPeople) {
                        if (p.name.equals(nameToSearch)){
                            System.out.println(p);
                            if(this.isFollowed(p) & !this.isBlocked(p)){
                                System.out.println("----------you already followed this user.----------");
                                System.out.println("1- unfollow  2- block\n");
                                int wantToUnfollowOrBlock = scanner.nextInt();
                                if (wantToUnfollowOrBlock == 1){
                                    unfollowPerson(p);
                                    break;
                                }
                                else if (wantToUnfollowOrBlock == 2){
                                    blockPerson(p);
                                    break;
                                }
                                break;
                            }
                            else if(!this.isFollowed(p) & !this.isBlocked(p)){
                                System.out.println("1- follow  2- block\n");
                                int wantToFollowOrBlock = scanner.nextInt();
                                if(wantToFollowOrBlock == 1){
                                    followPerson(p);
                                    break;
                                } else if (wantToFollowOrBlock == 2) {
                                    blockPerson(p);
                                    break;
                                }
                                break;
                            }
                            else {
                                System.out.println("-------------This user is already blocked------------");
                                System.out.println("Do you want to unblock this user?" +
                                        "1- yes     2- no");
                                int toBlock = scanner.nextInt();
                                if(toBlock == 1){
                                    unBlockPerson(p);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                else {
                    System.out.println("this name doesn't exist. try again.");
                }
            }
        }
        homePage();
    }
    private boolean isFollowed(Person personToFind){
        for (Person personToSearchInCurrentUserFollowings:currentUser.getFollowings()) {
            if(personToFind.equals(personToSearchInCurrentUserFollowings)){
                return true;
            }
        }
        return false;
    }
    private boolean isBlocked(Person personToFind){
        for (Person personToSearchInCurrentUserBlockedList:currentUser.getBlockedUsers()) {
            if(personToFind.equals(personToSearchInCurrentUserBlockedList)){
                return true;
            }
        }
        return false;
    }
    private void followPerson(Person personToFollow){
        if(currentUser.addPersonToFollowings(personToFollow) & personToFollow.addPersonToFollowers(currentUser)){
            System.out.println("This Person added to your followings successfully.");
            System.out.println(currentUser.getFollowings());
        }
        else {
            System.out.println("something is wrong. Person is not added to your followings.");
        }
        homePage();
    }
    private void unfollowPerson(Person personToUnfollow){
        if(currentUser.removePersonFromFollowings(personToUnfollow) & personToUnfollow.removePersonFromFollowers(currentUser)){
            System.out.println("This Person removed from your followings successfully.");
            System.out.println(currentUser.getFollowings());
            homePage();
        }
        else{
            System.out.println("something is wrong. Person is not removed from your followings.");
        }
        homePage();
    }
    private void blockPerson(Person personToBlock){
        if(this.isFollowed(personToBlock)){
            unfollowPerson(personToBlock);
        }
        if (currentUser.addPersonToBlockList(personToBlock)){
            System.out.println("This Person blocked successfully.");
        }
        else{
            System.out.println("something is wrong. Person is not blocked.");
        }
        homePage();
    }
    private void unBlockPerson(Person personToUnBlock){
        if(currentUser.removePersonFromBlockList(personToUnBlock)){
            System.out.println("The Person un blocked successfully.");
            homePage();
        }
        else {
            System.out.println("something is wrong. Person is still in block list.");
        }
    }
    private void likeAPost(){
        System.out.println("Enter the id of the post you want to like.");
        int idToLike = scanner.nextInt();
        Post p = getPostById(idToLike);
        if (p != null) {
            p.addLikeToPost();
        }
        homePage();
    }

    private void commentOnAPost(){
        System.out.println("Enter the id of the post you want to leave comment on.");
        int idToComment = scanner.nextInt();
        scanner.nextLine();
        for (Post p:allPosts) {
            if(Objects.equals(getPostById(idToComment), p)){
                System.out.println("Enter the text of your comment.");
                String commentBody = scanner.nextLine();
                p.addCommentToPost(p,commentBody,currentUser.getName());
                System.out.println("comment successfully added.");
                break;
            }
        }
        homePage();

    }

    private Post getPostById(int id){
        for (Post p:allPosts) {
            if(id == p.getPostId()){
                return p;
            }
        }
        return null;
    }
    private void userPage(){
        showUserPostsInUserPage(currentUser);
        while (true){
            System.out.println("1- Creat new post\n" +
                    "0- back\n" );
            int userPageInt = scanner.nextInt();
            if(userPageInt == 0){
                mainMenu();
                break;
            }
            else if(userPageInt == 1){
                creatNewPost();
                showUserPostsInUserPage(currentUser);
                break;
            }
        }
    }
    private void creatNewPost(){
        TypeOfMedia typeOfMedia;
        while (true){

            System.out.println("Enter the name of the post you want to creat. enter <back> to back.");
            String newPostName = scanner.nextLine();
            if(newPostName.equals("back")){
                userPage();
                break;
            }
            System.out.println("enter the type of media you want to post.\n1- image  2- gif  3- video");
            int typeOfMediaInt = scanner.nextInt();
            scanner.nextLine();

            if(typeOfMediaInt == 1){
                typeOfMedia = TypeOfMedia.IMAGE;
            }
            else if(typeOfMediaInt == 2){
                typeOfMedia = TypeOfMedia.GIF;
            }
            else if(typeOfMediaInt == 3){
                typeOfMedia = TypeOfMedia.VIDEO;
            }
            else {
                System.out.println("you should enter a number from 1 to 3. please try again.");
                continue;
            }
            System.out.println("Enter the description of the post that you want to creat.");
            String newPostDescription = scanner.nextLine();
            Post newPost = new Post(newPostName,newPostDescription, typeOfMedia);
            currentUser.addPostToUserPosts(newPost);
            allPosts.add(newPost);
            userPage();
        }

    }
    private void showUserPostsInUserPage(Person person){
        person.showAllPostsOfUser();
    }
    private void userChatsPage(){
        showAllUserChatList(currentUser);
        while(true){
            System.out.println("1- Start Chat with a Person\n" +
                    "2- Select a Chat\n" +
                    "0- back");
            int userChatsInt = scanner.nextInt();
            if(userChatsInt == 0){
                mainMenu();
                break;
            }
            else if (userChatsInt == 1){
                startChatWithAPerson();
                break;
            }

            else if (userChatsInt == 2){
                selectChat();
                break;
            }
        }
    }
    private void startChatWithAPerson(){
        while (true){
            System.out.println("Enter the name of the Person you want to start Chat with. Enter <back> to back.");
            String personNameStartChat = scanner.nextLine();
            if(personNameStartChat.equals("back")){
                userChatsPage();
                break;
            }
            else {
                if(UserPassMap.containsKey(personNameStartChat)){
                    System.out.println("user found.");
                    for (Person p: allPeople) {
                        if (p.name.equals(personNameStartChat)){
                            System.out.println(p);
                            Chat newChat = new Chat(p);
                            showChatText(newChat);
                            writeMessageInChat(newChat);
                        }
                    }
                    break;
                }
                else {
                    System.out.println("this name doesn't exist. try again.");
                }
            }
        }
    }

    private void showChatText(Chat chat){
        chat.showThisChat();
    }

    private void selectChat(){
        showAllUserChatList(currentUser);
        System.out.println("Enter the id of the Chat you want to see.\n0- back\n");
        int idChatInt = scanner.nextInt();
        if ( idChatInt == 0){
            userChatsPage();
        }
        else {
            Chat selectedChat = currentUser.getChatById(idChatInt);
            System.out.println(selectedChat);
        }
    }

    private void showAllUserChatList(Person person){
        person.showAllUserChats();
    }
    private void writeMessageInChat(Chat chat){
        System.out.println("Write the text you want to send. if you want to stop chating, enter <end>.");
        while (true){
            String chatText = scanner.nextLine();
            if(chatText.equals("end")){
                userChatsPage();
                break;
            }
            chat.addMessageToChat(chatText);
            showChatText(chat);
        }
    }

}
