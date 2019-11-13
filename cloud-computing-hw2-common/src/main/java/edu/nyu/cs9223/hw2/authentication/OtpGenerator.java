package edu.nyu.cs9223.hw2.authentication;

import java.util.Random;

/**
 * @author wwrus
 */
public class OtpGenerator {

    private static final String CAPITAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*_=+-/.?<>)";

    private static final int DEFAULT_LENGTH = 6;

    private OtpGenerator() {}

    public static String generate(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String values = CAPITAL_CHARS + SMALL_CHARS +
                NUMBERS + SYMBOLS;
        for (int i = 0; i < length; i++)
        {
            sb.append(values.charAt(random.nextInt(values.length())));
        }
        return sb.toString();
    }

    public static String generate() {
        return generate(DEFAULT_LENGTH);
    }
}
