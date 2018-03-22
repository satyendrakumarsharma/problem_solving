package com.satya.learning.problems.dynamic;

import java.util.Scanner;

/**
 * A program to identify the largest substring of a given string that has unique
 * characters.
 * 
 * @author Satyendra
 *
 */
public class LargestUniqueSubstring {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		
		String substr = getLargestUniqueSubstring(str);
		System.out.println(substr);
		
		in.close();
	}

	private static String getLargestUniqueSubstring(String str) {
		
		return null;
	}
}
