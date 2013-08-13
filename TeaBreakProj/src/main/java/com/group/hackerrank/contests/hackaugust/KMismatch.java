/**
 * ############################################################################
 * ## NOT COMPLETED ######################
 * ##############################################################
 */
package com.group.hackerrank.contests.hackaugust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class KMismatch {

    private static HashMap<Integer, ArrayList<String>> mapVal = new HashMap<Integer, ArrayList<String>>();
    private static int K = 0;
    private static int count = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        K = in.nextInt();
        in.nextLine();
        String val = in.nextLine();

        getContinousSubstringList(val);
    }

    private static void getContinousSubstringList(String inputStr) {
        List<String> chList = new ArrayList<String>();
        char[] ch = inputStr.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            String test = String.valueOf(c);
            chList.add(test);
            putIntoHashMap(mapVal, test);
            for (int j = i + 1; j < ch.length; j++) {
                char d = ch[j];
                test = test + String.valueOf(d);
                chList.add(test);
                putIntoHashMap(mapVal, test);
            }
        }
        calMisMatch();
//        System.out.println("ch is : " + chList);
//        System.out.println("map val is : " + mapVal);
//        System.out.println("count is : " + count);
        System.out.println(count);
    }

    private static void putIntoHashMap(HashMap<Integer, ArrayList<String>> hm, String val) {
        int len = val.length();
        ArrayList<String> str = new ArrayList<String>();
        if (hm.get(len) != null) {
            str = hm.get(len);
        }
        str.add(val);
        hm.put(len, str);
    }

    private static void calMisMatch() {
        Set<Integer> keySet = mapVal.keySet();
        Iterator<Integer> keySetItr = keySet.iterator();

        while (keySetItr.hasNext()) {
            int keys = keySetItr.next();

            ArrayList<String> valList = mapVal.get(keys);

            for (int i = 0; i < valList.size(); i++) {
                String firstVal = valList.get(i);
                char[] firstCharArr = firstVal.toCharArray();
                for (int j = i + 1; j < valList.size(); j++) {
                    String tempVal = valList.get(j);
                    char[] tempValCharArr = tempVal.toCharArray();
                    int tempCount = 0;
                    boolean tempBool = false;
                    for (int k = 0; k < firstCharArr.length; k++) {
                        if (firstCharArr[k] != tempValCharArr[k]) {
                            tempBool = true;
                            tempCount++;
                        }
//                        if (tempCount > K) {
//                            tempBool = false;
//                            break;
//                        }
                    }
                    if (tempCount <= K) {
                        System.out.println("mismatch contents are : [" + firstVal + "][" + tempVal + "]");
                        count++;
                    }
                }
            }

        }

    }
}
