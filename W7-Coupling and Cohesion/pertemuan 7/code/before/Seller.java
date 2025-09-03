import java.util.List;
import java.util.ArrayList;
class Seller extends User implements Sellable, NotificationService, ShippingService {
    private List<String> productList = new ArrayList<>();
    private List<String> unreleasedProducts = new ArrayList<>();

    private Admin admin;

    public List<String> getUnreleasedProducts() {
        return unreleasedProducts;
    }

    public void addUnreleasedProduct(String product) {
        unreleasedProducts.add(product);
    }

    public Seller(String username, String email, double balance, Admin admin) {
        super(username, email);
        this.balance=balance;
        this.admin = admin;
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

    public void requestPayment(double amount) {
        admin.processPayment(amount);
    }

    // CBO: Seller dapat mengetahui harga transaksi Customer secara langsung (seharusnya tidak perlu tahu)
    public void checkCustomerTransaction(Customer customer) {
        System.out.println(username + " melihat transaksi terakhir pelanggan " + customer.username + " sebesar $" + customer.balance);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Mengirim notifikasi kepada pelanggan: " + message);
    }

    @Override
    public void shipOrder(String orderID) {
        System.out.println(username + " sedang mengirim pesanan: " + orderID);
    }

    @Override
    void performAction() {
        System.out.println(username + " mengelola toko online.");
    }
}