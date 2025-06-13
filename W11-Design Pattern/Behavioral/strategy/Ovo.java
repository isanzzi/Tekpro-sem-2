public class Ovo implements PaymentStrategy{
    public void pay (int amount){
        System.out.println("Bayar Rp " + amount + "via OVO.");
    }
}
