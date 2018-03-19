package com.satya.learn.problem.linear;

/**
 * 
 * 
 * @author Satyendra
 *
 * @param <E>
 *            the type of value of a page.
 */
public class LRU<E> {

	private final int LIMIT;

	private Node<E>[] cache;
	private int size = 0;
	private Node<E> head;
	private Node<E> tail;

	private static class Node<E> {
		private E page;
		private Node<E> prev;
		private Node<E> next;

		public Node(E data) {
			this.page = data;
		}

		public int hashCode() {
			return page.hashCode();
		}

		public boolean equals(Object obj) {
			return page.equals(obj);
		}
	}

	public LRU() {
		this(16); // DEFAULT LIMIT
	}

	public LRU(int limit) {
		this.LIMIT = limit;
		this.cache = new Node[LIMIT];
	}

	/**
	 * This method provides a page for recent use.
	 * 
	 * @param page
	 *            target page.
	 */
	public void insert(E page) {
		boolean isPageFault = isPageFault(page);
		if (isPageFault) {
			/**
			 * PAGE FAULT :
			 * 1. place new node to front. 
			 * 2. remove excess node from tail.
			 */
			Node<E> newNode = newNode(page);
			int idxTgt = hashIndexFor(page);

			newNode.next = head;
			if (head != null) {
				head.prev = newNode;
			}
			head = newNode;
			
			cache[idxTgt] = newNode;
			size++;
			
			removeLeastRecent();
		} else {
			/**
			 * Existing Page : 
			 * 1. Move the page to front.
			 */

		}
	}

	private void removeLeastRecent() {
		if (size > LIMIT) {
			if (tail != null) {
				if (tail.prev != null) {
					tail.prev.next = null; // un-plug from tail
				}
				int idxTail = hashIndexFor(tail.page);
				cache[idxTail] = null; // for GC
				// TODO : remove only this element from chain.
			}
			size--;
		}
	}

	/**
	 * This method computes an index of cache for the given page, based on the
	 * has value.
	 * 
	 * @param page
	 *            the target page
	 * @return
	 */
	private int hashIndexFor(E page) {
		int h = page.hashCode();
		return Math.abs(h % LIMIT);
	}

	/**
	 * This method identifies if the requested page is available in page or is
	 * it a Page Fault.
	 * 
	 * @param page
	 *            the targeted page
	 * @return
	 */
	private boolean isPageFault(E page) {
		int idxTgt = hashIndexFor(page);
		Node<E> targetNode = cache[idxTgt];
		return targetNode == null || !targetNode.page.equals(page);
	}

	/**
	 * This method creates a Node<E> for the given page.
	 * 
	 * @param page
	 *            data representing a page.
	 * @return
	 */
	private Node<E> newNode(E page) {
		return new Node<>(page);
	}

	public int size() {
		return size;
	}
}