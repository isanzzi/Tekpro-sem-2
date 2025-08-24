class MenuMakanan {
    private String nama_makanan;
    private double harga_makanan;
    private int stok;

    public MenuMakanan(String nama_makanan, double harga_makanan, int stok) {
        this.nama_makanan = nama_makanan;
        this.harga_makanan = harga_makanan;
        this.stok = stok;
    }

    public String getNama() {
        return nama_makanan;
    }

    public double getHarga() {
        return harga_makanan;
    }

    public int getStok() {
        return stok;
    }

    public boolean stokhabis() {
        return stok == 0;
    }

    public void kurangiStok(int jumlah) { // Perubahan untuk mengurangi stok
        if (jumlah <= stok) {
            stok -= jumlah;
        } else {
            System.out.println("Stok tidak cukup!");
        }
    }
}