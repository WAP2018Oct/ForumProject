package Model;

public class User {
    private String username;
    private String password;
    private boolean flag;
    private String firstname;
    private String lastname;
    private String role;
    private int id;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String role, String firstname){
        this.username=username;
        this.password=password;
        this.role=role;
        this.firstname=firstname;

        System.out.println("Working...");
    }
    public User(int id, String username, String password, String firstname, String lastname, String role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;

    }
    public User(String username, String password, String firstname, String lastname, String role) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;

    }

    public User(String userName, String password, boolean flag) {
        this.username = userName;
        this.password = password;
        this.flag = flag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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
}
