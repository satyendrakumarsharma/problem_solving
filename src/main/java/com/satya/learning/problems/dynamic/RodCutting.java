package com.satya.learning.problems.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a rod of length n inches and an array of prices that contains prices of
 * all pieces of size smaller than n. Determine the maximum value obtainable by
 * cutting up the rod and selling the pieces.
 * 
 * @see <a href=
 *      "https://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod">Cutting
 *      a Rod | GeeksForGeeks</a>
 * 
 */
public class RodCutting {
	public static void main(String[] args) {
		Map<Integer, Integer> mktPrice = new HashMap<>();
		mktPrice.put(1, 2);
		mktPrice.put(2, 5);
		mktPrice.put(3, 7);
		mktPrice.put(4, 8);

		int price = cutRod(5, mktPrice);
		System.out.println("Maximum Profit : " + price);

	}

	private static int cutRod(int len, Map<Integer, Integer> mktPrice) {
		int ms = mktPrice.size();
		int[][] t = new int[ms][len + 1];

		for (int piece = 1; piece <= ms; piece++) {
			int piecePrice = mktPrice.get(piece);
			t[piece - 1][0] = 0;
			for (int rodSize = 1; rodSize <= len; rodSize++) {
				int sellPrice = 0;
				if (rodSize < piece) {
					sellPrice = t[piece - 1][rodSize];
				} else {
					sellPrice = t[piece - 1][rodSize - piece] + piecePrice;
				}
				if (piece > 1) {
					sellPrice = Math.max(sellPrice, t[piece - 2][rodSize]);
				}
				t[piece - 1][rodSize] = sellPrice;
				System.out.print(sellPrice + " ");
			}
			System.out.println();
		}
		return t[ms - 1][len];
	}
}
