package com.satya.learning.problems.dynamic;

import java.util.Scanner;

/**
 * Given two Strings, calculate the minimum number of edits needed in first
 * string to get converted into the second string.
 * <p>
 * Edit : Insert or Remvoe or Update
 * 
 * <p>
 * GeekForGeeks: <a href=
 * "http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance">Mininum
 * Edit Distance</a>
 * 
 * @author Satyendra
 *
 */
public class MinimumEditDistance {

	private static int minEdit(String s1, String s2) {
		int len1 = s1.length(), len2 = s2.length();
		int[][] m = new int[len2 + 1][len1 + 1];

		for (int c = 0; c <= len1; c++)
			m[0][c] = c;
		for (int r = 0; r <= len2; r++)
			m[r][0] = r;

		for (int r = 1; r <= len2; r++) {
			for (int c = 1; c <= len1; c++) {
				if (s1.charAt(c - 1) == s2.charAt(r - 1)) {
					m[r][c] = m[r - 1][c - 1];
				} else {
					int edit = m[r - 1][c - 1];
					int add = m[r - 1][c];
					int del = m[r][c - 1];
					int minChange = Math.min(Math.min(edit, add), del);
					m[r][c] = minChange + 1;
				}
			}
		}
		return m[len2][len1];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine();
		String s2 = in.nextLine();
		int minEditCount = minEdit(s1, s2);
		System.out.println(minEditCount);
		in.close();
	}

}
