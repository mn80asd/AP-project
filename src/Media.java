import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Media {
    HashMap<String,String> UserPassMap = new HashMap<>();
    HashMap<String,Integer> NamePassGroupMap = new HashMap<>();
    static Person currentUser;
    public ArrayList<Person> allPeople = new ArrayList<>();
    public ArrayList<Post> allPosts = new ArrayList<>();
    public static int helpId = 0;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        Media myInsta = new Media();
        myInsta.beginPerson();
    }
    private void beginPerson() throws Exception{
        while (true){
            System.out.println();
            System.out.println("\tWelcome to PARADOX media! ❤️️\uD83C\uDF89");
            System.out.println("\tHope you have a good time here. \uD83E\uDDAD✨");
            System.out.println();
            System.out.println("\t\t1.Login");
            System.out.println("\t\t2.Sign up");
            System.out.println("\t\t3.Exit");
            int loginOrSignUp = scanner.nextInt();
            switch (loginOrSignUp){
                case 1:
                    if (allPeople.isEmpty()){
                        System.out.println("\tThere isn't any registered account. ⛔️");
                        continue;
                    }
                    else {
                        loginPerson();
                        break;
                    }
                case 2:
                    signUpPerson();
                    break;
                case 3:
                    System.out.println("\t\t☹️ BYE BYE ☹️");
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    beginPerson();
            }
            break;
        }
    }
    private void loginPerson()throws Exception{
        while (true) {
            System.out.print("\tEnter your username(Enter 'cancel' if you are regretful): ");
            String username = scanner.nextLine();
            if(username.equals("cancel")){
                beginPerson();
                break;
            }
            if (UserPassMap.containsKey(username)){
                System.out.print("\tEnter your password: ");
                String password = scanner.nextLine();
                if(Objects.equals(password, UserPassMap.get(username))){
                    System.out.println("\tYou logged in successfully. ✅");
                    for (Person p:allPeople) {
                        if(p.name.equals(username)){
                            currentUser = p;
                            break;
                        }
                    }
                    mainMenu();
                    break;
                }
                else {
                    System.out.println("\tThis password is incorrect. ⛔");
                }
            }
            else {
                System.out.println("\tThis username is incorrect. ⛔");
            }
        }
    }
    private void signUpPerson()throws Exception{
        while (true){
            System.out.print("\tEnter a username(Enter 'back' if you are regretful): ");
            String temp0 = scanner.nextLine();
            String username = scanner.nextLine();
            if (username.equals("back")){
                beginPerson();
                break;
            }
            if (!UserPassMap.containsKey(username)){
                System.out.print("\tEnter a password: ");
                String password = scanner.nextLine();
                UserPassMap.put(username, String.valueOf(password));

                System.out.println("\tEnter bio of your account: ");
                System.out.print("\t\t");
                String setBio = scanner.nextLine();

                Person newPerson = new Person(username,setBio);
                allPeople.add(newPerson);
                System.out.println("\tYou signed up successfully. ✅");
                for (Person p:allPeople) {
                    if(p.name.equals(username)){
                        currentUser = p;
                        break;
                    }
                }
                mainMenu();
                break;
            }
            else{
                System.out.println("\tThis username is taken before, try something else. ⛔");
            }
        }
    }
    private void mainMenu()throws Exception{
        while (true){
            System.out.println("\tEnter the number of your chosen task.");
            System.out.println("\t\t1- Home Page \uD83C\uDFE0");
            System.out.println("\t\t2- User Page \uD83D\uDE4B\u200D");
            System.out.println("\t\t3- Message Page \uD83D\uDC8C");
            System.out.println("\t\t4- Log out \uD83D\uDC4B");

            int menu_chosen_task = scanner.nextInt();
            switch (menu_chosen_task){
                case 1:
                    homePage();
                    break;
                case 2:
                    userPage();
                    break;
                case 3:
                    userChatsPage();
                    break;
                case 4:
                    beginPerson();
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    break;
            }
        }
    }
    private void homePage()throws Exception{
        while (true){
            System.out.println("\tEnter the number of your chosen task.");
            System.out.println("\t\t1- Search for a Person page \uD83D\uDD0E");
            System.out.println("\t\t2- Like a post ❤");
            System.out.println("\t\t3- Comment on a post ☁️");
            System.out.println("\t\t4- Back");

            if (!currentUser.getFollowings().isEmpty()) {
                showLastPosts();
            }
            int homePageInt = scanner.nextInt();
            switch (homePageInt){
                case 1:
                    searchForAPersonPage();
                    break;
                case 2:
                    likeAPost();
                    break;
                case 3:
                    commentOnAPost();
                    break;
                case 4:
                    mainMenu();
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    return;
            }
        }
    }
    private void showLastPosts()throws Exception{
        if (currentUser.getFollowings().isEmpty()){
            return;
        }
        for (Person p:currentUser.getFollowings()) {
            System.out.println(p.getPeronPosts().get(p.getPeronPosts().size()-1));
        }
    }
    private void searchForAPersonPage()throws Exception{
        while (true){
            System.out.print("\tEnter username of your goal Person(or enter 'cancel' to stop searching): ");
            String nameToSearch = scanner.nextLine();
            if (nameToSearch.equals("cancel")){
                homePage();
                break;
            }
            else {
                if(UserPassMap.containsKey(nameToSearch)){
                    System.out.println("\tUser found. ✅");
                    for (Person p: allPeople) {
                        if (p.name.equals(nameToSearch)){
                            System.out.println(p);
                            if(this.isFollowed(p) & this.isBlocked(p)){
                                System.out.println("\tYou have already followed this user. \uD83E\uDD28");
                                System.out.println("\t\t1- Unfollow");
                                System.out.println("\t\t2- Block");
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
                            else if(!this.isFollowed(p) & this.isBlocked(p)){
                                System.out.println("\t\t1- Follow");
                                System.out.println("\t\t2- Block");
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
                                System.out.println("\tYou have already followed this user. \uD83E\uDD28");
                                System.out.println("\tDo you want to unblock this user? \uD83D\uDE42");
                                System.out.println("\t\t1- Yes");
                                System.out.println("\t\t2- No");
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
                    System.out.println("\tThis username doesn't exist! Try Again. ⛔");
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
                return false;
            }
        }
        return true;
    }
    private void followPerson(Person personToFollow)throws Exception{
        if(currentUser.addPersonToFollowings(personToFollow) & personToFollow.addPersonToFollowers(currentUser)){
            System.out.println("\tThis Person added to your followings successfully. ✅");
            System.out.println(currentUser.getFollowings());
        }
        else {
            System.out.println("something is wrong. Person is not added to your followings.");
        }
        homePage();
    }
    private void unfollowPerson(Person personToUnfollow)throws Exception{
        if(currentUser.removePersonFromFollowings(personToUnfollow) & personToUnfollow.removePersonFromFollowers(currentUser)){
            System.out.println("\tThis Person removed from your followings successfully. ✅");
            System.out.println(currentUser.getFollowings());
            homePage();
        }
        else{
            System.out.println("something is wrong. Person is not removed from your followings.");
        }
        homePage();
    }
    private void blockPerson(Person personToBlock)throws Exception{
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
    private void unBlockPerson(Person personToUnBlock)throws Exception{
        if(currentUser.removePersonFromBlockList(personToUnBlock)){
            System.out.println("The Person un blocked successfully.");
            homePage();
        }
        else {
            System.out.println("something went wrong. Person is still in block list.");
        }
    }
    private void likeAPost()throws Exception{
        System.out.println("Enter the id of the post you want to like.");
        int idToLike = scanner.nextInt();
        Post p = getPostById(idToLike);
        if (p != null) {
            p.addLikeToPost();
        }
        homePage();
    }

    private void commentOnAPost()throws Exception{
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
    private void userPage()throws Exception{
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
    private void creatNewPost()throws Exception{
        TypeOfMedia typeOfMedia;
        while (true){
            scanner.nextLine();
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
            System.out.println(newPostName);
            Post newPost = new Post(newPostName,newPostDescription, typeOfMedia);
            currentUser.addPostToUserPosts(newPost);
            allPosts.add(newPost);
            userPage();
        }

    }

    private void showUserPostsInUserPage(Person person){
        person.showAllPostsOfUser();
    }

    private void userChatsPage()throws Exception{
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
    private void startChatWithAPerson()throws Exception{
        while (true){
            System.out.print("\tEnter username of who you want to start Chat with.(Enter <back> to back.): ");
            String personNameStartChat = scanner.nextLine();
            if(personNameStartChat.equals("back")){
                userChatsPage();
                break;
            }
            else {
                if(UserPassMap.containsKey(personNameStartChat)){
                    System.out.println("\tUser found. ✅");
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
                    System.out.println("\tThis username doesn't exist! Try Again. ⛔");
                }
            }
        }
    }

    private void showChatText(Chat chat){
        chat.showThisChat();
    }

    private void selectChat()throws Exception{
        showAllUserChatList(currentUser);
        System.out.print("\tEnter the id number of the Chat that you want (back==0): ");
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
    private void writeMessageInChat(Chat chat)throws Exception{
        System.out.println("\tWrite the text you want to send.(you can stop chatting by entering 'end'): ");
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
