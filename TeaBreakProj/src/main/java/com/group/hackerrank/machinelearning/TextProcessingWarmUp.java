package com.group.hackerrank.machinelearning;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * This problem will help you warm up and practice basic text and string
 * processing techniques. This will be a first step towards more complex Text
 * and Natural Language Processing and Analysis tasks.
 *
 * You will be given a fragment of text.
 *
 * In this fragment, you need to identify the articles used (i.e., ‘a’, ‘an’,
 * ‘the’).
 *
 * And you also need to identify dates (which might be expressed in a variety of
 * ways such as ‘15/11/2012’,’15/11/12’, ‘15th March 1999’,’15th March 99’ or
 * ‘20th of March, 1999’).
 *
 * You can make the following assumptions 1) In the date, year and day will
 * always be in numeric form. Which means, you don’t have to worry about
 * “fifteenth” or “twentieth” etc. Month, could be either numeric form (1-12) or
 * with its name (January-December, Jan-Dec).
 *
 * 2) This is a bit open ended, and somewhat intentionally so. The aim is for
 * you to try to write something which figures out as many common patterns as
 * possible, in which dates are present in text.
 *
 * 3) Most of the test cases are Wikipedia articles. Having a look at the common
 * formats in which dates occur in those, will help.
 *
 * 4) Dates could either be in the form: Month followed by Day followed by Year,
 * or Day followed by Month followed by Year.
 *
 * 5) The day could be in the form of either (1,2,3,…31) or (1st, 2nd,
 * 3rd…31st).
 *
 * A fragment is a valid date if it contains day, month and year information
 * (all three of them should be present). To extract date information, you will
 * need to try detecting different kinds of representations of dates, some of
 * which have been shown above. The more patterns you match and identify
 * correctly, the greater your score will be.
 *
 * Input Format
 *
 * First line contains the number of test cases T. This is followed by T test
 * fragments (each fragment will be in one line and each will have a blank line
 * after it) . Each line contains a paragraph of text in which you need to
 * identify the articles and dates. There will be a blank line after each
 * paragraph.
 *
 * So, totally there are 2T+1 lines in the input file. The last one is a blank
 * line after the last text fragment.
 *
 * Output Format
 *
 * 4T lines, four lines of output for each test case. First line -> number of
 * occurrences of ‘a’. Second line -> number of occurrences of ‘an’. Third Line
 * -> number of occurrences of ‘the’. Fourth Line -> number of occurrences of
 * date information.
 *
 *
 * @author navneet.singh
 */
public class TextProcessingWarmUp {

    public static void main(String[] args) {
        String patternDate = "((\\d{2}/\\d{2}/(\\d{4}|\\d{2}))|((\\d{2}|\\d{1})?(st|nd|rd|th)?(\\s|\\sof\\s)"
                + "((?i)January|February|March|April|May|June|July|August|September|October|November|December)"
                + "(((\\s|,)(\\d{4}|\\d{2}([^a-zA-Z])))|((\\d{2}([^a-zA-Z]))(|,)(\\s\\d{4})))))";
        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();
        in.nextLine();
        for (int i = 0; i < noOfLines; i++) {
            String inputStr = in.nextLine();
            in.nextLine();
            int aCount = computeCount(inputStr, "[^a-zA-Z](?i)a[^a-zA-Z]");
//            System.out.println("a occurs count : " + aCount);
//            System.out.println("an count : " + computeCount(inputStr, " (?i)an "));
//            System.out.println("the count : " + computeCount(inputStr, "([^a-zA-Z]|)(?i)the[^a-zA-Z]"));
//            System.out.println("date count : " + computeCount(inputStr, patternDate));
            System.out.println(aCount);
            System.out.println(computeCount(inputStr, "[^a-zA-Z](?i)an[^a-zA-Z]"));
            System.out.println(computeCount(inputStr, "([^a-zA-Z]|)(?i)the[^a-zA-Z]"));
            System.out.println(computeCount(inputStr, patternDate));
        }
    }

    private static int computeCount(String inputStr, String patternStr) {
        int count = 0;
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find()) {
            ++count;
        }
        return count;
    }
}
