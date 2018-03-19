package com.satya.learning.problems.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Satyendra
 *
 */
public class MaxHeap<E extends Comparable<E>> {

	private static class TreeNode<E> {
		private E data;
		private TreeNode<E> parent;
		private TreeNode<E> left;
		private TreeNode<E> right;

		public TreeNode(E data) {
			this.data = data;
		}
	}

	private TreeNode<E> root;
	private TreeNode<E> end;

	private Queue<TreeNode<E>> queue;

	private int size;

	public MaxHeap() {
		this.queue = new LinkedList<>();
	}

	public void insert(E e) {
		TreeNode<E> newNode = createNewNode(e);
		if (root == null) {
			// ROOT ELEMENT to be INSERTED
			root = newNode;
		} else {
			// ELEMENT to be placed in TREE
			TreeNode<E> queueFront = queue.peek();
			// Link to parent
			newNode.parent = queueFront;
			// Link to children
			if (queueFront.left == null) {
				queueFront.left = newNode;
			} else if (queueFront.right == null) {
				queueFront.right = newNode;
				queue.poll();	// O(1)
			}
		}
		queue.add(newNode);		// O(1)
		
		repositionNode(newNode);
		
	}
	
	private void repositionNode(TreeNode<E> node) {
		if (node == null) {
			return;
		}
		TreeNode<E> parent = node.parent;
		// Move up while Parent is lesser than Child
		while (parent.data.compareTo(node.data) < 0) {
			// swap
			TreeNode<E> tempNP = node.parent;
			TreeNode<E> tempNL = node.left;
			TreeNode<E> tempNR = node.right;
			
			node.parent = parent.parent;
			
			if (node == parent.left) {
				node.left.parent = node;
				node.left = parent;
			} else {
				node.left = parent.left;
			}
			if (node == parent.right) {
				node.right = parent;
				node.right.parent = node;
			} else {
				node.right = parent.right;
			}

			parent.parent = tempNP;
			parent.left   = tempNL;
			parent.right  = tempNR;
			
			node = parent;
			parent = parent.parent;
		}
	}
	
	public E remove() {
		return null;
	}

	public E getMax() {
		return (root != null) ? root.data : null;
	}

	public int size() {
		return size;
	}

	private TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

}
