import java.util.*;
import java.util.concurrent.*;

// 2. Record untuk merepresentasikan Produk secara immutable
//    Alasan: Record memastikan data produk tidak dapat diubah setelah dibuat.
record Product(String id, String name, double price) {}

// 2. Record untuk merepresentasikan Order secara immutable
//    Alasan: Record membuat data pesanan tetap konstan setelah didefinisikan.
record Order(String orderId, String buyer, List<Product> products) {}

public class DigitalMarketplace {
    // 1. List digunakan untuk menyimpan riwayat pesanan secara thread-safe
    //    Alasan: CopyOnWriteArrayList aman untuk akses concorrurent saat mencatat riwayat.
    private final List<Order> orderHistory = new CopyOnWriteArrayList<>();

    // 1. Set digunakan untuk melacak produk unik yang dibeli pengguna
    //    Alasan: Set memastikan tidak ada duplikat produk dalam daftar pembelian pengguna.
    private final Map<String, Set<String>> userPurchases = new ConcurrentHashMap<>();

    // 1. Map digunakan untuk katalog produk dengan akses cepat via ID
    //    Alasan: ConcurrentHashMap memungkinkan pembaruan katalog secara thread-safe.
    private final Map<String, Product> productCatalog = new ConcurrentHashMap<>();

    // 5. Queue digunakan untuk antrean pesanan yang belum diproses
    //    Alasan: ConcurrentLinkedQueue memastikan FIFO dan thread-safety.
    private final Queue<Order> orderQueue = new ConcurrentLinkedQueue<>();

    // 6. Immutable Collection untuk daftar produk yang didukung
    //    Alasan: Set.of memastikan daftar produk tetap konstan.
    private final Set<String> supportedProductIds = Set.of("P001", "P002", "P003");

    // 6. Immutable Collection untuk daftar pengguna awal
    //    Alasan: List.of memastikan daftar pengguna tidak dapat diubah.
    private final List<String> initialUsers = List.of("Alice", "Bob", "Charlie");

    public DigitalMarketplace() {
        // 6. Immutable Collection untuk inisialisasi katalog produk
        //    Alasan: Map.of memastikan data awal produk tidak dapat dimodifikasi.
        initializeProducts();
    }

    private void initializeProducts() {
        Map<String, Product> initialProducts = Map.of(
                "P001", new Product("P001", "Laptop", 1200.99),
                "P002", new Product("P002", "Smartphone", 699.49),
                "P003", new Product("P003", "Headphones", 149.99)
        );
        productCatalog.putAll(initialProducts);
    }

    // Menambahkan pesanan ke antrean
    public void placeOrder(String orderId, String buyer, List<String> productIds) {
        // 3. Menggunakan Optional untuk validasi produk
        //    Alasan: Optional memastikan penanganan aman terhadap produk yang tidak valid.
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> product = Optional.ofNullable(
                    supportedProductIds.contains(productId) ? productCatalog.get(productId) : null
            );
            product.ifPresent(products::add);
        }

        if (!products.isEmpty()) {
            Order order = new Order(orderId, buyer, Collections.unmodifiableList(products));
            orderQueue.offer(order); // 5. Enqueue
            System.out.println("Order " + orderId + " placed by " + buyer);
        } else {
            System.out.println("Order failed: No valid products selected.");
        }
    }

    // Membatalkan pesanan dari antrean
    public void cancelOrder(String orderId) {
        // 5. Dequeue menggunakan removeIf
        //    Alasan: removeIf efisien dan aman untuk menghapus pesanan tertentu.
        boolean removed = orderQueue.removeIf(order -> order.orderId().equals(orderId));
        if (removed) {
            System.out.println("Order " + orderId + " cancelled successfully.");
        } else {
            System.out.println("Order " + orderId + " not found or already processed.");
        }
    }

    // Memproses pesanan dengan ExecutorService
    public void processOrders() {
        // 4. Menggunakan ExecutorService untuk konkurensi
        //    Alasan: Thread pool meningkatkan efisiensi pemrosesan pesanan secara paralel.
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();

        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll(); // 5. Dequeue
            futures.add(executor.submit(() -> processOrder(order)));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processOrder(Order order) {
        orderHistory.add(order); // Menyimpan ke riwayat
        Set<String> productIds = userPurchases.computeIfAbsent(order.buyer(), k -> ConcurrentHashMap.newKeySet());
        order.products().forEach(p -> productIds.add(p.id()));
        double total = order.products().stream().mapToDouble(Product::price).sum();
        System.out.println("Processed order " + order.orderId() + " for " + order.buyer() + " - Total: $" + total);
    }

    // Menampilkan riwayat pesanan
    public void printOrderHistory() {
        System.out.println("\nOrder History:");
        orderHistory.forEach(System.out::println);
    }

    // Menampilkan pembelian pengguna
    public void printUserPurchases(String buyer) {
        System.out.println("Purchases by " + buyer + ": " + userPurchases.getOrDefault(buyer, Set.of()));
    }

    public static void main(String[] args) {
        DigitalMarketplace marketplace = new DigitalMarketplace();

        marketplace.placeOrder("O001", "Alice", List.of("P001", "P003"));
        marketplace.placeOrder("O002", "Bob", List.of("P002"));
        marketplace.placeOrder("O003", "Charlie", List.of("P004")); // Produk tidak valid
        marketplace.cancelOrder("O002"); // Membatalkan pesanan Bob

        marketplace.processOrders();

        marketplace.printOrderHistory();
        marketplace.printUserPurchases("Alice");
    }
}