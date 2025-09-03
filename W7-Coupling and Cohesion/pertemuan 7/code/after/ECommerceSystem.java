import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ECommerceSystem {
    private FinancialService financialService;
    private NotificationManager notificationManager;
    private ProductService productService;
    private OrderService orderService;
    private ReviewService reviewService;
    private SecurityService securityService;
    private DiscountService discountService;
    private ShippingService shippingService;
    private LoyaltyService loyaltyService;
    private AnalyticsService analyticsService;

    private Map<String, User> users = new HashMap<>();

    public ECommerceSystem() {
        this.financialService = new FinancialService();
        this.notificationManager = new NotificationManager();
        this.productService = new ProductService();
        this.orderService = new OrderService();
        this.reviewService = new ReviewService();
        this.securityService = new SecurityService();
        this.discountService = new DiscountService();
        this.shippingService = new ShippingService();
        this.loyaltyService = new LoyaltyService();
        this.analyticsService = new AnalyticsService();
    }

    public void registerCustomer(String username, String email) {
        Customer customer = new Customer(username, email);
        users.put(username, customer);
        financialService.addUser(username, 0);
        orderService.registerUser(username);
        loyaltyService.addUser(username);
        System.out.println("Pelanggan baru terdaftar: " + username);
    }

    public void registerSeller(String username, String email, double initialBalance) {
        Seller seller = new Seller(username, email);
        users.put(username, seller);
        financialService.addUser(username, initialBalance);
        productService.registerSeller(username);
        orderService.registerUser(username);
        System.out.println("Penjual baru terdaftar: " + username);
    }

    public void registerAdmin(String username, String email) {
        Admin admin = new Admin(username, email);
        users.put(username, admin);
        System.out.println("Admin baru terdaftar: " + username);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    // Customer operations
    public void buyProduct(String customerUsername, String sellerUsername, String product) {
        // Verify product availability
        if (productService.isProductAvailable(sellerUsername, product)) {
            double price = productService.getProductPrice(sellerUsername, product);
            // Process payment
            if (financialService.hasEnoughBalance(customerUsername, price)) {
                financialService.processPayment(customerUsername, sellerUsername, price);
                // Create order
                String orderId = orderService.createOrder(customerUsername, sellerUsername, product, price);
                // Send notifications
                notificationManager.sendNotification(customerUsername, "Pesanan " + orderId + " berhasil dibuat");
                notificationManager.sendNotification(sellerUsername, "Anda menerima pesanan baru: " + orderId);
                // Add loyalty points
                loyaltyService.addPoints(customerUsername, (int)(price / 10));
                // Track activity
                analyticsService.trackUserActivity(customerUsername, "Membeli produk: " + product);
            } else {
                notificationManager.sendNotification(customerUsername, "Saldo tidak mencukupi");
            }
        } else {
            notificationManager.sendNotification(customerUsername, "Produk tidak tersedia");
        }
    }

    public void submitProductReview(String customerUsername, String product, String content, int rating) {
        reviewService.submitReview(customerUsername, product, content, rating);
        analyticsService.trackUserActivity(customerUsername, "Mengirim ulasan produk");
    }

    public void redeemLoyaltyPoints(String customerUsername, int points) {
        if (loyaltyService.redeemPoints(customerUsername, points)) {
            double discount = points / 10.0;
            discountService.setUserDiscount(customerUsername, discount);
            notificationManager.sendNotification(customerUsername,
                    "Berhasil menukar " + points + " poin untuk diskon " + discount + "%");
        } else {
            notificationManager.sendNotification(customerUsername, "Poin tidak mencukupi");
        }
    }

    // Seller operations
    public void addProduct(String sellerUsername, String product, double price) {
        productService.addProduct(sellerUsername, product, price);
        analyticsService.trackUserActivity(sellerUsername, "Menambahkan produk: " + product);
    }

    public void addUnreleasedProduct(String sellerUsername, String product) {
        productService.addUnreleasedProduct(sellerUsername, product);
    }

    public void manageOrders(String sellerUsername) {
        List<Order> orders = orderService.getSellerOrders(sellerUsername);
        System.out.println(sellerUsername + " mengelola " + orders.size() + " pesanan");
        analyticsService.trackUserActivity(sellerUsername, "Mengelola pesanan");
    }

    public void shipOrder(String sellerUsername, String orderId) {
        shippingService.shipOrder(sellerUsername, orderId);
        orderService.updateOrderStatus(orderId, ShippingStatus.SHIPPED);
        analyticsService.trackUserActivity(sellerUsername, "Mengirim pesanan: " + orderId);
    }

    // Admin operations
    public void moderateReview(String adminUsername, Review review, boolean approved) {
        reviewService.moderateReview(adminUsername, review, approved);
        analyticsService.trackUserActivity(adminUsername, "Memoderasi ulasan");
    }

    public void monitorUser(String adminUsername, String username) {
        securityService.monitorUser(username);
        analyticsService.trackUserActivity(adminUsername, "Memantau pengguna: " + username);
    }

    public void checkForFraud(String adminUsername, String username, double amount) {
        boolean isSuspicious = securityService.detectFraud(username, amount);
        if (isSuspicious) {
            notificationManager.sendNotification(adminUsername, "Aktivitas mencurigakan terdeteksi: " + username);
        }
        analyticsService.trackUserActivity(adminUsername, "Memeriksa kecurangan");
    }

    public void adjustUserBalance(String adminUsername, String username, double amount) {
        financialService.adjustBalance(username, amount);
        notificationManager.sendNotification(username, "Saldo Anda disesuaikan sebesar $" + amount);
        analyticsService.trackUserActivity(adminUsername, "Menyesuaikan saldo pengguna: " + username);
    }

    public List<String> getApprovedUnreleasedProducts(String adminUsername, String sellerUsername) {
        return productService.getApprovedUnreleasedProducts(sellerUsername, adminUsername);
    }

    public void generateAnalyticsReport(String adminUsername) {
        analyticsService.generateReport(adminUsername);
    }

    public static void main(String[] args) {
        ECommerceSystem system = new ECommerceSystem();

        // Register users
        system.registerAdmin("Charlie", "charlie@admin.com");
        system.registerCustomer("Alice", "alice@mail.com");
        system.registerSeller("Bob", "bob@store.com", 1000);

        // Fund customer account
        system.adjustUserBalance("Charlie", "Alice", 2000);

        // Customer actions
        User alice = system.getUserByUsername("Alice");
        alice.login();
        system.buyProduct("Alice", "Bob", "Laptop");
        system.submitProductReview("Alice", "Laptop", "Produk luar biasa!", 5);
        system.redeemLoyaltyPoints("Alice", 50);
        alice.logout();

        // Seller actions
        User bob = system.getUserByUsername("Bob");
        bob.login();
        system.addProduct("Bob", "Smartphone", 500);
        system.addProduct("Bob", "Laptop", 1000);
        system.addUnreleasedProduct("Bob", "Laptop Gaming");
        system.addUnreleasedProduct("Bob", "Smartphone 5G");
        system.manageOrders("Bob");
        system.shipOrder("Bob", "ORD1000");
        bob.logout();

        // Admin actions
        User charlie = system.getUserByUsername("Charlie");
        charlie.login();
        Review pendingReview = system.reviewService.getPendingReviews().get(0);
        system.moderateReview("Charlie", pendingReview, true);
        system.monitorUser("Charlie", "Alice");
        system.checkForFraud("Charlie", "Alice", 3000);
        system.getApprovedUnreleasedProducts("Charlie", "Bob");
        system.generateAnalyticsReport("Charlie");
        charlie.logout();
    }
}