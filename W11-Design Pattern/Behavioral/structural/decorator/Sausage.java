class Sausage extends PizzaToppingDecorator {
    public Sausage(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", Sausage";
    }

    public double cost() {
        return 1.25 + pizza.cost();
    }
}