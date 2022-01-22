
import java.util.ArrayList;

public class group extends chat{
    private final String name;
    private ArrayList<person> members = new ArrayList<>();
    private person admin;

    public void setAdmin(person admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void addMemberToGroup(person personToAdd){
        members.add(personToAdd);
    }
    public group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group {" +
                ", name= " + name +
                ", admin" + admin +
                ", members= " + members +
                '}';
    }
}
