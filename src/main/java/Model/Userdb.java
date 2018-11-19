package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Userdb {
    private static Map<Integer, User> userDb = new HashMap<>();
    private static int id = 3;

    static {

        userDb.put(1, new User(1, "test", "123", "Red", "Guy", "https://pm1.narvii.com/6153/5863e09305d25f18531c8ce556b8f70e7dd1632d_128.jpg", "Admin"));
        userDb.put(2, new User(2, "user", "password", "UserFirstName", "UserLastName", "https://pm1.narvii.com/6694/2f60d34edffed3e3512bb844a4f221f6704d3374_128.jpg", "Contributor"));
        userDb.put(3, new User(3, "foo", "bar", "Foo", "Bar", "", "user"));
    }

    public static void addUser(User user) {
        userDb.put(user.getId(), user);
    }

    public static void deleteUser(int userId) {
        userDb.remove(userId);
    }

    public static void updateUser(User user) {
        userDb.put(user.getId(), user);
    }

    public static List<User> getAllUsers() {

        return new ArrayList<>(userDb.values());
    }

    public static User getUserById(int userId) {
        return userDb.get(userId);
    }
/*    public static List<User> getAllUsersGson(){
        return new Gson().toJson(userDb.values());
    }*/

    public static int genId() {
        return ++id;
    }

    public List<User> getAllUserss() {

        return new ArrayList<>(userDb.values());
    }

    public int genUniqueId() {
        return userDb.size() + 1;
    }

    public void addUsers(User user) {
        userDb.put(user.getId(), user);
    }

    public void deleteUsers(int userId) {
        userDb.remove(userId);
    }

}
