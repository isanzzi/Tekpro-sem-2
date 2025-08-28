import java.util.ArrayList;
import java.util.List;

// Interface untuk pengguna yang bisa membeli produk
interface Purchasable {
    void buyProduct(String product, double price);
}

// Interface untuk pengguna yang bisa menjual produk
interface Sellable {
    void addProduct(String product, double price);
    void manageOrders();
}

// Interface untuk admin yang bisa memoderasi
interface Moderatable {
    void moderateReviews();
}

// Interface untuk fitur premium
interface PremiumFeatures {
    double getDiscount(double price);
    void accessVIPSupport();
}

// Abstract class User sebagai dasar semua jenis pengguna
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

// Customer bisa membeli produk
class Customer extends User implements Purchasable {
    private List<String> purchaseHistory = new ArrayList<>();

    public Customer(String username, String email) {
        super(username, email);
    }

    @Override
    public void buyProduct(String product, double price) {
        purchaseHistory.add(product);
        System.out.println(username + " membeli " + product + " seharga $" + price);
    }

    @Override
    void performAction() {
        System.out.println(username + " sedang berbelanja di e-commerce.");
    }
}

// Seller bisa menjual produk
class Seller extends User implements Sellable {
    private List<String> productList = new ArrayList<>();

    public Seller(String username, String email) {
        super(username, email);
    }

    @Override
    public void addProduct(String product, double price) {
        productList.add(product);
        System.out.println(username + " menambahkan produk: " + product + " seharga $" + price);
    }

    @Override
    public void manageOrders() {
        System.out.println(username + " sedang mengelola pesanan.");
    }

    @Override
    void performAction() {
        System.out.println(username + " sedang mengelola toko online.");
    }
}

// Admin bisa memoderasi ulasan dan transaksi
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

// Premium Customer adalah Customer yang punya fitur tambahan
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

// Main class untuk menjalankan sistem
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