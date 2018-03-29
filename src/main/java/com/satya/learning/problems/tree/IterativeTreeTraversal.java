package com.satya.learning.problems.tree;

import java.util.Stack;

/**
 * 
 * 
 * @author Satyendra
 *
 */
public class IterativeTreeTraversal {

	private static class Node {
		private int data;
		private Node left;
		private Node right;

		private Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(0);
		root.right = new Node(-10);
		root.left.left = new Node(5);
		root.left.right = new Node(6);
		root.right.right = new Node(11);

		System.out.println("In Order:");
		inOrder(root);

		System.out.println("Pre Order:");
		preOrder(root);

		System.out.println("Post Order:");

	}

	/**
	 * Implementation of in-order traversal using iteration.
	 * 
	 * @param node
	 *            the root node of tree
	 */
	private static void inOrder(Node node) {
		Stack<Node> stk = new Stack<>();
		while (true) {
			if (node != null) {
				stk.push(node);
				node = node.left;
			} else {
				if (stk.isEmpty())
					break;
				Node cur = stk.pop();
				System.out.println(cur.data);
				node = cur.right;
			}
		}
	}

	/**
	 * Implementation of pre-order traversal using iteration.
	 * 
	 * @param node
	 *            the root node of tree
	 */
	private static void preOrder(Node node) {
		Stack<Node> stk = new Stack<>();
		while (true) {
			if (node != null) {
				System.out.println(node.data);
				stk.push(node);
				node = node.left;
			} else {
				if (stk.isEmpty())
					break;
				Node cur = stk.pop();
				node = cur.right;
			}
		}
	}

	/**
	 * Implementation of post-order traversal using iteration.
	 * 
	 * @param node
	 *            the root node of tree
	 */
	private static void postOrder(Node node) {
		Stack<Node> stk = new Stack<>();
		while (true) {
			if (node != null) {
				stk.push(node);
				node = node.left;
			} else {
				if (stk.isEmpty())
					break;
				Node cur = stk.pop();
				node = cur.right;
			}
		}
	}

}
