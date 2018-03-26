package com.satya.learning.problems.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * For two given strings, print the longest sub-sequence that is common in both
 * strings.
 * 
 * @see <a href="https://www.geeksforgeeks.org/longest-common-subsequence">Geeks
 *      For Geeks : Longest Common Subsequence</a>
 * 
 * @author Satyendra
 *
 */
public class LargestCommonSubsequence {

	public static void main(String[] args) {
		Integer[] a1 = new Integer[] {4, 2, 7, 6, 8};
		Integer[] a2 = new Integer[] {5, 4, 4, 7, 8, 2};
		
		System.out.println(largestCommonSubsequence(Arrays.asList(a1), Arrays.asList(a2)));
	}
	

	private static <E> int largestCommonSubsequence(List<E> a1, List<E> a2) {
		int lenR = a1.size();
		int lenC = a2.size();
		
		int[][] m = new int[lenR+1][lenC+1];
		
		for (int r = 1; r <= lenR; r++) {
			for (int c = 1; c <= lenC; c++) {
				if (a1.get(r-1).equals(a2.get(c-1))) { 
					m[r][c] = m[r-1][c-1] + 1;
				} else {
					m[r][c] = Math.max(m[r-1][c], m[r][c-1]);
				}
			}
		}
		
		return m[lenR][lenC];
	}

}
