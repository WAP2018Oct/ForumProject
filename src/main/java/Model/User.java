package Model;

import java.util.ArrayList;
import java.util.List;
public class User {
    private String username;
    private String password;
    private boolean flag;
    private String firstname;
    private String lastname;
    private String role;
    private int id;
    private List<User> userdb= new ArrayList<>();


    public User(String uname, String pw, String firstname, String lastname, String role, int id){
        this.username = uname;
        this.password = pw;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.id = id;
        userdb.add(this);
        //this.role = role;
    }
    public User(String uname, String pw, boolean flag){
        this.username = uname;
        this.password = pw;
        this.flag = flag;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUserdb() {
        return userdb;
    }

    public void setUserdb(List<User> userdb) {
        this.userdb = userdb;
    }
}
