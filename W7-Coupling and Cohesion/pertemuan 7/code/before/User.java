abstract class User {
    protected String username;
    protected String email;
    protected double balance; // Informasi saldo yang seharusnya tidak bisa diakses Admin secara langsung

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

    abstract void performAction();
}