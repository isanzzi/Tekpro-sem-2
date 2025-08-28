public class ECommerceSystem {
    public static void main(String[] args) {
        // Membuat berbagai jenis pengguna
        Customer customer = new Customer("Alice", "alice@mail.com");
        Seller seller = new Seller("Bob", "bob@store.com");
        Admin admin = new Admin("Charlie", "charlie@admin.com");
        PremiumCustomer premiumCustomer = new PremiumCustomer("Dave", "dave@premium.com");

        // Simulasi aktivitas pengguna
        customer.login();
        customer.buyProduct("Laptop", 1000);
        customer.logout();

        seller.login();
        seller.addProduct("Smartphone", 500);
        seller.manageOrders();
        seller.logout();

        admin.login();
        admin.moderateReviews();
        admin.logout();

        premiumCustomer.login();
        double finalPrice = premiumCustomer.getDiscount(1200);
        premiumCustomer.buyProduct("Gaming PC", finalPrice);
        premiumCustomer.accessVIPSupport();
        premiumCustomer.logout();
    }
}
