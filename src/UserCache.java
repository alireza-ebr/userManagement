import java.util.HashMap;
import java.util.Map;

public class UserCache {

    private final Map<Integer, User> userCache = new HashMap<>();
    private final UserRepo repo;

    public UserCache(UserRepo repo) {
        this.repo = repo;
    }

    public User getById(int id) {
        if (userCache.containsKey(id)) {
            return userCache.get(id);
        }
        User u = repo.getById(id);
        if (u != null) {
            userCache.put(id, u);
        }
        return u;
    }

    public void add(User user) {
        repo.addUser(user);
    }

    public boolean deleteById(int id) {
        boolean removed = repo.deleteById(id);
        if (removed){
            userCache.remove(id);
            return removed;
        }
        return false;
    }

    public boolean update(User updated) {
        boolean ok = repo.updateUser(updated);
        if (ok) {
            userCache.put(updated.getId(), updated);
            return true;
        }
        return ok;
    }

    public void printCache() {
        System.out.println("Cache kyes: " + userCache.keySet());
    }
}
