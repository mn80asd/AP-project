import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Media {

    HashMap<String,String> user_pass_map = new HashMap<>();
    static Person current_user;
    public ArrayList<Person> all_people = new ArrayList<>();
    public ArrayList<Post> all_posts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static int help_id_chat = 1;
    private boolean the_app_is_running = true;

    public static void main(String[] args) throws Exception {
        Media my_insta = new Media();
        my_insta.start_person();
    }

    private void start_person() throws Exception{
        while (the_app_is_running){
            System.out.println();
            System.out.println("\tWelcome to PARADOX media! ❤️️\uD83C\uDF89");
            System.out.println("\tHope you have a good time here. \uD83E\uDDAD✨");
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("\t\t1.Login");
            System.out.println("\t\t2.Sign up");
            System.out.println("\t\t3.Exit");
            System.out.println("----------------------------------------");
            int login_or_signup = scanner.nextInt();
            switch (login_or_signup){
                case 1:
                    if (all_people.isEmpty()){
                        System.out.println("\tThere isn't any registered account. ⛔️");
                        continue;
                    }
                    else {
                        login_person();
                        break;
                    }
                case 2:
                    signup_person();
                    break;
                case 3:
                    System.out.println("\t\t☹️ BYE BYE ☹️");
                    the_app_is_running = false;
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    start_person();
                    break;
            }
            break;
        }
    }

    private void login_person()throws Exception{
        while (true) {
            scanner.nextLine();
            System.out.print("\tEnter your username(Enter 'cancel' if you are regretful): ");
            String username = scanner.nextLine();
            if(username.equals("cancel")){
                start_person();
                break;
            }
            if (user_pass_map.containsKey(username)){
                System.out.print("\tEnter your password: ");
                String password = scanner.nextLine();
                if(Objects.equals(password, user_pass_map.get(username))){
                    System.out.println("\tYou logged in successfully. ✅");
                    for (Person p:all_people) {
                        if(p.name.equals(username)){
                            current_user = p;
                            break;
                        }
                    }
                    main_menu();
                    break;
                }
                else
                    System.out.println("\tThis password is incorrect. ⛔");
            }
            else
                System.out.println("\tThis username is incorrect. ⛔");
        }
    }

    private void signup_person()throws Exception{
        while (true){
            System.out.print("\tEnter a username(Enter 'back' if you are regretful): ");
            scanner.nextLine();
            String username = scanner.nextLine();
            if (username.equals("back")){
                start_person();
                break;
            }
            if (!user_pass_map.containsKey(username)){
                System.out.print("\tEnter a password: ");
                String password = scanner.nextLine();
                user_pass_map.put(username, String.valueOf(password));
                System.out.println("\tEnter bio of your account: ");
                System.out.print("\t\t");
                String setBio = scanner.nextLine();
                Person newPerson = new Person(username,setBio);
                all_people.add(newPerson);
                System.out.println("\tYou signed up successfully. ✅");
                for (Person p:all_people) {
                    if(p.name.equals(username)){
                        current_user = p;
                        break;
                    }
                }
                main_menu();
                break;
            }
            else
                System.out.println("\tThis username is taken before, try something else. ⛔");
        }
    }

    private void main_menu()throws Exception{
        while (true){
            System.out.println("----------------------------------------");
            System.out.println("\tEnter the number of your chosen task.");
            System.out.println("\t\t1- Home Page \uD83C\uDFE0");
            System.out.println("\t\t2- User Page \uD83D\uDE4B\u200D");
            System.out.println("\t\t3- Message Page \uD83D\uDC8C");
            System.out.println("\t\t4- Log out \uD83D\uDC4B");
            System.out.println("----------------------------------------");

            int menu_chosen_task = scanner.nextInt();
            switch (menu_chosen_task){
                case 1:
                    home_page();
                    break;
                case 2:
                    user_page();
                    break;
                case 3:
                    chat_page();
                    break;
                case 4:
                    start_person();
                    return;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    break;
            }
        }
    }

    private void home_page()throws Exception{
        while (true){
            System.out.println("\tEnter the number of your chosen task.");
            System.out.println("\t\t1- Search for a Person page \uD83D\uDD0E");
            System.out.println("\t\t2- Like a post ❤");
            System.out.println("\t\t3- Comment on a post ☁️");
            System.out.println("\t\t4- Back");

            if (!current_user.getFollowings().isEmpty())
                show_last_posts();
            int home_page_chosen_task = scanner.nextInt();
            switch (home_page_chosen_task){
                case 1:
                    search_for_person_page();
                    break;
                case 2:
                    like_a_post();
                    break;
                case 3:
                    comment_on_a_post();
                    break;
                case 4:
                    main_menu();
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    return;
            }
        }
    }

    private void show_last_posts(){
        if (current_user.getFollowings().isEmpty())
            return;
        for (Person p:current_user.getFollowings())
            System.out.println(p.getPeronPosts().get(p.getPeronPosts().size()-1));
    }

    private void search_for_person_page()throws Exception{
        while (true){
            System.out.print("\tEnter username of your goal Person(or enter 'cancel' to stop searching): ");
            scanner.nextLine();
            String name_to_search = scanner.nextLine();
            if (name_to_search.equals("cancel")){
                home_page();
                break;
            }
            else {
                if(user_pass_map.containsKey(name_to_search)){
                    System.out.println("\tUser found. ✅");
                    for (Person p: all_people) {
                        if (p.name.equals(name_to_search)){
                            System.out.println(p);
                            if(this.is_followed(p) & this.is_blocked(p)){
                                System.out.println("\tYou have already followed this user. \uD83E\uDD28");
                                System.out.println("\t\t1- Unfollow");
                                System.out.println("\t\t2- Block");
                                int wanna_unfollow_or_block = scanner.nextInt();
                                switch (wanna_unfollow_or_block){
                                    case 1:
                                        unfollow_person(p);
                                        break;
                                    case 2:
                                        block_person(p);
                                        break;
                                    default:
                                        System.out.println("\tThere is not any option for your input. ⛔");
                                        break;
                                }
                                break;
                            }
                            else if(!this.is_followed(p) & this.is_blocked(p)){
                                System.out.println("\t\t1- Follow");
                                System.out.println("\t\t2- Block");
                                int wanna_follow_or_block = scanner.nextInt();
                                switch (wanna_follow_or_block){
                                    case 1:
                                        follow_person(p);
                                        break;
                                    case 2:
                                        block_person(p);
                                        break;
                                    default:
                                        System.out.println("\tThere is not any option for your input. ⛔");
                                        break;
                                }
                                break;
                            }
                            else {
                                System.out.println("\tYou have already followed this user. \uD83E\uDD28");
                                System.out.println("\tDo you want to unblock this user? \uD83D\uDE42");
                                System.out.println("\t\t1- Yes");
                                System.out.println("\t\t2- No");
                                int to_block = scanner.nextInt();
                                switch (to_block){
                                    case 1:
                                        unblock_person(p);
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("\tThere is not any option for your input. ⛔");
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                else
                    System.out.println("\tThis username doesn't exist! Try Again. ⛔");
            }
        }
        home_page();
    }

    private boolean is_followed(Person person_to_find){
        for (Person person_to_search_in_current_user_followings:current_user.getFollowings())
            if(person_to_find.equals(person_to_search_in_current_user_followings))
                return true;
        return false;
    }

    private boolean is_blocked(Person person_to_find){
        for (Person person_to_search_in_current_user_blocks:current_user.getBlockedUsers())
            if(person_to_find.equals(person_to_search_in_current_user_blocks))
                return false;
        return true;
    }

    private void follow_person(Person person_to_follow)throws Exception{
        if(current_user.addPersonToFollowings(person_to_follow) & person_to_follow.addPersonToFollowers(current_user)){
            System.out.println("\tThis Person added to your followings successfully. ✅");
            System.out.println(current_user.getFollowings());
        }
        else
            System.out.println("\tSomething went wrong. You can't add the goal user to your followings. ☹⛔️");
        home_page();
    }

    private void unfollow_person(Person person_to_unfollow)throws Exception{
        if(current_user.removePersonFromFollowings(person_to_unfollow) & person_to_unfollow.removePersonFromFollowers(current_user)){
            System.out.println("\tThis Person removed from your followings successfully. ✅");
            System.out.println(current_user.getFollowings());
            home_page();
        }
        else
            System.out.println("\tSomething went wrong. You can't remove the goal person from your followings. ☹⛔");
        home_page();
    }

    private void block_person(Person person_to_block)throws Exception{
        if(this.is_followed(person_to_block))
            unfollow_person(person_to_block);
        if (current_user.addPersonToBlockList(person_to_block))
            System.out.println("\tThis Person blocked successfully. ✅");
        else
            System.out.println("\tSomething went wrong. You can't block the goal person. ☹⛔");
        home_page();
    }

    private void unblock_person(Person person_to_unblock)throws Exception{
        if(current_user.removePersonFromBlockList(person_to_unblock)){
            System.out.println("\tThe Person unblocked successfully. ✅");
            home_page();
        }
        else
            System.out.println("\tSomething went wrong. Person is still on your block list. ☹⛔");
    }

    private void like_a_post()throws Exception{
        System.out.print("\tEnter id number of the post you want to like: ");
        int id_to_like = scanner.nextInt();
        Post p = get_post_by_id(id_to_like);
        if (p != null)
            p.addLikeToPost();
        home_page();
    }

    private void comment_on_a_post()throws Exception{
        System.out.print("\tEnter id of the post you want to leave comment on: ");
        int id_to_comment = scanner.nextInt();
        scanner.nextLine();
        for (Post p:all_posts)
            if(Objects.equals(get_post_by_id(id_to_comment), p)){
                System.out.print("\tEnter the text of your comment: ");
                String comment_text = scanner.nextLine();
                p.addCommentToPost(comment_text,current_user);
                System.out.println("\tYour comment successfully added. ✅");
                break;
            }
        home_page();
    }

    private Post get_post_by_id(int id){
        for (Post p:all_posts)
            if(id == p.getPostId())
                return p;
        return null;
    }

    private void user_page()throws Exception{
        show_user_posts_in_user_page(current_user);
        while (true){
            System.out.println("\t\t1- Create new post");
            System.out.println("\t\t2- Back" );
            int user_page_int = scanner.nextInt();
            switch (user_page_int){
                case 1:
                    create_new_post();
                    show_user_posts_in_user_page(current_user);
                    break;
                case 2:
                    main_menu();
                    return;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    break;
            }
        }
    }

    private void create_new_post()throws Exception{
        while (true){
            scanner.nextLine();
            System.out.print("\tEnter name of the post you want to create.(enter <back> to back): ");
            String new_post_name = scanner.nextLine();
            if(new_post_name.equals("back")){
                user_page();
                break;
            }
            System.out.println("\tEnter the type of media you want to post.");
            System.out.println("\t\t1- Image");
            System.out.println("\t\t2- Gif");
            System.out.println("\t\t3- Video");
            int type_of_media_int = scanner.nextInt();
            scanner.nextLine();
            TypeOfMedia type_of_media = null;
            boolean should_continue = false;
            switch (type_of_media_int){
                case 1:
                    type_of_media = TypeOfMedia.IMAGE;
                    break;
                case 2:
                    type_of_media = TypeOfMedia.GIF;
                    break;
                case 3:
                    type_of_media = TypeOfMedia.VIDEO;
                    break;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    System.out.println("\tTry again.");
                    should_continue = true;
                    break;
            }
            if (should_continue)
                continue;
            System.out.print("\tEnter a caption for the post that you want to publish.☺️: ");
            String new_post_description = scanner.nextLine();
            System.out.println(new_post_name);
            Post new_post = new Post(new_post_name,new_post_description,type_of_media);
            current_user.addPostToUserPosts(new_post);
            all_posts.add(new_post);
            user_page();
        }
    }

    private void show_user_posts_in_user_page(Person person){
        person.showAllPostsOfUser();
    }

    private void chat_page()throws Exception{

        while(true){
            System.out.println("----------------------------------------");
            System.out.println("\tEnter the number of your chosen task.");
            System.out.println("\t\t1- Start chat with a person");
            System.out.println("\t\t2- Select a chat");
            System.out.println("\t\t3- Back");
            System.out.println("----------------------------------------");

            int user_chats_int = scanner.nextInt();
            switch (user_chats_int){
                case 1:
                    start_chat_with_a_person();
                    break;
                case 2:
                    select_chat();
                    break;
                case 3:
                    main_menu();
                    return;
                default:
                    System.out.println("\tThere is not any option for your input. ⛔");
                    System.out.println("\tTry again.");
                    break;
            }
        }
    }

    private void start_chat_with_a_person()throws Exception{
        while (true){
            System.out.print("\tEnter username of who you want to start Chat with.(Enter <back> to back.): ");
            scanner.nextLine();
            String person_name_start_chat = scanner.nextLine();
            if(person_name_start_chat.equals("back")){
                chat_page();
                break;
            }
            else {
                if(user_pass_map.containsKey(person_name_start_chat)){
                    System.out.println("\tUser found. ✅");
                    for (Person reciever: all_people) {
                        if (reciever.name.equals(person_name_start_chat)){
                            System.out.println(reciever);
                            Chat new_chat_sender = new Chat(reciever,help_id_chat);
                            current_user.all_person_chats.add(new_chat_sender);
                            Chat new_chat_reciever = new Chat(current_user,help_id_chat);
                            reciever.all_person_chats.add(new_chat_reciever);
                            help_id_chat++;
                            show_chat_text(new_chat_sender);
                            write_message_in_chat(new_chat_sender,reciever,new_chat_reciever);
                        }
                    }
                    break;
                }
                else
                    System.out.println("\tThis username doesn't exist! Try Again. ⛔");
            }
        }
    }

    private void show_chat_text(Chat chat){
        chat.show_this_chat();
    }

    private void select_chat()throws Exception{
        show_all_users_chat_list(current_user);
        System.out.print("\tEnter the id number of the Chat that you want (back==0): ");
        int id_chat_int = scanner.nextInt();
        scanner.nextLine();
        if (id_chat_int == 0)
            chat_page();
        else {
            Chat selected_sender_chat = current_user.getChatById(id_chat_int);
            Chat selected_reciever_chat = selected_sender_chat.getReceiver().getChatById(id_chat_int);
            System.out.println(selected_sender_chat);
            write_message_in_chat(selected_sender_chat,selected_sender_chat.getReceiver(),selected_reciever_chat);
        }
    }

    private void show_all_users_chat_list(Person person){
        person.showAllUserChats();
    }

    private void write_message_in_chat(Chat sender_chat, Person reciever, Chat reciever_chat)throws Exception{
        System.out.println("\tWrite the text you want to send.(you can stop chatting by entering 'end'): ");
        while (true){
            String chat_text = scanner.nextLine();
            if(chat_text.equals("end")){
                show_chat_text(sender_chat);
                System.out.println("-------------------chat ended-----------------");
                chat_page();
                break;
            }
            sender_chat.add_message_to_chat("     "+current_user.getName()+" :   " + chat_text);
            reciever_chat.add_message_to_chat("     "+current_user.getName()+" :   "  + chat_text);
            System.out.println("sent");

        }
    }

}
