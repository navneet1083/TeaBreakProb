/**
 * ############################################################################
 * ## COMPLETED ######################
 * ##############################################################
 */
package com.group.hackerrank.algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Given N integers [N<=10^5], count the total pairs of integers that have a
 * difference of K. [K>0 and K<1e9]. Each of the N integers will be greater than
 * 0 and at least K away from 2^31-1 (Everything can be done with 32 bit
 * integers).
 *
 * Input Format
 *
 * 1st line contains N & K (integers). 2nd line contains N numbers of the set.
 * All the N numbers are assured to be distinct.
 *
 * Output Format
 *
 * One integer saying the number of pairs of numbers that have a diff K.
 *
 * Sample Input #00:
 *
 * 5 2
 * 1 5 3 4 2
 *
 * Sample Output #00:
 *
 * 3
 *
 * Sample Input #01:
 *
 * 10 1 363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635
 * 491595254 879792181 1069262793  *
 * Sample Output #01:
 *
 * 0
 *
 */
public class Pairs {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();
        int k = in.nextInt();
        int count = 0;
//        System.out.println(" no of lines : " + noOfLines);
//        System.out.println(" k value is : " + k);

//        TreeSet<Integer> sortedNum = new TreeSet<Integer>();
        List<Integer> lstNum = new ArrayList<Integer>();

        for (int i = 0; i < noOfLines; i++) {
            int num = in.nextInt();
            lstNum.add(num);
//            System.out.println(" line by line : " + num);
        }
        Collections.sort(lstNum);

        for (int i = 0; i < lstNum.size(); i++) {
            for (int j = i + 1; j < lstNum.size(); j++) {
                if ((lstNum.get(j) - lstNum.get(i)) > k) {
                    break;
                } else {
                    if ((lstNum.get(j) - lstNum.get(i)) == k) {
                        count++;
                    }
                }
            }
        }

//        System.out.println("sorted tree is : " + lstNum);
//        System.out.println("count is : " + count);
        System.out.println(count);
    }
}
