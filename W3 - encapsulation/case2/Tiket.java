// Kelas Tiket
public class Tiket {
    private Film film;
    private String jadwalTayang;
    private String nomorKursi;

    public Tiket(Film film, String jadwalTayang, String nomorKursi){
        this.film = film;
        this.jadwalTayang = jadwalTayang;
        this.nomorKursi = nomorKursi;
    }

    public Film getFilm(){
        return film;
    }

    public String getJadwalTayang(){
        return jadwalTayang;
    }

    public String getNomorKursi(){
        return nomorKursi;
    }
}
