// Kelas Main
public class Main {
    public static void main(String[] args) {
        Film film1 = new Film("Hachiko", "Drama", 93, 35000);
        Film film2 = new Film("Spider-man no way home", "Fiction", 148, 40000);

        // Menampilkan informasi film
        System.out.println("=== Daftar Film ===");
        System.out.println("1. " + film1.getJudul() + " (" + film1.getGenre() + ") - " + film1.getDurasi() + " menit - Rp. " + film1.getHargaTiket());
        System.out.println("2. " + film2.getJudul() + " (" + film2.getGenre() + ") - " + film2.getDurasi() + " menit - Rp. " + film2.getHargaTiket());

        // Membuat pelanggan
        Pelanggan pelanggan1 = new Pelanggan("Ihsan Fauzi", "ihsan@email.com", "081234567890"); // Tambah nomor telepon

        // Memesan tiket
        Tiket tiket1 = new Tiket(film1, "27 Februari 2025 - 19:00 WIB", "A5");
        Pemesanan pemesanan1 = new Pemesanan(pelanggan1, tiket1, 1);

        // Menampilkan detail pemesanan
        pemesanan1.tampilkanDetailPemesanan();
    }
}
