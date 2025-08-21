public class IDCard {
    private String IDNumber;
    private String Pin;
    private double Balance;

    public IDCard(String IDNumber, String Pin, double Balance) {
        this.IDNumber = IDNumber;
        this.Balance = Balance;
        this.Pin = Pin;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double amount) {
        this.Balance = amount;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public String getPIN() {
        return Pin;
    }
}