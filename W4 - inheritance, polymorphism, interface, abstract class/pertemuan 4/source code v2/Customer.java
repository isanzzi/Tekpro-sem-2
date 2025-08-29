import java.util.ArrayList;
import java.util.List;

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