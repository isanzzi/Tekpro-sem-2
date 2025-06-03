// untuk kendaraan yang tidak ada di spesifikasi seperti mobil dan motor
// agar tidak return null dan masih bisa panggil suaranya
public class KendaraanKosong extends Kendaraan{
    public void suara (){
        System.out.println("Kendaraan tidak dikenali atau tidak ada suaranya");
    }
}
