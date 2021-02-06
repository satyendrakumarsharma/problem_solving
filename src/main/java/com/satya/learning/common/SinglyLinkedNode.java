package com.satya.learning.common;

/**
 * Definition of Node for a singly linked list.
 * 
 * @author Satyendra
 *
 */
public class SinglyLinkedNode<E> {
	private E value;
	private SinglyLinkedNode<E> next;

	public SinglyLinkedNode(E value) {
		this.value = value;
		this.next = null;
	}

	public E getValue() {
		return this.value;
	}

	public SinglyLinkedNode<E> getNext() {
		return this.next;
	}

	public void setNext(SinglyLinkedNode<E> next) {
		this.next = next;
	}

	public boolean hasNext() {
		return this.next != null;
	}
}
