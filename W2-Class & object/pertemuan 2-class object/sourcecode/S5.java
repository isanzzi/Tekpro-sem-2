import java.util.Scanner;

public class S5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1, s2;

        try {
            s1 = scan.next();
            s2 = scan.next();

            System.out.println(s1.length() + s2.length());

            if (s1.compareTo(s2) > 0) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

            s1 = toTitleCase(s1);
            s2 = toTitleCase(s2);

            System.out.print(s1 + " " + s2);
        } finally {
            scan.close();
        }
    }

    public static String toTitleCase(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] lower = s.toCharArray();
        lower[0] = Character.toUpperCase(lower[0]);
        return String.valueOf(lower);
    }
}
