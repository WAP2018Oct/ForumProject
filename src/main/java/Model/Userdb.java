package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Userdb {
    private static Map<Integer, User> userDb = new HashMap<>();
    private static int id = 3;

    static {
        userDb.put(1, new User(1, "test", "123", "John", "Doe", "Contributor"));
        userDb.put(2, new User(2, "user", "password", "UserFirstName", "UserLastName", "Contributor"));
        userDb.put(3, new User(3, "foo", "bar", "Foo", "Bar", "user"));
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

    public List<User> getAllUserss() {

        return new ArrayList<>(userDb.values());
    }
    public static List<User> getAllUsers() {

        return new ArrayList<>(userDb.values());
    }
/*    public static List<User> getAllUsersGson(){
        return new Gson().toJson(userDb.values());
    }*/

    public static User getUserById(int userId) {
        return userDb.get(userId);
    }

    public static int genId() {
        return ++id;
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
