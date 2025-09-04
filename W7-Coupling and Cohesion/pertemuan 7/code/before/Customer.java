import java.util.List;
import java.util.ArrayList;

class Customer extends User implements Purchasable, FraudDetection, ReviewSystem, LoyaltyProgram {
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
    public boolean detectFraud(String username, double amount) {
        System.out.println("Mendeteksi kemungkinan penipuan untuk pelanggan: " + username);
        return amount > 2000;
    }

    @Override
    public void submitReview(String review) {
        System.out.println(username + " mengirimkan ulasan: " + review);
    }

    @Override
    public void redeemPoints(String username, int points) {
        System.out.println(username + " menukarkan " + points + " poin loyalitas.");
    }

    public void approveRefund(String orderID) {
        System.out.println(username + " menyetujui pengembalian dana untuk pesanan " + orderID);
    }

    @Override
    void performAction() {
        System.out.println(username + " sedang berbelanja di e-commerce.");
    }
}