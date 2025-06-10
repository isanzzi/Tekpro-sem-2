public class Bank implements  PaymentStrategy{
    public void pay(int amount) {
        System.out.println("Bayar Rp "+ amount + "via Bank.");
    }
}
