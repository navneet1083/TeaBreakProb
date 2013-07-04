package com.group.hackerrank.machinelearning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * A Basic Spell Checker
 *
 * This challenge will introduce you to the basics of Spell Checking. Right from
 * what you type in a search box, or the red squiggles you see as you enter text
 * via your browser, or the articles you write using an online or offline word
 * processor; spell checking is an important tool on the PC and on the Internet.
 *
 * Most frequent spelling mistakes
 *
 * People are prone to making spelling mistakes as they type in a hurry. It has
 * been observed that the most common spelling mistakes occur for the following
 * reasons:
 *
 * Deletion, Replacement, Transposition, Insertion Assume only the letters a-z
 * are involved.
 *
 * One character in the string gets deleted incorrectly. Example: The user
 * enters Ordnary instead of Ordinary (i.e. leaves out the i)
 *
 * One character in the string is incorrectly replaced by another one. Example:
 * The user enters Accedent instead of Accident.
 *
 * While typing hurriedly, the user ends up swapping one pair of consecutive
 * characters. Example: The user enters Noramlly instead of Normally.
 *
 * The user ends up inserting one extra character somewhere in the string.
 * Example: The user enters Heello instead of Hello. The extra character will
 * only be a letter from [a-z] for the purpose of solving this problem.
 *
 * So, generally, the correct string is just one step of one edit distance away
 * from what the user erroneously types in.
 *
 * Please take note, that in each of the four popular cases above, the mistake
 * occurs only at one particular character (or, pair of characters in case 3).
 *
 * If a spell checker is able to detect these simple but common mistakes, it
 * will be able to handle sixty to seventy percent of all spelling mistakes
 * which people make while typing text on their computers.
 *
 * What you need to do
 *
 * You will be provided with a Corpus of text which you can read in as a file in
 * your program. Assume it is placed inthe same folder as your program. Read in
 * this text, and build up a dictionary of words and the frequencies with which
 * those words occur.Words are string of letters, and they might contain hyphens
 * and/or apostrophes. The end of the corpus file is marked by “END-OF-CORPUS”
 *
 * In case you would like to try out the corpus locally, download this file, and
 * use it from the same directory as your program code: corpus
 *
 * Then, via the standard input, you will be provided with a set of (possibly)
 * mistyped words.
 *
 * Your program should recommend the likeliest known word from the dictionary
 * you built up, for each of those mistyped words. If the given word exists in
 * your dictionary, output it as it is.
 *
 * Guidelines
 *
 * Consider the four popular mistakes described earlier in the description.
 * Think of the candidate words which might have led to the given mis-typed
 * versions.
 *
 * Among the candidate words, restrict your choice to the words which do exist
 * in your dictionary. Among these, pick up the word which occurred most
 * frequently, as the best possible suggestion which you can find. If there are
 * multiple such words which occurred most frequently, then output the one which
 * occurs first in lexicographical order.
 *
 * If your program cannot come up with any suggestion, output the original
 * (possibly mis-typed) word itself.
 *
 * Output should be in lower case.
 *
 * For the purpose of building a dictionary and language model, details about
 * the corpus have already been provided.
 *
 * Input Format
 *
 * The first line of the input contains N. N lines follow, each line having 1
 * possibly misspelt word.
 *
 * Output Format
 *
 * For each word, output the correct spelling of the word. If the word is not
 * misspelt, print it as is.
 *
 * Sample Input
 *
 * 5
 * contan seroius pureli dose note
 *
 * Sample Output
 *
 * contain serious purely dose note
 *
 * Explanation
 *
 * 5 words are given all of them are misspelt. Each word in the output is the
 * correct spelling of the corresponding misspelt word of the input.
 *
 * Note
 *
 * For this problem, as described in the statement, only stick to generating
 * suggestions which are just one “edit distance” or “mistake” away from the
 * original word, otherwise the answer we expect will be different from yours.
 *
 * Corpus can be downloaded here
 *
 * Scoring
 *
 * Scoring is proportional to the answers you compute correctly.
 *
 * Score for each test case = (100 * correctAnswers/TotalNumberOfTests)
 *
 * Total Score = Average of Scores for all test cases which are run on your
 * submission.
 *
 ** A Note Regarding the Corpus **
 *
 * We are aware of the fact that the corpus might have more spelling errors than
 * desired, but we are also constrained by the fact that there are only a few
 * sources which we can use without violating terms and conditions of providers.
 *
 */
public class BasicSpellChecker {

    /**
     * HOLDING DICTIONARY WORDS
     */
    private static HashMap<String, Integer> dictionary = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try {
            File f = new File("corpus.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String lineContent;
            while ((lineContent = br.readLine()) != null) {
                if (lineContent.contains("END_OF_CORPUS")) {
                    break;
                }
                for (String tokenStr : lineContent.toLowerCase().split(" ")) {
                    int newVal = 1;
                    if (dictionary.containsKey(tokenStr)) {
                        newVal = dictionary.get(tokenStr) + 1;
                    }
                    dictionary.put(tokenStr, newVal);
                }
            }
//            System.out.println("dictionary completed");
//            System.out.println("seroius  " + dictionary.get("serious"));

            /**
             * Read the input to get the words
             */
            Scanner in = new Scanner(System.in);
            int noOfLines = in.nextInt();
            in.nextLine();
            for (int i = 0; i < noOfLines; i++) {
                String scanWord = in.nextLine();
                System.out.println(lookUpWord(scanWord.trim()));
//                System.out.println("Result is : " + lookUpWord(scanWord.trim()));
            }
//            System.out.println("seroius  word in dictionary " + lookUpWord("seroius"));



        } catch (Exception e) {
            System.out.println("Exception occured : " + e);
        }
    }

    /**
     * This method is responsible for look-up of given words with all possible
     * combinations of word given in problem and get MAXIMUM occurrences of a
     * word in dictionary.
     *
     * @param word
     * @return
     */
    private static String lookUpWord(String word) {

        HashSet<String> allPossibleComb = combinations(word);
//        System.out.println("all possible comb : " + allPossibleComb);
        //Map to hold all possible matches
        HashMap<Integer, String> possMatch = new HashMap<Integer, String>();
        for (String str : allPossibleComb) {
            if (dictionary.containsKey(str)) {
                int key = dictionary.get(str);
                possMatch.put(key, str);
            }
        }

        if (possMatch.size() > 0) {
//            System.out.println("possmatch : " + possMatch);
//            System.out.println("distance algo : " + temp);
            return possMatch.get(Collections.max(possMatch.keySet()));
        } else {
            return word;
        }
    }

    /**
     * This method is responsible of creating all possible combination of a
     * given word w.r.t conditions given in problem statement.
     *
     * @param word
     * @return
     */
    private static HashSet<String> combinations(String word) {
        if (word == null || word.isEmpty()) {
            return null;
        }
        HashSet<String> list = new HashSet<String>();
        String w = null;
        /**
         * DELETION
         */
        for (int i = 0; i < word.length(); i++) {
            w = word.substring(0, i) + word.substring(i + 1);
            list.add(w);
        }
        /**
         * TRANSPOSE
         */
        for (int i = 0; i < word.length() - 1; i++) {
            w = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
            list.add(w);
        }
        /**
         * REPLACE
         */
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                w = word.substring(0, i) + c + word.substring(i + 1);
                list.add(w);
            }
        }
        /**
         * INSERTION
         */
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                w = word.substring(0, i) + c + word.substring(i);
                list.add(w);
            }
        }

        return list;
    }

    /**
     * Below methods have not been used in this class but can be used further.
     * TODO : NEED TO MOVED IN PROPER PACKAGE
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static int computeLevenshteinDistance(CharSequence str1,
            CharSequence str2) {
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distance[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]
                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                        : 1));
            }
        }

        return distance[str1.length()][str2.length()];
    }
}
