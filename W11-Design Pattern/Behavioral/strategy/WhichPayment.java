public class WhichPayment {
    private PaymentStrategy strategy;

    public  void setStrategy (PaymentStrategy strategy){
        this.strategy= strategy;
    }

    public  void payBill (int amount){
        if (strategy != null){
            strategy.pay(amount);
        } else {
            System.out.println("Strategi payment belum dipilih.");
        }
    }
}
