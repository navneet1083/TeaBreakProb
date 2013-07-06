package com.group.hackerrank.contests.hackjuly;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <URL> https://www.hackerrank.com/contests/july13/challenges/game-of-thrones
 * 
 * King Robert has 7 kingdoms under his rule. He gets to know from Raven that
 * the Dothraki are to wage a war against him soon. But, he knows the Dothraki
 * need to cross the narrow river to enter his dynasty. There is only one bridge
 * that connects both sides of the river which is sealed by a huge door.
 *
 *
 *
 * The king wants to lock the door so that the Dothraki can’t enter. But, to
 * lock the door you need a key that is a palindrome of an anagram of a certain
 * string.
 *
 * The king has a list of words. For each given word, can you help the king in
 * figuring out if any anagram of it can be a palindrome or not?.
 *
 * Input Format A single line which will contain the input string
 *
 * Constraints 1<=length of string <= 10^5 Each character of the string is a
 * lowercase english alphabet.
 *
 * Output Format A single line containing YES/NO in capital letter of english
 * alphabet.
 *
 * Sample Input : 01
 *
 * aaabbbb Sample Output : 01
 *
 * YES Explanation One of the permutations of the given string which is a
 * palindrome is bbaaabb. * Sample Input : 02
 *
 * cdefghmnopqrstuvw Sample Output : 02
 *
 * NO Explanation You can verify that no permutation of the given string can be
 * a palindrome. Sample Input : 03
 *
 * cdcdcdcdeeeef
 *
 * Sample Output : 03
 *
 * YES Explanation One of the permutations of the given string which is a
 * palindrome is ddcceefeeccdd .
 *
 */
public class GamesOfThrones_1 {

    public static void main(String[] args) {
        int oddCount = 0;
        String message = "";
        Scanner in = new Scanner(System.in);
        String lineContent = in.nextLine();
        HashMap<Character, Integer> mappCh = new HashMap<Character, Integer>();
        char[] ch = lineContent.toCharArray();
        for (char c : ch) {
            if (mappCh.containsKey(c)) {
                mappCh.put(c, mappCh.get(c) + 1);
            } else {
                mappCh.put(c, 1);
            }
        }
        Iterator<Character> itr = mappCh.keySet().iterator();
        while (itr.hasNext()) {
            if (oddCount > 1) {
                message = "NO";
                System.out.println("NO");
                break;
            }
            Character key = itr.next();
            if ((mappCh.get(key) % 2) != 0) {
                oddCount++;
            }
        }
        if (message.equals("")) {
            System.out.println("NO");
        }
        if (!message.equals("NO")) {
            System.out.println("YES");
        }
    }
}
