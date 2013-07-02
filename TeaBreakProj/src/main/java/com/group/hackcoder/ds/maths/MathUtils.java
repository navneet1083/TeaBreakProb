package com.group.hackcoder.ds.maths;

/**
 * This class is responsible for the computations of all mathematical operations
 * and expose methods according to that.
 *
 * @author navneet.singh
 */
public class MathUtils {

    /**
     * Computation of addition of two numbers without using any Arithmetic
     * operators and it is done by bitwise operators.
     *
     * @param n1 - first number as int primitive
     * @param n2 - second number as int primitive
     * @return - an int number
     * @throws Exception
     */
    public static int AddNosWithoutArithOpr(int n1, int n2) throws Exception {
        int firstComputation = (n1 & n2) << 1;
        int secondComputation = (n1 ^ n2 ^ firstComputation);
        return secondComputation;
    }
}
