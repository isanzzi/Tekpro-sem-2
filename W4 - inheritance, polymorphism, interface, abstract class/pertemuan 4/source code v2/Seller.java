import java.util.ArrayList;
import java.util.List;

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