/**
 * **
 * **************************************************************************
 ******************************* COMPLETED ******************************
 * **************************************************************************
 */


/*
 * Sometimes you need to compare lists of number, but sorting each one normally will take too much time. Instead you can use alternative methods to find the differences between each list.

 Challenge
 Numeros The Artist was arranging two identical lists A and B into specific orders. The arrangements of the two arrays were random, Numeros was very proud of his arrangements. Unfortunately, some numbers got left out of List A. Can you find the missing numbers from A without messing up his order?

 Details
 There are many duplicates in the lists, but you need to find the extra numbers, i.e. B - A. Print the numbers in numerical order. Print each missing number once, even if it is missing multiple times. The numbers are all within a range of 100 from each other.

 Input Format
 There will be four lines of input:

 n - the size of the first list
 This is followed by n numbers that makes up the first list.
 m - the size of the second list
 This is followed by m numbers that makes up the second list.

 Output Format
 Output all the numbers (in ascending order) that are in B but not in A.

 Constraints
 1<= n,m <= 1000001
 -10000 <= x <= 10000 , x ∈ B
 Xmax - Xmin < 101

 Sample Input

 10
 203 204 205 206 207 208 203 204 205 206
 13
 203 204 204 205 206 207 205 208 203 206 205 206 204
 Sample Output

 204 205 206
 Explanation

 Although 204 presented in both arrays, but 204's frequency in A is smaller than that of B. Similarly 205 and 206 occur twice in A but thrice in B. So, these three numbers constitute the output. The rest of the numbers occur at least as many times in A as they do in B - so they are not "missing numbers".
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.group.hackerrank.algorithms.search;

import java.util.*;

public class MissingNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<Long, Integer> hm1 = new HashMap<Long, Integer>();
        HashMap<Long, Integer> hm2 = new HashMap<Long, Integer>();
        HashSet<Long> hs = new HashSet<Long>();
        TreeSet<Long> ts = new TreeSet<Long>();

        int firstLimit = in.nextInt();
        for (int i = 0; i < firstLimit; i++) {
            long num = in.nextLong();
            if (hm1.containsKey(num)) {
                int count = hm1.get(num) + 1;
                hm1.put(num, count);
            } else {
                hs.add(num);
                hm1.put(num, 1);
            }
        }

        int secondLimit = in.nextInt();
        for (int i = 0; i < secondLimit; i++) {
            long num = in.nextLong();
            if (hm2.containsKey(num)) {
                int count = hm2.get(num) + 1;
                hm2.put(num, count);
            } else {
                hs.add(num);
                hm2.put(num, 1);
            }
        }

        Iterator<Long> itr = hs.iterator();
        while (itr.hasNext()) {
            long val = itr.next();
//            if (!hm1.containsKey(val) || !hm2.containsKey(val)) {
//                ts.add(val);
//            } else if (hm1.get(val) != hm2.get(val)) {
//                ts.add(val);
//            }
            if (hm1.containsKey(val) && hm2.containsKey(val)) {
                if (!hm1.get(val).equals(hm2.get(val))) {
                    ts.add(val);
                }
            } else {
                ts.add(val);
            }
        }

        Iterator<Long> itrts = ts.iterator();
        while (itrts.hasNext()) {
            System.out.print(itrts.next() + " ");
        }

    }
}
