public class Main {
    public static void main (String[] args){
        WhichPayment resultchoose = new WhichPayment();

        //ini harusnya gagal karena belum ada pilihan paymentnya
        resultchoose.payBill(1000);

        resultchoose.setStrategy(new GoPay());
        resultchoose.payBill(10000);

        resultchoose.setStrategy(new Ovo());
        resultchoose.payBill(50000);

        resultchoose.setStrategy(new Bank());
        resultchoose.payBill(20000);
    }
}
