package com.satya.learning.problems.tree;

import java.util.Scanner;

/**
 * Given a tree, validate if this is a BST.
 * 
 * @author Satyendra
 *
 */
public class CheckTreeAsBST {

	private static class Node {
		int data;
		Node left, right;
		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		in.close();
		
		Node root = new Node(6);
		
		root.left = new Node(4);
		root.left.left = new Node(2);
		root.left.right= new Node(5);
		root.left.left.left = new Node(1);
		root.left.left.right= new Node(3);
		
		root.right = new Node(8);
		root.right.left = new Node(7);
		root.right.right= new Node(9);
		
		boolean isBST = isBST(root);
		
		System.out.println(isBST ? "YES" : "NO");

	}

	private static boolean isBST(Node root) {
		return isBSTSubtree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBSTSubtree(Node node, int min, int max) {
		if (node == null)
			return true;

		int data = node.data;
		boolean isLeftBST = true, isRightBST = true;
		
		boolean isBST = data > min && data < max;
		
		if (node.left != null) {
			isLeftBST = isBSTSubtree(node.left, min, data);
		}
		if (node.right != null) {
			isRightBST = isBSTSubtree(node.right, data, max);
		}
		return isBST && isLeftBST && isRightBST;
	}

}