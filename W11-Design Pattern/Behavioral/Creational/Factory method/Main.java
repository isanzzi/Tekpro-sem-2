public class Main {
    public static void main (String[] args){
        Kendaraan Kendaraan0 = KendaraanFactory.buat("failedcase");
        Kendaraan0.suara();

        Kendaraan Kendaraan1 = KendaraanFactory.buat("Mobil");
        Kendaraan1.suara();

        Kendaraan Kendaraan2 = KendaraanFactory.buat("Motor");
        Kendaraan2.suara();
    }
}
