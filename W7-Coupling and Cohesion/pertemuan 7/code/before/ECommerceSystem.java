import java.util.*;
public class ECommerceSystem {
    public static void main(String[] args) {
        Admin admin = new Admin("Charlie", "charlie@admin.com");
        Customer customer = new Customer("Alice", "alice@mail.com");
        Seller seller = new Seller("Bob", "bob@store.com", 1000,admin);

        customer.login();
        customer.buyProduct("Laptop", 1000);
        customer.submitReview("Produk luar biasa!");
        customer.redeemPoints("Alice", 500);
        customer.detectFraud("Alice", 2500);
        customer.approveRefund("a111, 2000");
        customer.logout();

        seller.login();
        seller.addProduct("Smartphone", 500);
        seller.manageOrders();
        seller.sendNotification("Pesanan Anda sedang dikirim.");
        seller.shipOrder("ORD123");
        seller.requestPayment(500);
        seller.checkCustomerTransaction(customer);
        seller.addUnreleasedProduct("Laptop Gaming");
        seller.addUnreleasedProduct("Smartphone 5G");
        seller.logout();

        admin.login();
        admin.moderateReviews();
        admin.submitReview("Produk berkualitas tinggi!");
        admin.trackUserActivity("Alice");
        admin.detectFraud("Alice", 3000);
        admin.applyDiscount(2000);
        admin.monitorSecurity("Bob");
        admin.adjustUserBalance(customer, -500);
        admin.adjustUserBalance(seller, 500);
        admin.viewUnreleasedProducts(seller);
        admin.logout();
    }
}