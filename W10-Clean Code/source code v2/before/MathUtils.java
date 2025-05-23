// ****************************************************************
// MathUtils.java
//
// Provides static mathematical utility functions.
//
// ****************************************************************
public class MathUtils{
    //-------------------------------------------------------------
// Returns the factorial of the argument given
//-------------------------------------------------------------
    public static int factorial(int n) throws IllegalArgumentException{
        if (n<0){
            throw new IllegalArgumentException("Faktorial tidak didefinisikan untuk bilangan negatif");
        }
        if (n>16){
            throw new IllegalArgumentException("Nilai terlalu besar, akan menyebabkan overflow (maksimum adalah 16)");
        }
        int fac = 1;
        for (int i=n; i>0; i--)
            fac *= i;
        return fac;
    }
}