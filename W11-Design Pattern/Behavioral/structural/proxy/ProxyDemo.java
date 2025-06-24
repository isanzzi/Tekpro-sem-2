public class ProxyDemo {
    public static void main(String[] args) {
        UserService adminService = new UserServiceProxy("admin");
        adminService.deleteUser("ihsan"); // Output: User 'ihsan' has been deleted.

        UserService userService = new UserServiceProxy("user");
        userService.deleteUser("fauzi"); // Output: Access denied. Only admin can delete users.
    }
}