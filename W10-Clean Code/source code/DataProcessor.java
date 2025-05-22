public class DataProcessor {
    public double calculateAverage(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) { // Duplikasi kode
            sum += numbers[i];
        }
        return sum / numbers.length; // Potensi pembagian dengan nol, tidak ada pengecekan
    }

    public void printAverage(int[] numbers) {
        double avg = calculateAverage(numbers);
        System.out.println("Avg: " + avg); // Nama variabel tidak deskriptif
    }

    public void printSum(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) { // Duplikasi kode lagi
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);
        int x = 42; // Magic number dan dead code
        if (false) {
            System.out.println("Tidak pernah dieksekusi");
        }
    }

    public void duplicateLogic(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) { // Duplikasi kode ketiga
            sum += numbers[i];
        }
        System.out.println("Duplicate Sum: " + sum);
    }

    public void insecureOperation() {
        String secret = "password123"; // Pelanggaran keamanan: hardcode password
        System.out.println("Secret: " + secret);
        int uninitializedVar; // Variabel tidak diinisialisasi
        System.out.println(uninitializedVar); // Bug: penggunaan variabel tidak diinisialisasi
    }

    public void overlyComplexMethod(int a, int b, int c, int d, int e) { // Terlalu banyak parameter
        if (a > b && b > c && c > d && d > e || a == 42) { // Logika kompleks dan magic number
            System.out.println("Kompleksitas tinggi");
        }
    }
}