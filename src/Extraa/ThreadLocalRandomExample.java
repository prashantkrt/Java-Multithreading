package Extraa;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomExample {
    public static void main(String[] args) {
        // Generate a random integer between 1 (inclusive) and 10 (exclusive)
        int randomInt = ThreadLocalRandom.current().nextInt(1, 10);

        // Generate a random double between 0.0 (inclusive) and 1.0 (exclusive)
        double randomDouble = ThreadLocalRandom.current().nextDouble();
        System.out.println(randomDouble);

        randomDouble =ThreadLocalRandom.current().nextDouble(10,20);


        // Generate a random long between 100 (inclusive) and 200 (exclusive)
        long randomLong = ThreadLocalRandom.current().nextLong(100, 200);

        System.out.println("Random Int: " + randomInt);
        System.out.println("Random Double: " + randomDouble);
        System.out.println("Random Long: " + randomLong);
    }
}