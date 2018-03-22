package com.satya.learning.problems.dynamic;

import java.util.Scanner;

/**
 * @author Satyendra
 *
 */
public class GridlandMetro {

	private static int gridlandMetro(int n, int m, int k, int[][] track) {
		int light = 0;

		return light;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		int[][] track = new int[k][3];
		for (int track_i = 0; track_i < k; track_i++) {
			for (int track_j = 0; track_j < 3; track_j++) {
				track[track_i][track_j] = in.nextInt();
			}
		}
		int result = gridlandMetro(n, m, k, track);
		System.out.println(result);
		in.close();
	}
}
