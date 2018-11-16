package Model;

import java.util.ArrayList;
import java.util.List;

public class Userdb {
    private List<User> userdbms = new ArrayList<User>();
    User user;
    public Userdb(){
        System.out.println("Man O M");
        //userdbms.add(new User("dbrown","123", "Dan","Brown","Admin",111));
        //userdbms.add(new User("rwallace","1234", "Randal","Wallace","Author",112));
    }
    Userdb(User users){
        this.user = users;
        System.out.println("Addong User "+users.getUsername());
        userdbms.add(this.user);
    }
    public List<User> getUserdbms() {

        return userdbms;
    }
}
