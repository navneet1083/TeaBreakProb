/**
 * ############################################################################
 * ## COMPLETED ######################
 * ##############################################################
 */
package com.group.hackerrank.contests.hackaugust;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *
 * <URL> https://www.hackerrank.com/contests/quantium/challenges/koane-eye
 *
 * Koane eye Planet Zorg is inhabited by Koanes. Koanes vision is topsy turvy.
 *
 * For example,
 *
 * for humans the text is visible as
 *
 * 123 223 323 423 523 623 for Koanes, the same text is seen as
 *
 * 326 325 324 323 322 321 Task
 *
 * Your task is to take a read a set of characters and print the Koanes format
 * of the same characters as output.
 *
 * Constraints
 *
 * The testcases are similar to the ones mentioned in the example above.
 */
public class KoaneEye {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        String inputStr = "";
        while ((inputStr = in.nextLine()) != null) {
            if (inputStr.length() == 0) {
                break;
            }
            int input = Integer.parseInt(inputStr);
            arr.add(input);
        }
        for (int i = arr.size() - 1; i >= 0; i--) {
            int val = arr.get(i);
            System.out.println(reverseInteger(val));
        }

    }

    private static int reverseInteger(int n) {
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10;
            reverse = reverse + n % 10;
            n = n / 10;
        }
        return reverse;
    }
}
