package com.group.hackerrank.algorithms.search;

import java.util.Scanner;

/**
 * URL ==> https://www.hackerrank.com/challenges/encryption
 *
 * One classic method for composing secret messages is called a square code. The
 * spaces are removed from the english text and the characters are written into
 * a square (or rectangle). The width and height of the rectangle have the
 * constraint,
 *
 * floor(sqrt(word)) <= width, height <= ceil(sqrt(word))
 *
 * Among the possible squares, choose the one with the minimum area.
 *
 * in case of rectangle number of rows is kept smaller than number of columns) .
 * For example, the sentence “if man was meant to stay on the ground god would
 * have given us roots” is 54 characters long, so it is written into a rectangle
 * with 7 rows and 8 columns.
 *
 * ifmanwas meanttos tayonthe groundgo dwouldha vegivenu sroots
 *
 * The coded message is obtained by reading down the columns going left to
 * right. For example, the message above is coded as:
 *
 * imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau
 *
 * You will be given a message in english with no spaces between the words.The
 * maximum message length be 81 characters. Print the encoded message. In the
 * encoded message there should be a space between any two consecutive words.
 *
 * Here are some more examples:
 *
 * Sample Input:
 *
 * haveaniceday
 *
 * Sample Output:
 *
 * hae and via ecy
 *
 * Sample Input:
 *
 * feedthedog  *
 * Sample Output:
 *
 * fto ehg ee dd
 *
 * Sample Input:
 *
 * chillout
 *
 * Sample Output:
 *
 * clu hlt io
 *
 *
 */
public class Encryption {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String inputContent = in.nextLine();
        inputContent = inputContent.trim();

        char[] c = inputContent.toCharArray();
        int len = inputContent.length();
        double width = Math.floor(Math.sqrt(len));
        double height = Math.ceil(Math.sqrt(len));
//        System.out.println("width is : " + width);
//        System.out.println("height is : " + height);
        int h = Integer.parseInt(String.valueOf(height).replaceAll(".0", ""));
        int w = Integer.parseInt(String.valueOf(width).replaceAll(".0", ""));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = i; j < len; j = j + h) {
//                System.out.print(c[j]);
                sb.append(c[j]);
            }
//            System.out.print(" ");
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
