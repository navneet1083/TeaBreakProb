package com.group.hackcoder.teabreakproj;

import com.group.hackcoder.ds.maths.MathUtils;

/**
 * Main calling class
 *
 */
public class App {

    public static void main(String[] args) {

        try {
            // Addition of two numbers with bitwise operators
            System.out.println("addition of two numbers : " + MathUtils.AddNosWithoutArithOpr(10, 5));
        } catch (Exception e) {
            System.out.println("Exception occured : " + e);
        }
    }
}