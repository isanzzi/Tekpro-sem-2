class Cheese extends PizzaToppingDecorator {
    public Cheese(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    public double cost() {
        return 1.00 + pizza.cost();
    }
}