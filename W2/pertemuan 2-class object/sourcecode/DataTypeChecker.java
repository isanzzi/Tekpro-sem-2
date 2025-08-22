import java.util.Scanner;

public class DataTypeChecker {
    public static void main(String[] args) {
        // Menampilkan nilai minimum dan maksimum untuk setiap tipe data
        System.out.println("byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);

        // Menggunakan try-with-resources untuk memastikan Scanner ditutup secara otomatis
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ada berapa test case : ");
            int T = sc.nextInt(); // Jumlah test case
            sc.nextLine(); // Membersihkan newline setelah nextInt()

            System.out.println("You entered " + T + " test cases.");

            for (int i = 0; i < T; i++) {
                System.out.print("Masukkan angka: ");
                String input = sc.nextLine(); // Baca input sebagai string

                try {
                    long n = Long.parseLong(input); // Coba konversi ke long
                    System.out.println(n + " can be fitted in:");

                    if (n >= Byte.MIN_VALUE && n <= Byte.MAX_VALUE) {
                        System.out.println("* byte");
                    }
                    if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE) {
                        System.out.println("* short");
                    }
                    if (n >= Integer.MIN_VALUE && n <= Integer.MAX_VALUE) {
                        System.out.println("* int");
                    }
                    if (n >= Long.MIN_VALUE && n <= Long.MAX_VALUE) {
                        System.out.println("* long");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " cannot be fitted anywhere.");
                }
            }
        }
    }
}
