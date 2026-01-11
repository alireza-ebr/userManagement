import java.util.List;
import java.util.Scanner;

public class UserManagement {

    private static final Scanner scanner = new Scanner(System.in);
    private static UserRepo repo = new UserRepo();
    private static UserCache cache = new UserCache(repo);

    public static void main(String[] args) {

        repo.addUser(new User(1, "Ali", 25));
        repo.addUser(new User(2, "Sara", 30));

        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addUser();
                case 2 -> deleteById();
                case 3 -> updateUser();
                case 4 -> viewUser();
                case 5 -> listUsers();
                case 6 -> showCache();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void printMenu() {
        System.out.println("***MENU***");
        System.out.println("1. Add User");
        System.out.println("2. Delete User");
        System.out.println("3. Update User");
        System.out.println("4. view User");
        System.out.println("5. List all users");
        System.out.println("6. Show cache");
        System.out.println("0. Exit");
        System.out.println("Enter your choice");
    }

    private static void addUser() {
        System.out.print("Enter ID: ");
        int addId = Integer.parseInt(UserManagement.scanner.nextLine());
        System.out.print("Enter name: ");
        String name = UserManagement.scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(UserManagement.scanner.nextLine());

        cache.add(new User(addId, name, age));
        System.out.println("User added successfully");
    }

    private static void deleteById() {
        System.out.print("Enter ID to delete: ");
        int delId = Integer.parseInt(UserManagement.scanner.nextLine());
        boolean delated = cache.deleteById(delId);
        System.out.println(delated ? "User deleted successfully" : "User not found");
    }

    private static void updateUser() {
        System.out.println("Enter ID to update: ");
        int updId = Integer.parseInt(UserManagement.scanner.nextLine());

        User existing = cache.getById(updId);
        if (existing == null) {
            System.out.println("User not found");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = UserManagement.scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = Integer.parseInt(UserManagement.scanner.nextLine());

        boolean ok = cache.update(new User(updId, newName, newAge));
        System.out.println(ok ? "User updated successfully" : "User not found");
    }

    private static void viewUser() {
        System.out.print("Enter ID to view: ");
        int viewId = Integer.parseInt(UserManagement.scanner.nextLine());
        User found = cache.getById(viewId);
        System.out.println(found == null ? "User not found" : found);
    }

    private static void listUsers() {
        List<User> list = repo.ListAll();
        list.forEach(System.out::println);

    }

    private static void showCache() {
        cache.printCache();
    }
}