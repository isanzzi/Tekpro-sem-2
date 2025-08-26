// Kelas Pelanggan
public class Pelanggan {
    private String nama;
    private String email;  // Perbaikan nama variabel
    private String notelp;

    public Pelanggan(String nama, String email, String notelp){
        this.nama = nama;
        this.email = email;
        this.notelp = notelp;
    }

    public String getNama(){
        return nama;
    }

    public String getEmail(){ // Perbaikan method getter
        return email;
    }

    public String getNotelp(){
        return notelp;
    }
}
