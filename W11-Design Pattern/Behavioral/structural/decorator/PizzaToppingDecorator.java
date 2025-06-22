abstract class PizzaToppingDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}