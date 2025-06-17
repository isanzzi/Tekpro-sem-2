class Mushroom extends PizzaToppingDecorator {
    public Mushroom(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    public double cost() {
        return 0.75 + pizza.cost();
    }
}