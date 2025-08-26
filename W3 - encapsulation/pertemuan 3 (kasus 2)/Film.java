// Kelas Film
public class Film {
    private String judul;
    private String genre;
    private int durasi;
    private double hargaTiket;

    public Film(String judul, String genre, int durasi, double hargaTiket){
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.hargaTiket = hargaTiket;
    }

    public String getJudul(){
        return judul;
    }

    public String getGenre(){
        return genre;
    }

    public int getDurasi(){ // Perbaikan nama method
        return durasi;
    }

    public double getHargaTiket() { // Getter harga tiket
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket){ // Perbaikan setter
        this.hargaTiket = hargaTiket;
    }
}
