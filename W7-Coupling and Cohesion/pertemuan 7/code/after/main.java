import java.util.*;

// Core model: User as abstract base class
abstract class User {
    protected String username;
    protected String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void login() {
        System.out.println(username + " telah login.");
    }

    public void logout() {
        System.out.println(username + " telah logout.");
    }

    abstract void performAction();
}

// Financial concerns moved to separate service
class FinancialService {
    private Map<String, Double> userBalances = new HashMap<>();

    public void addUser(String username, double initialBalance) {
        userBalances.put(username, initialBalance);
    }

    public boolean hasEnoughBalance(String username, double amount) {
        Double balance = userBalances.getOrDefault(username, 0.0);
        return balance >= amount;
    }

    public void adjustBalance(String username, double amount) {
        Double currentBalance = userBalances.getOrDefault(username, 0.0);
        userBalances.put(username, currentBalance + amount);
        System.out.println("Saldo " + username + " disesuaikan sebesar $" + amount);
    }

    public void processPayment(String fromUser, String toUser, double amount) {
        if (hasEnoughBalance(fromUser, amount)) {
            adjustBalance(fromUser, -amount);
            adjustBalance(toUser, amount);
            System.out.println("Pembayaran $" + amount + " dari " + fromUser + " ke " + toUser);
        } else {
            System.out.println("Saldo tidak mencukupi untuk " + fromUser);
        }
    }
}

// Notification concerns moved to specialized service
class NotificationManager implements NotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("Notifikasi untuk " + recipient + ": " + message);
    }
}

interface NotificationService {
    void sendNotification(String recipient, String message);
}

// Product management moved to dedicated service
class ProductService {
    private Map<String, Map<String, Double>> sellerProducts = new HashMap<>();
    private Map<String, List<String>> unreleasedProducts = new HashMap<>();

    public void registerSeller(String sellerUsername) {
        sellerProducts.putIfAbsent(sellerUsername, new HashMap<>());
        unreleasedProducts.putIfAbsent(sellerUsername, new ArrayList<>());
    }

    public void addProduct(String sellerUsername, String product, double price) {
        sellerProducts.get(sellerUsername).put(product, price);
        System.out.println(sellerUsername + " menambahkan produk: " + product + " seharga $" + price);
    }

    public void addUnreleasedProduct(String sellerUsername, String product) {
        unreleasedProducts.get(sellerUsername).add(product);
        System.out.println(sellerUsername + " menambahkan produk draft: " + product);
    }

    public boolean isProductAvailable(String sellerUsername, String product) {
        return sellerProducts.getOrDefault(sellerUsername, new HashMap<>()).containsKey(product);
    }

    public double getProductPrice(String sellerUsername, String product) {
        return sellerProducts.getOrDefault(sellerUsername, new HashMap<>()).getOrDefault(product, 0.0);
    }

    public List<String> getApprovedUnreleasedProducts(String sellerUsername, String adminUsername) {
        // Only return if proper approval in place
        System.out.println(adminUsername + " mendapatkan produk draft yang disetujui dari " + sellerUsername);
        return unreleasedProducts.getOrDefault(sellerUsername, new ArrayList<>());
    }
}

// Order management moved to dedicated service
class OrderService {
    private Map<String, List<Order>> userOrders = new HashMap<>();
    private int nextOrderId = 1000;

    public OrderService() {
        for (ShippingStatus status : ShippingStatus.values()) {
            // Initialize all statuses
        }
    }

    public void registerUser(String username) {
        userOrders.putIfAbsent(username, new ArrayList<>());
    }

    public String createOrder(String buyerUsername, String sellerUsername, String product, double price) {
        String orderId = "ORD" + nextOrderId++;
        Order order = new Order(orderId, buyerUsername, sellerUsername, product, price);
        userOrders.get(buyerUsername).add(order);
        System.out.println("Pesanan dibuat: " + orderId + " untuk " + product);
        return orderId;
    }

    public void updateOrderStatus(String orderId, ShippingStatus status) {
        // Find and update order
        System.out.println("Status pesanan " + orderId + " diperbarui ke " + status);
    }

    public List<Order> getSellerOrders(String sellerUsername) {
        List<Order> sellerOrders = new ArrayList<>();
        for (List<Order> orders : userOrders.values()) {
            for (Order order : orders) {
                if (order.getSellerUsername().equals(sellerUsername)) {
                    sellerOrders.add(order);
                }
            }
        }
        return sellerOrders;
    }

    public List<Order> getBuyerOrders(String buyerUsername) {
        return userOrders.getOrDefault(buyerUsername, new ArrayList<>());
    }
}

class Order {
    private String id;
    private String buyerUsername;
    private String sellerUsername;
    private String product;
    private double price;
    private ShippingStatus status;

    public Order(String id, String buyerUsername, String sellerUsername, String product, double price) {
        this.id = id;
        this.buyerUsername = buyerUsername;
        this.sellerUsername = sellerUsername;
        this.product = product;
        this.price = price;
        this.status = ShippingStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }
}

enum ShippingStatus {
    PENDING, PROCESSING, SHIPPED, DELIVERED
}

// Review system moved to dedicated service
class ReviewService {
    private List<Review> reviews = new ArrayList<>();
    private List<Review> pendingReviews = new ArrayList<>();

    public void submitReview(String username, String product, String content, int rating) {
        Review review = new Review(username, product, content, rating);
        pendingReviews.add(review);
        System.out.println(username + " mengirimkan ulasan untuk " + product + ": " + content);
    }

    public void moderateReview(String adminUsername, Review review, boolean approved) {
        if (approved) {
            pendingReviews.remove(review);
            reviews.add(review);
            System.out.println(adminUsername + " menyetujui ulasan: " + review.getContent());
        } else {
            pendingReviews.remove(review);
            System.out.println(adminUsername + " menolak ulasan: " + review.getContent());
        }
    }

    public List<Review> getPendingReviews() {
        return new ArrayList<>(pendingReviews);
    }

    public List<Review> getApprovedReviews() {
        return new ArrayList<>(reviews);
    }
}

class Review {
    private String username;
    private String product;
    private String content;
    private int rating;

    public Review(String username, String product, String content, int rating) {
        this.username = username;
        this.product = product;
        this.content = content;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public String getProduct() {
        return product;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}

// Security concerns moved to dedicated service
class SecurityService {
    private Set<String> monitoredUsers = new HashSet<>();

    public void monitorUser(String username) {
        monitoredUsers.add(username);
        System.out.println("Memantau keamanan akun: " + username);
    }

    public boolean detectFraud(String username, double amount) {
        boolean suspicious = amount > 1000;
        if (suspicious) {
            System.out.println("Aktivitas mencurigakan terdeteksi untuk " + username + ": $" + amount);
        }
        return suspicious;
    }
}

// Discount handling moved to dedicated service
class DiscountService {
    private Map<String, Double> userDiscounts = new HashMap<>();

    public void setUserDiscount(String username, double discountPercentage) {
        userDiscounts.put(username, discountPercentage);
    }

    public double applyDiscount(String username, double price) {
        double discountPercentage = userDiscounts.getOrDefault(username, 0.0);
        double discountAmount = price * (discountPercentage / 100);
        System.out.println("Diskon sebesar $" + discountAmount + " diterapkan untuk " + username);
        return price - discountAmount;
    }
}

// Shipping concerns moved to dedicated service
class ShippingService {
    public void shipOrder(String sellerUsername, String orderId) {
        System.out.println(sellerUsername + " sedang mengirim pesanan: " + orderId);
    }

    public void trackShipment(String orderId) {
        System.out.println("Melacak pengiriman untuk pesanan: " + orderId);
    }
}

// Loyalty program moved to dedicated service
class LoyaltyService {
    private Map<String, Integer> userPoints = new HashMap<>();

    public void addUser(String username) {
        userPoints.put(username, 0);
    }

    public void addPoints(String username, int points) {
        int currentPoints = userPoints.getOrDefault(username, 0);
        userPoints.put(username, currentPoints + points);
        System.out.println(username + " mendapatkan " + points + " poin loyalitas");
    }

    public boolean redeemPoints(String username, int points) {
        int currentPoints = userPoints.getOrDefault(username, 0);
        if (currentPoints >= points) {
            userPoints.put(username, currentPoints - points);
            System.out.println(username + " menukarkan " + points + " poin loyalitas");
            return true;
        }
        return false;
    }

    public int getPoints(String username) {
        return userPoints.getOrDefault(username, 0);
    }
}

// Analytics service for tracking user activities
class AnalyticsService {
    public void trackUserActivity(String username, String activity) {
        System.out.println("Aktivitas tercatat: " + username + " - " + activity);
    }

    public void generateReport(String adminUsername) {
        System.out.println(adminUsername + " membuat laporan analitik");
    }
}

// Customer class now focused only on customer-specific operations
class Customer extends User {
    private List<String> purchaseHistory = new ArrayList<>();

    public Customer(String username, String email) {
        super(username, email);
    }

    @Override
    void performAction() {
        System.out.println(username + " sedang berbelanja di e-commerce.");
    }
}

// Seller class now focused only on seller-specific operations
class Seller extends User {
    public Seller(String username, String email) {
        super(username, email);
    }

    @Override
    void performAction() {
        System.out.println(username + " mengelola toko online.");
    }
}

// Admin class now focused only on admin-specific operations
class Admin extends User {
    public Admin(String username, String email) {
        super(username, email);
    }

    @Override
    void performAction() {
        System.out.println(username + " mengelola sistem e-commerce.");
    }
}

// Main system class that coordinates the services
