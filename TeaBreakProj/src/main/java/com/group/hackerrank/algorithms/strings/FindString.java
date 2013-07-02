/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.hackerrank.algorithms.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class FindString {

    private static TreeSet<String> unionAllSet = new TreeSet<String>();
    private static ArrayList<String> unionAllArr = new ArrayList<String>();
    private static HashMap<String, String> unionAllMap = new HashMap<String, String>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();
        in.nextLine();
        for (int i = 0; i < noOfLines; i++) {
            String inputStr = in.nextLine();
            getCombinations(inputStr);
//            System.out.println("combination of " + i + "th line : " + getCombinations);
        }
        int maxLength = unionAllSet.size();
        int noOfOutputLines = in.nextInt();
        in.nextLine();
        //convert into array
        ArrayList<String> arrIndx = new ArrayList<String>();
        Iterator<String> itr = unionAllSet.iterator();
        while (itr.hasNext()) {
            arrIndx.add(itr.next());
        }
        for (int i = 0; i < noOfOutputLines; i++) {
            int index = in.nextInt();
            if (index < maxLength + 1) {
//                System.out.println("found : " + arrIndx.get(index - 1));
                System.out.println(arrIndx.get(index - 1));
            } else {
//                System.out.println("not found : INVALID");
                System.out.println("INVALID");
            }
        }
//        System.out.println("printing unionAll : " + unionAllSet);
    }

    private static void getCombinations(String inputStr) {
        int length = inputStr.length();
//        TreeSet<String> arr = new TreeSet<String>();
        for (int i = 0; i < length; i++) {
            char firstStr = inputStr.charAt(i);
            StringBuilder temp = new StringBuilder();
//            arr.add(Character.toString(firstStr));
//            unionAllSet.add(Character.toString(firstStr));
//            unionAllArr.add(Character.toString(firstStr));
            unionAllMap.put(Character.toString(firstStr), "");
            temp.append(firstStr);
            for (int j = i + 1; j < length; j++) {
                char secondComb = inputStr.charAt(j);
                temp.append(secondComb);
//                arr.add(temp.toString());
//                unionAllSet.add(temp.toString());
//                unionAllArr.add(temp.toString());
                unionAllMap.put(temp.toString(), "");
            }
        }
//        return arr;
    }
}
