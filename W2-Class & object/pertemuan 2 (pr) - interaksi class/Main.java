public class Main {
    public static void main(String[] args) {
        IDCard myCard = new IDCard("123456789", "1234", 100000);
        MachineATM myATM = new MachineATM();

        myATM.insertCard(myCard); // 🔵 Masukkan kartu
        myATM.start(); // 🔵 Mulai ATM
    }
}
