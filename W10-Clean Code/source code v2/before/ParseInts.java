// ****************************************************************
// ParseInts.java
//
// Reads a line of text and prints the integers in the line.
//
// ****************************************************************
import java.util.Scanner;
public class ParseInts{
    public static void main(String[] args){
        int val, sum=0;
        Scanner scan = new Scanner(System.in);
        String line;
        System.out.println("Enter a line of text");
        Scanner scanLine = new Scanner(scan.nextLine());
        while (scanLine.hasNext()) {
            try{
                val = Integer.parseInt(scanLine.next());
                sum += val;
            } catch (NumberFormatException e) {  // Lanjutkan ke token berikutnya tanpa melakukan apa-apa
            }
        }
        System.out.println("The sum of the integers on this line is " + sum);
    }
}