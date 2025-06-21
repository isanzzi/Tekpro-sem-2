public class PizzaOrder {
    public static void main(String[] args) {
        Pizza pizza = new Margherita();
        pizza = new Cheese(pizza);
        pizza = new Mushroom(pizza);
        pizza = new Sausage(pizza);

        System.out.println(pizza.getDescription()); // Margherita, Extra Cheese, Mushroom, Sausage
        System.out.println("Total Price: $" + pizza.cost()); // 5.00 + 1.00 + 0.75 + 1.25 = 8.00
    }
}
