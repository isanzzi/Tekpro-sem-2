abstract class User {
    protected String username;
    protected String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void login() {
        System.out.println(username + " telah login.");
    }

    public void logout() {
        System.out.println(username + " telah logout.");
    }

    // Method ini di-override oleh subclass
    abstract void performAction();
}