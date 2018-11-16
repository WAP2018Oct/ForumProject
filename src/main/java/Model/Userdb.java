package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Userdb {
    //    private List<User> userdbms = new ArrayList<User>();

    Map<Integer, User> userDb = new HashMap<>();

    {
        userDb.put(1, new User(1,"test","123", "John", "Doe", "user"));
//        userDb.put(2, new User(2,"test","123", "John", "Doe", "user"));
//        userDb.put(3, new User(3,"test","123", "John", "Doe", "user"));
    }

    public void addUser(User user) {
        userDb.put(user.getId(), user);
    }

    public void deleteUser(int userId) {
        userDb.remove(userId);
    }

    public void updateUser(User user) {
        userDb.put(user.getId(), user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userDb.values());
    }

    public User getUserById(int userId) {
        return userDb.get(userId);
    }

    public int genId() {
        return userDb.size() + 1;
    }
}
