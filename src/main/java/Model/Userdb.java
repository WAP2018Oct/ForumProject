package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Userdb {
    //    private List<User> userdbms = new ArrayList<User>();

    private static Map<Integer, User> userDb = new HashMap<>();

    static {
        userDb.put(1, new User(1,"test","123", "John", "Doe", "user"));
//        userDb.put(2, new User(2,"test","123", "John", "Doe", "user"));
//        userDb.put(3, new User(3,"test","123", "John", "Doe", "user"));
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

    public static int genId() {
        return userDb.size() + 1;
    }
}
