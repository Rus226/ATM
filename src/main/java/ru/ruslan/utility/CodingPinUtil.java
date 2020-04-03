package ru.ruslan.utility;

import java.util.Random;

public class CodingPinUtil {
    private CodingPinUtil(){}

    private static Random random = new Random();

    public static String encode(String pin) {

        return pin.substring(0, 1) + random.nextInt(10) +
                pin.substring(1, 2) + random.nextInt(10) +
                pin.substring(2, 3) + random.nextInt(10) +
                pin.substring(3) + random.nextInt(10);
    }

    public static String decode(String newPin) {

        return newPin.substring(0,1) +
                newPin.substring(2,3) +
                newPin.substring(4,5) +
                newPin.substring(6,7);
    }
}
