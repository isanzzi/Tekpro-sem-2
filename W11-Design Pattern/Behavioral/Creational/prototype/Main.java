public class Main {
    public static  void main (String[] args){
        Warrior original = new Warrior("vandal", 160);
        System.out.println("Warrior asal 160");
        original.setStrength(156);
        System.out.println("Warrior strength rubah ke 156");

        Warrior copy= (Warrior) original.clone();

        System.out.println("Original : "+ original);
        System.out.println("Copy : "+copy);
    }
}
