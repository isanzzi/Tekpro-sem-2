class Restaurant {
    private MenuMakanan[] Menu;
    private static byte id = 0;

    public Restaurant() {
        Menu = new MenuMakanan[10];
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {
        Menu[id] = new MenuMakanan(nama, harga, stok);
        id++;
    }

    public void tampilMenuMakanan() {
        for (int i = 0; i < id; i++) {
            if (!isOutOfStock(i)) {
                System.out.println(Menu[i].getNama() + " (" + Menu[i].getStok() + ")\tRp. " + Menu[i].getHarga());
            }
        }
    }

    public void PesanMakanan(String nama, int jumlah) { // Implementasi method pemesanan
        for (int i = 0; i < id; i++) {
            if (Menu[i].getNama().equals(nama)) {
                if (Menu[i].getStok() >= jumlah) {
                    Menu[i].kurangiStok(jumlah);
                    System.out.println("Pesanan " + nama + " sebanyak " + jumlah + " berhasil.");
                } else {
                    System.out.println("Stok " + nama + " tidak mencukupi.");
                }
                return;
            }
        }
        System.out.println("Menu tidak ditemukan.");
    }

    public boolean isOutOfStock(int index) {
        return Menu[index].stokhabis();
    }
}