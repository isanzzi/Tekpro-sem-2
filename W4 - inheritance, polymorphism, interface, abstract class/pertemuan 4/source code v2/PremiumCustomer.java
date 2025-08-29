class PremiumCustomer extends Customer implements PremiumFeatures {
    public PremiumCustomer(String username, String email) {
        super(username, email);
    }

    @Override
    public double getDiscount(double price) {
        double discount = price * 0.15; // Diskon 15%
        System.out.println(username + " mendapatkan diskon $" + discount);
        return price - discount;
    }

    @Override
    public void accessVIPSupport() {
        System.out.println(username + " mengakses layanan pelanggan VIP.");
    }

    @Override
    void performAction() {
        System.out.println(username + " menikmati fitur eksklusif sebagai member premium.");
    }
}