/**
 * ############################################################################
 * ## NOT COMPLETED ######################
 * ##############################################################
 */
package com.group.hackerrank.contests.hackjuly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class HackerRank {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int firstNum = in.nextInt();
        int secondNum = in.nextInt();

        HashSet<Integer> leftComb = new HashSet<Integer>();
        HashSet<Integer> rightComb = new HashSet<Integer>();
        HashSet<Integer> temp = new HashSet<Integer>();
        ArrayList<Integer> ai = new ArrayList<Integer>();
        ArrayList<Integer> aj = new ArrayList<Integer>();
        int sum = 0;

        for (int i = 1; i <= firstNum; i++) {
            for (int j = 1; j <= secondNum; j++) {
                int firstVal = i * j;
                if (!leftComb.contains(firstVal)) {
                    int secondVal = i ^ j;
                    if (!rightComb.contains(secondVal)) {
                        ai.add(firstVal);
                        aj.add(secondVal);
                        String val = String.valueOf(firstVal) + String.valueOf(secondVal);
                        temp.add(Integer.parseInt(val));
                        System.out.println("val is : " + val);
                        sum += Integer.parseInt(val);
                        sum += computeOther(ai, aj, temp);
                    }
                    leftComb.add(firstVal);
                    rightComb.add(secondVal);
                }
            }
        }

//        System.out.println("left combination is : " + leftComb);
//        System.out.println("right combination is : " + rightComb);
//        computePossibleCombinations(leftComb, rightComb);
        System.out.println(sum);
//        System.out.println(computePossibleCombinations(leftComb, rightComb));

    }

    private static int computeOther(ArrayList<Integer> ai,
            ArrayList<Integer> aj, HashSet<Integer> temp) {
        int sum = 0;
        for (int i = 0; i < ai.size(); i++) {
            for (int j = 0; j < aj.size(); j++) {
                String val = String.valueOf(ai.get(i)) + String.valueOf(aj.get(j));
                if (!temp.contains(Integer.parseInt(val))) {
                    sum += Integer.parseInt(val);
                    temp.add(Integer.parseInt(val));
                }
            }
        }
        return sum;


    }

    private static int computePossibleCombinations(HashSet<Integer> leftComb,
            HashSet<Integer> rightComb) {
        int sum = 0;
        Iterator<Integer> leftItr = leftComb.iterator();
        Iterator<Integer> rightItr = rightComb.iterator();
        while (leftItr.hasNext()) {
            int val = leftItr.next();
            rightItr = rightComb.iterator();
            while (rightItr.hasNext()) {
                String comb = String.valueOf(val) + String.valueOf(rightItr.next());
//                System.out.println("comb is : " + comb);
                sum += Integer.parseInt(comb);
            }
        }
//        System.out.println("sum is : " + sum);
        return sum;
    }
}
