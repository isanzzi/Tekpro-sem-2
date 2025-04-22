import java.util.List;
import java.util.ArrayList;

// Generic Interface
interface Deliverable<T> {
    void deliver(T item);
}

// Generic Class
class Product<T> {
    private String name;
    private double price;
    private T detail;

    public Product(String name, double price, T detail) {
        this.name = name;
        this.price = price;
        this.detail = detail;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public T getDetail() { return detail; }

    @Override
    public String toString() {
        return name + " - $" + price + " (" + detail.toString() + ")";
    }
}

// Generic Inheritance
class DigitalProduct<T> extends Product<T> implements Deliverable<Product<T>> {
    public DigitalProduct(String name, double price, T detail) {
        super(name, price, detail);
    }

    public void deliver(Product<T> item) {
        System.out.println("Delivering digital item: " + item.getName());
    }
}

class PhysicalProduct<T> extends Product<T> implements Deliverable<Product<T>> {
    public PhysicalProduct(String name, double price, T detail) {
        super(name, price, detail);
    }

    public void deliver(Product<T> item) {
        System.out.println("Shipping physical item: " + item.getName());
    }
}

// Generic Multiple
class Order<K, V> {
    private K orderId;
    private V product;

    public Order(K orderId, V product) {
        this.orderId = orderId;
        this.product = product;
    }

    public void showOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Product: " + product.toString());
    }
}

// Wildcard Example
class Cart {
    public static void showCartInfo(List<? extends Product<?>> cartItems) {
        for (Product<?> item : cartItems) {
            System.out.println(item);
        }
    }
}

// Main Class
public class MarketplaceDemo {
    public static void main(String[] args) {
        DigitalProduct<Integer> ebook = new DigitalProduct<>("E-Book Java", 10.0, 500); // 500 pages
        PhysicalProduct<Double> tShirt = new PhysicalProduct<>("Kaos Polos", 15.0, 0.25); // 0.25 kg

        Order<String, Product<?>> order1 = new Order<>("ORD001", ebook);
        Order<String, Product<?>> order2 = new Order<>("ORD002", tShirt);

        order1.showOrder();
        order2.showOrder();

        List<Product<?>> cart = new ArrayList<>();
        cart.add(ebook);
        cart.add(tShirt);

        Cart.showCartInfo(cart);

        ebook.deliver(ebook);
        tShirt.deliver(tShirt);
    }
}
