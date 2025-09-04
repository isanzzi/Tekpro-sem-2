import java.util.List;
import java.util.ArrayList;
class Admin extends User implements Moderatable, PaymentProcessor, ReviewSystem, AnalyticsService, FraudDetection, DiscountService, SecurityService, NotificationService {
    public Admin(String username, String email) {
        super(username, email);
    }

    //CBO
    public void viewUnreleasedProducts(Seller seller) {
        System.out.println("Produk yang belum dirilis oleh Seller " + ": " + seller.getUnreleasedProducts());
    }

    // CBO: Admin bisa mengakses saldo Customer atau Seller secara langsung (seharusnya tidak boleh)
    public void adjustUserBalance(User user, double amount) {
        user.balance += amount;
        System.out.println(username + " mengubah saldo milik " + user.username + " sebesar $" + amount);
    }

    @Override
    public void moderateReviews() {
        System.out.println(username + " sedang memoderasi ulasan.");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println(username + " memproses pembayaran sebesar $" + amount);
    }

    @Override
    public void submitReview(String review) {
        System.out.println(username + " menyetujui ulasan: " + review);
    }

    @Override
    public void trackUserActivity(String username) {
        System.out.println("Melacak aktivitas pengguna: " + username);
    }

    @Override
    public boolean detectFraud(String username, double amount) {
        System.out.println("Mendeteksi kecurangan untuk pengguna: " + username + " sebesar $" + amount);
        return amount > 1000;
    }

    @Override
    public double applyDiscount(double price) {
        double discount = price * 0.1;
        System.out.println("Diskon sebesar $" + discount + " diterapkan.");
        return price - discount;
    }

    @Override
    public void monitorSecurity(String username) {
        System.out.println("Memantau keamanan akun: " + username);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Admin mengirim notifikasi: " + message);
    }

    @Override
    void performAction() {
        System.out.println(username + " mengelola sistem e-commerce.");
    }
}