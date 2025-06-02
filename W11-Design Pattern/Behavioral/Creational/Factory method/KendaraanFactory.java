public class KendaraanFactory {
    static Kendaraan buat (String Tipe){
        if (Tipe.equals("Mobil")){
            return new Mobil();
        }
        else if (Tipe.equals("Motor")){
            return new Motor();
        }
        else{
            return new KendaraanKosong();
        }
    }
}
