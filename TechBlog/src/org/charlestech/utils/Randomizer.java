package org.charlestech.utils;

import java.io.IOException;
import java.nio.CharBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;

/*
 * @Description Get random string or number
 * @author Charles Chen
 * @date 14-4-3
 * @version 1.0
 */
public class Randomizer {
    private static Random randomizer = new Random();
    private static final char[] CAPITALS = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private static final char[] LOWERS = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
    private static final char[] NUMBERS = "1234567890".toCharArray();
    private static final char[] CHARACTERS = "!@#$%^&*_.,?\\/-`~".toCharArray();

    /**
     * Get random integer in range
     *
     * @param range
     * @return
     */
    public static int getRandInt(int range) {
        return randomizer.nextInt(range);
    }

    /**
     * Get a random double with specific maximum fraction digits
     *
     * @param digits
     * @return
     */
    public static double getRandDouble(int digits) {
        if (digits < 1) digits = 1;
        double randDouble = randomizer.nextDouble();
        String formatStr = "#.";
        for (int i = 0; i < digits; i++) {
            formatStr += "0";
        }
        DecimalFormat df = new DecimalFormat(formatStr);
        return Double.parseDouble(df.format(randDouble));
    }

    /**
     * Get random percentage with specific maximum fraction digits
     *
     * @param digits
     * @return
     */
    public static String getRandPercent(int digits) {
        if (digits < 0) digits = 0;
        double randDouble = randomizer.nextDouble();
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(digits);
        return nf.format(randDouble);
    }

    /**
     * Get random string
     *
     * @param length String length
     * @param mode   0: Mixed literals, numbers and characters, 1: Mixed literals and numbers, 2: Only literals, 3: Only numbers
     * @return
     */
    public static String getRandStr(int length, int mode) {
        if (length < 1) return null;
        String randStr = null;
        Scanner sc = null;
        char[] chars = null;
        switch (mode) {
            case 0:
                // Mixed string
                chars = (String.valueOf(CAPITALS) + String.valueOf(LOWERS) + String.valueOf(NUMBERS) + String.valueOf(CHARACTERS)).toCharArray();
                break;
            case 1:
                // Literals and numbers
                chars = (String.valueOf(CAPITALS) + String.valueOf(LOWERS) + String.valueOf(NUMBERS)).toCharArray();
                break;
            case 2:
                // Only literals
                chars = (String.valueOf(CAPITALS) + String.valueOf(LOWERS)).toCharArray();
                break;
            case 3:
                // Only numbers
                chars = NUMBERS;
            default:
                break;
        }
        sc = new Scanner(new RandomWords(chars, length));
        if (sc.hasNext()) randStr = sc.next();
        return randStr;
    }

    /**
     * Random words reader
     */
    static class RandomWords implements Readable {
        private char[] chars;
        private int length;
        private Random randomizer = new Random();

        public RandomWords(char[] chars, int length) {
            this.chars = chars;
            this.length = length;
        }

        public int read(CharBuffer cb) throws IOException {
            for (int i = 0; i < length; i++) {
                cb.append(chars[randomizer.nextInt(chars.length)]);
            }
            cb.append(" ");
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(getRandStr(10, 0));
    }
}
