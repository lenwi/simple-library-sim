package ui;

import java.util.Random;

public class randomtest {
    //public static void main(String[] args) {
        //Random r = new Random();
        //int low = 0;
        //int high = 2;
        //int result = r.nextInt(high-low) + low;
        //System.out.println(result);
        //System.out.println(result);
        //System.out.println(result);
    //}

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomNumberInRange(0, 2));
        }

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
