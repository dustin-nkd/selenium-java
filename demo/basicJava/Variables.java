package basicJava;

public class Variables {
    // Instance variable
    int instanceVar = 10;

    // Static variable (class variable)
    static String staticVar = "Hello, Java!";

    public void showVariables() {
        // Local variable
        int localVar = 5;
        System.out.println("Local variable: " + localVar);
        System.out.println("Instance variable: " + instanceVar);
        System.out.println("Static variable: " + staticVar);
    }

    public static void main(String[] args) {
        // Primitive data types
        byte byteVar = 127;
        short shortVar = 32000;
        int intVar = 100000;
        long longVar = 1000000000L;
        float floatVar = 10.5f;
        double doubleVar = 99.99;
        char charVar = 'A';
        boolean boolVar = true;

        // Print primitive data types
        System.out.println("Byte: " + byteVar);
        System.out.println("Short: " + shortVar);
        System.out.println("Int: " + intVar);
        System.out.println("Long: " + longVar);
        System.out.println("Float: " + floatVar);
        System.out.println("Double: " + doubleVar);
        System.out.println("Char: " + charVar);
        System.out.println("Boolean: " + boolVar);

        // Call method to display variables
        Variables obj = new Variables();
        obj.showVariables();
    }
}