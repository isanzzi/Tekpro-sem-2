// Interface untuk pengguna yang bisa menjual produk
interface Sellable {
    void addProduct(String product, double price);
    void manageOrders();
}