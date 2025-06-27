class UserServiceProxy implements UserService {
    private RealUserService realService;
    private String role;

    public UserServiceProxy(String role) {
        this.role = role;
        this.realService = new RealUserService();
    }

    public void deleteUser(String username) {
        if ("admin".equalsIgnoreCase(role)) {
            realService.deleteUser(username);
        } else {
            System.out.println("Access denied. Only admin can delete users.");
        }
    }
}