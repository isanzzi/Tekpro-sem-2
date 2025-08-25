// Kelas Pemesanan
public class Pemesanan {
    private Pelanggan pelanggan; // Perbaikan nama variabel
    private Tiket tiket;
    private int jumlahTiket;
    private double totalHarga;

    public Pemesanan(Pelanggan pelanggan, Tiket tiket, int jumlahTiket) {
        this.pelanggan = pelanggan;
        this.tiket = tiket;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = hitungTotalHarga();
    }

    private double hitungTotalHarga() {
        return jumlahTiket * tiket.getFilm().getHargaTiket();
    }

    public void tampilkanDetailPemesanan() {
        System.out.println("=== Detail Pemesanan Tiket ===");
        System.out.println("Nama Pelanggan  : " + pelanggan.getNama());
        System.out.println("Email           : " + pelanggan.getEmail());
        System.out.println("No. Telepon     : " + pelanggan.getNotelp()); // Tambahan informasi
        System.out.println("Film            : " + tiket.getFilm().getJudul());
        System.out.println("Genre           : " + tiket.getFilm().getGenre());
        System.out.println("Durasi          : " + tiket.getFilm().getDurasi() + " menit");
        System.out.println("Jadwal Tayang   : " + tiket.getJadwalTayang());
        System.out.println("Nomor Kursi     : " + tiket.getNomorKursi());
        System.out.println("Jumlah Tiket    : " + jumlahTiket);
        System.out.println("Total Harga     : Rp. " + totalHarga);
    }
}
