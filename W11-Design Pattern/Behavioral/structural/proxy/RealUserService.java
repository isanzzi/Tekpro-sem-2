class RealUserService implements UserService {
    public void deleteUser(String username) {
        System.out.println("User '" + username + "' has been deleted.");
    }
}