package com.satya.learn.problem.linear;

import com.satya.learn.problem.linear.LRU.Node;

/**
 * Least Recently Used cache implementation:
 * Page Replacement Algorithm
 * 
 * @author Satyendra
 *
 * @param <E>
 *            the type of value of a page.
 */
public class LRU<E> {

	private final int LIMIT;

	private Node<E> []cache;
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

	@SuppressWarnings("unchecked")
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
			 * 1. Place new node of page to front. 
			 * 2. Remove excess node from tail.
			 */
			Node<E> newNode = newNode(page);
			int idxTgt = hashIndexFor(page);

			newNode.next = head;
			if (head != null) {
				head.prev = newNode;
			} else {
				tail = newNode;
			}
			head = newNode;
			
			cache[idxTgt] = newNode;	// TODO : Place at correct location in chain.
			size++;
			
			removeLeastRecent();
		} else {
			/**
			 * Existing Page : 
			 * 1. Remove the page from current position of list.
			 * 2. Place the new node of page to front.
			 * 2b. IF current position is at tail, then correct the tail.
			 */
			int idxTgt = hashIndexFor(page);
			Node<E> existingNode = cache[idxTgt];	// TODO : Get correct node from chain.
			Node<E> prevNode = existingNode.prev;
			Node<E> nextNode = existingNode.next;
			if (prevNode != null) {
				prevNode.next = existingNode.next;
			}
			if (nextNode != null) {
				nextNode.prev = existingNode.prev;
			}
			existingNode.prev = null;
			existingNode.next = head;
			head = existingNode;
			if (existingNode == tail) {
				tail = tail.prev;
			}
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CACHE:").append("\n");
		for (int i = 0; i < LIMIT; i++) {
			sb.append((cache[i] == null ? "NULL" : cache[i].page+"-"+cache[i].page.hashCode()) + ", ");
		}
		sb.append("\nLIST:").append("\n");
		Node<E> node = head;
		while(node != null) {
			sb.append("[" + node.page + "], ");
			node = node.next;
		}
		return sb.toString();
	}
	
}