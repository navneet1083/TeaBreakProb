/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.codeforces.contests396;

import java.util.Scanner;

public class ProblemB {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();

        for (int i = 0; i < noOfLines; i++) {
            int num = in.nextInt();
            

        }


        int a = 21;
        int b = 90;
        Fraction f1 = new Fraction(a, b);
        System.out.println("f1 is : " + f1);
    }

    private static void computeFraction() {
    }

    static class Fraction {

        private int numerator;
        private int denominator;

        //constructor for building fractions
        public Fraction(int num, int denom) {
            int gcf = gcf(num, denom);
            numerator = num / gcf;
            denominator = denom / gcf;
        }

        //simplifying fractions
        private int gcf(int a, int b) {
            return a % b == 0 ? b : gcf(b, a % b);
        }

        //printing the fraction
        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }
}
