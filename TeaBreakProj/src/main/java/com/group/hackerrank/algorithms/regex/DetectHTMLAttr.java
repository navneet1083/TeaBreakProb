package com.group.hackerrank.algorithms.regex;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL : https://www.hackerrank.com/challenges/html-attributes
 *
 * Charlie’s second assignment was given by the Professor as soon as he
 * submitted the first one. Professor asked Charlie to create a list of all the
 * attributes of every tag found in HTML pages.
 *
 * <p>This is a paragraph</p>
 * The above HTML string has p as its tag but no attributes.
 *
 * <a href="http://www.hackerrank.com">HackerRank</a>
 * This string has a as a tag and href as an attribute.
 *
 * Input Format
 *
 * The first line contains N, the number of lines in the HTML fragment. This is
 * followed by lines from a valid HTML document or fragment.
 *
 * Constraints
 *
 * Number of characters in the test fragments <= 10000 characters. Characters
 * will be restricted to ASCII. Fragments for the tests will be picked up from
 * Wikipedia. Attributes are all lowercase alphabets.
 *
 * Output Format
 *
 * Each tag must be separated by a newline arranged in lexicographic order Each
 * attribute noted for a given tag must be arranged next to a tag in
 * lexicographic order.
 *
 * The output will be of the format
 *
 * tag-1:attribute-1,attribute-2,attribute-3....
 * tag-2:attribute-1,attribute-2,attribute-3....
 * tag-3:attribute-1,attribute-2,attribute-3.... ...
 * tag-n:attribute-1,attribute-2,attribute-3.... Where tag-1 is
 * lexicographically smaller than tag-2 and attribute-1 is lexicographically
 * smaller than attribute-2
 *
 * Sample Input:1
 *
 * 2
 * <p><a href="http://www.quackit.com/html/tutorial/html_links.cfm">Example
 * Link</a></p>
 * <div class="more-info"><a
 * href="http://www.quackit.com/html/examples/html_links_examples.cfm">More Link
 * Examples...</a></div>
 * Sample Output:1
 *
 * a:href div:class p: Sample Input:2
 *
 * 13
 * <div class="portal" role="navigation" id='p-navigation'>
 * <h3>Navigation</h3>
 * <div class="body">
 * <ul>
 * <li id="n-mainpage-description"><a href="/wiki/Main_Page" title="Visit the
 * main page [z]" accesskey="z">Main page</a></li>
 * <li id="n-contents"><a href="/wiki/Portal:Contents" title="Guides to browsing
 * Wikipedia">Contents</a></li>
 * <li id="n-featuredcontent"><a href="/wiki/Portal:Featured_content"
 * title="Featured content the best of Wikipedia">Featured content</a></li>
 * <li id="n-currentevents"><a href="/wiki/Portal:Current_events" title="Find
 * background information on current events">Current events</a></li>
 * <li id="n-randompage"><a href="/wiki/Special:Random" title="Load a random
 * article [x]" accesskey="x">Random article</a></li>
 * <li id="n-sitesupport"><a
 * href="//donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&utm_medium=sidebar&utm_campaign=C13_en.wikipedia.org&uselang=en"
 * title="Support us">Donate to Wikipedia</a></li>
 * </ul>
 * </div>
 * </div>
 * Sample Output:2
 *
 * a:accesskey,href,title div:class,id,role h3: li:id ul: Viewing Submissions
 *
 * You can view others’ submissions if you solve this challenge. Navigate to the
 * challenge leaderboard.
 *
 */
public class DetectHTMLAttr {

    private static TreeMap<String, TreeSet<String>> tm = new TreeMap<String, TreeSet<String>>();

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        int noOfLines = in.nextInt();
        in.nextLine();

        for (int i = 0; i < noOfLines; i++) {

//            String inputStr = "<div class=\"more-info\"><a href=\"http://www.quackit.com/html/examples/html_links_examples.cfm\">More Link Examples...</a></div>";
            String inputStr = in.nextLine();
//        String regEx = "(\\S+)=(\\\"|'| |)(.*)(\\\"|'| |>)";
//        String regEx = "</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[^'\">\\s]+))?)+\\s*|\\s*)/?>";
//        String regEx = "<(?:[A-Za-z_:][\\w:.-]*(?=\\s)(?!(?:[^>\"\\']|\"[^\"]*\"|\\'[^\\']*\\')*?(?<=\\s)(?:term|range)\\s*=)(?!\\s*/?>)\\s+(?:\".*?\"|\\'.*?\\'|[^>]*?)+|/?[A-Za-z_:][\\w:.-]*\\s*/?)>";
            String regEx = "<(\\w+)((?:\\s+\\w+(?:\\s*=\\s*(?:(?:\"[^\"]*\")|(?:'[^']*')|[^>\\s]+))?)*)\\s*(\\/?)>";
            String attributeVal = "(\\S+)=[\"']?((?:.(?![\"']?\\s+(?:\\S+)=|[>\"']))+.)?[\"']?";
//        String tagName = "<\\w+(>| )";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(inputStr);
            while (matcher.find()) {
                boolean traverse = true;
                String input = matcher.group();
                String key = getTagName(input).trim();
                Pattern pattern2 = Pattern.compile(attributeVal);
                Matcher matcher2 = pattern2.matcher(input);
//                System.out.println("match found :" + key);
                while (matcher2.find()) {
                    traverse = false;
                    String val = matcher2.group().split("=")[0].trim();
                    putIntoMap(key, val);
//                    System.out.println("in 2nd traversal : " + val);
                }
                if (traverse) {
                    putIntoMap(key, "");
                }

//                System.out.println("********");
            }
        }

        Iterator<String> itr = tm.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            Iterator<String> itval = tm.get(key).iterator();
            StringBuilder sb = new StringBuilder();
            while (itval.hasNext()) {
                sb.append(itval.next()).append(",");
            }
            System.out.println(key + ":" + sb.toString().replaceAll(",$", ""));
        }

    }

    private static String getTagName(String input) {
        String retStr = "";
        String tagNameRegEx = "<\\w+(>| )";
        Pattern pattern = Pattern.compile(tagNameRegEx);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            retStr = matcher.group().replaceAll("<", "").replaceAll(">", "");
        }
        return retStr;
    }

    private static void putIntoMap(String key, String value) {
        if (tm.containsKey(key)) {
            TreeSet<String> temp = tm.get(key);
            temp.add(value);
            tm.put(key, temp);
        } else {
            TreeSet<String> temp = new TreeSet<String>();
            temp.add(value);
            tm.put(key, temp);
        }
    }
}
