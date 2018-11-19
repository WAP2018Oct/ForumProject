package Model;

public class User {
    private String username;
    private String password;
    private boolean flag;
    private String firstName;
    private String lastName;
    private String avatarLink;
    private String role;
    private int id;

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public User(){

    }
    public User(String username, String password, String firstName, String lastName, String role) {
        super();
        System.out.println("Checking");
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(int id, String username, String password, String firstName, String lastName, String avatarLink, String role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarLink = avatarLink;
        this.role = role;
    }
    public User(int id, String username, String password, String firstName, String lastName, String role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
