package com.satya.learning.problems.tree;

/**
 * For a given binary-tree, print the left view.
 * 
 * <pre>
 * Input : 
                 1
               /   \
              2     3
             / \     \
            4   5     6             
 * Output : 1 2 4
 * </pre>
 * 
 * @see <a href=
 *      "https://www.geeksforgeeks.org/print-left-view-binary-tree">GeeksForGeeks
 *      - Print Left View</a>
 * 
 * @author Satyendra
 *
 */
public class LeftViewOfTree {

	private class Node {
		private int data;
		private Node left;
		private Node right;
		private Node(int data) {
			this.data = data;
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	private static void printLeftView(Node root) {
		if (root == null) return;
		
	}
}
