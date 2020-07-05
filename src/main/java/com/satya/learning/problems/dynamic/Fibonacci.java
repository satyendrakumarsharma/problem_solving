package com.satya.learning.problems.dynamic;

import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long start = System.nanoTime();

        System.out.println("Fibonacci(" + n + ")   : " + fibonacci(n));

        long end = System.nanoTime();
        System.out.println("Time non-DP Fibonacci : " + (double)(end - start)/1000000d + " Milli Seconds");

        start = System.nanoTime();

        long[] mem = new long[n+1];
        for (long l : mem) {
            System.out.print(l + " ");
        }
        System.out.println();
        System.out.println("FibonacciDP(" + n + ") : " + fibonacciDP(n, mem));

        end = System.nanoTime();
        System.out.println("Time DP Fibonacci     : " + (double)(end - start)/1000000d + " Milli Seconds");

        in.close();
    }

    private static long fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    private static long fibonacciDP(int n, long[] mem) {
        if (n < 2) {
            return n;
        }
        if (mem[n] > 0) {
            return mem[n];
        }
        long feb = fibonacciDP(n - 2, mem) + fibonacciDP(n - 1, mem);
        mem[n] = feb;
        return feb;
    }
}
