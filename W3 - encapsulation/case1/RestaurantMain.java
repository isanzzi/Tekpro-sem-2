class RestaurantMain {
    public static void main(String[] args) {
        Restaurant menu = new Restaurant();
        menu.tambahMenuMakanan("Bala-Bala", 1000, 20);
        menu.tambahMenuMakanan("Gehu", 1000, 10);
        menu.tambahMenuMakanan("Tahu", 1000, 15);
        menu.tambahMenuMakanan("Molen", 1000, 5);

        menu.tampilMenuMakanan();

        System.out.println("\n=== Pemesanan ===");
        menu.PesanMakanan("Gehu", 5);
        menu.PesanMakanan("Molen", 6); // Tidak cukup stok

        System.out.println("\n=== Menu Setelah Pemesanan ===");
        menu.tampilMenuMakanan();
    }
}