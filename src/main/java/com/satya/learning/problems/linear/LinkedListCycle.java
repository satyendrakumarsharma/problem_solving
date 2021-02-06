package com.satya.learning.problems.linear;

import java.util.Scanner;

import com.satya.learning.common.SinglyLinkedNode;

/**
 * Given head, the head of a linked list, determine if the linked list has a
 * cycle in it.
 * <p>
 * The input {@code pos} is index at which the last element links to create a
 * cycle. {@code pos} as -1 means that no cycle exists.
 * 
 * <pre>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * 
 * Input: head = [1], pos = -1
 * Output: false
 * </pre>
 * 
 * @see <a href= "https://leetcode.com/problems/linked-list-cycle/">Leet Code -
 *      Linked List Cycle</a>
 * 
 * @author Satyendra
 *
 */
public class LinkedListCycle {

	private static boolean hasCycle(SinglyLinkedNode<Integer> head) {
		if (head == null) {
			return false;
		}
		SinglyLinkedNode<?> slow = head;
		SinglyLinkedNode<Integer> fast = head.getNext();

		while (slow != fast) {
			if (fast == null || !fast.hasNext()) {
				return false;
			}
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the number of elements in Linked List: ");
		int size = s.nextInt();

		SinglyLinkedNode<Integer> head = null;
		SinglyLinkedNode<Integer> node = null;
		SinglyLinkedNode<Integer> tail = null;
		System.out.println("Enter each of " + size + " elements:");
		for (int idx = 0; idx < size; idx++) {
			int value = s.nextInt();
			SinglyLinkedNode<Integer> newNode = new SinglyLinkedNode<>(value);
			if (idx == 0) {
				head = newNode;
			} else {
				node.setNext(newNode);
			}
			node = newNode;
		}
		tail = node;

		System.out.print("Enter the position where tail links: ");
		int pos = s.nextInt();
		if (pos >= size) {
			System.err.println("Invalid position for cycle is passed: " + pos);
			System.exit(pos);
		}
		if (pos >= 0) {
			SinglyLinkedNode<Integer> posNode = head;
			for (int i = 0; i < pos; i++) {
				posNode = posNode.getNext();
			}
			tail.setNext(posNode);
		}

		boolean hasCycle = hasCycle(head);
		System.out.println("Output: This linked list has a cycle: " + hasCycle);

		s.close();
	}

}
