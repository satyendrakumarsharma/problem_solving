package com.satya.learning.problems.dynamic;

import java.util.Scanner;

/**
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 * 
 * A subsequence is a sequence that can be derived from an array by deleting
 * some or no elements without changing the order of the remaining elements. For
 * example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 * <pre>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * </pre>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/longest-increasing-subsequence">Leet Code
 *      - Longest Increasing Subsequence</a>
 * 
 * @author Satyendra
 *
 */
public class LongestIncreasingSubsequence {

	/**
	 * Time complexity : O(n²)
	 * Space complexity : O(n)
	 * 
	 * @param nums the array of integers
	 * @return size of the largest increasing subsequence
	 */
	public static int lengthOfLongestIncreasingSubsequence(int[] nums) {
		int maxLen = 1;
		int[] seqMax = new int[nums.length];

		for (int i = 0; i < seqMax.length; i++) {
			seqMax[i] = 1;
		}

		for (int from = 0; from < nums.length; from++) {
			for (int to = from + 1; to < nums.length; to++) {
				int nextIncreasingVal = seqMax[from] + 1;
				if (nums[from] < nums[to] && nextIncreasingVal > seqMax[to]) {
					seqMax[to] = nextIncreasingVal;
					if (seqMax[to] > maxLen) {
						maxLen = seqMax[to];
					}
				}
			}
		}

		return maxLen;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the size of array: ");
		int size = s.nextInt();
		int[] arr = new int[size];
		System.out.println("Enter each of " + size + " elements:");
		for (int idx = 0; idx < size; idx++) {
			arr[idx] = s.nextInt();
		}
		int lis = lengthOfLongestIncreasingSubsequence(arr);
		System.out.print("Longest Increasing Subsequence is of size: " + lis);
		s.close();
	}

}