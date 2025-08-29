class Admin extends User implements Moderatable {
    public Admin(String username, String email) {
        super(username, email);
    }

    @Override
    public void moderateReviews() {
        System.out.println(username + " sedang memoderasi ulasan.");
    }

    @Override
    void performAction() {
        System.out.println(username + " sedang mengawasi sistem e-commerce.");
    }
}