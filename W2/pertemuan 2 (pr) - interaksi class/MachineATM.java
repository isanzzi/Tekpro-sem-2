import java.util.Scanner;

public class MachineATM {
    private IDCard ID; // Kartu yang dimasukkan

    public void insertCard(IDCard ID) {
        this.ID = ID;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        if (ID == null) {
            System.out.println("Tidak ada kartu yang dimasukkan!");
            return;
        }

        System.out.println("Selamat datang di ATM!");

        // Looping untuk cek IDNumber tanpa batas
        while (true) {
            System.out.print("Masukkan ID Number: ");
            String inputID = scanner.next();

            if (inputID.equals(ID.getIDNumber())) {
                System.out.println("ID benar! Masukkan PIN.");
                break; // Jika ID benar, lanjut ke validasi PIN
            } else {
                System.out.println("ID salah! Silakan coba lagi.");
            }
        }

        // PIN hanya boleh dicoba 3 kali
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Masukkan PIN: ");
            String inputPIN = scanner.next();

            if (inputPIN.equals(ID.getPIN())) {
                System.out.println("Login berhasil!");
                showMenu(scanner);
                return;
            } else {
                attempts++;
                System.out.println("PIN salah! Percobaan ke-" + attempts);
            }
        }

        System.out.println("Terlalu banyak percobaan salah. Kartu Anda diblokir.");
    }

    private void showMenu(Scanner scanner) {
        int choice = -1;
        while (choice != 4) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Deposit");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Saldo Anda: " + ID.getBalance());
                    break;
                case 2:
                    System.out.print("Masukkan jumlah deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Jumlah uang yang bisa ditarik adalah : " + ID.getBalance());
                    System.out.print("Masukkan jumlah penarikan: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan ATM.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            double newBalance = ID.getBalance() + amount;
            ID.setBalance(newBalance);
            System.out.println("Deposit berhasil: " + amount);
            System.out.println("saldo anda sekarang : " + ID.getBalance());
        } else {
            System.out.println("Jumlah deposit tidak valid!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= ID.getBalance()) {
            double newBalance = ID.getBalance() - amount;
            ID.setBalance(newBalance);
            System.out.println("Penarikan berhasil: " + amount);
            System.out.println("Saldo anda sekarang : " + ID.getBalance());
        } else {
            System.out.println("Saldo tidak cukup atau jumlah tidak valid.");
        }
    }
}
