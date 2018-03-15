package com.satya.learning.problems.dynamic;

import java.util.Scanner;

/**
 * Given a set of non-negative integers and a sum value. Determine if there is
 * subset of given set that adds to given sum value.
 * 
 * @author Satyendra
 *
 */
public class SubsetSum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		int sum = in.nextInt();
		in.close();

		boolean isSum = isSubsetSum(a, sum);
		
		System.out.println(isSum ? "YES" : "NO");
	}

	private static boolean isSubsetSum(int[] a, int sum) {
		int len = a.length;
		boolean[][] m = new boolean[len][sum+1];
		
		for (int r = 0; r < len; r++) {
			for (int c = 0; c <= sum; c++) {
				if (r==0) {
					if(c<=a[r]) {
						m[r][c] = true;
					}
				} else if (m[r-1][c] || m[r-1][c-a[r]]) {
					m[r][c] = true;
				}
				System.out.print(m[r][c] ? "T " : "F ");
			}
			System.out.println();
		}
		
		return m[len-1][sum];
	}

}
