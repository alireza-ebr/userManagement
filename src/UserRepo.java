import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean deleteById(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    public boolean updateUser(User updated) {
        for (User u : users) {
            if (u.getId() == updated.getId()) {
                u.setName(updated.getName());
                u.setAge(updated.getAge());
                return true;
            }
        }
        return false;
    }

    public User getById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public List<User> ListAll() {
        return new ArrayList<>(users);
    }
}
