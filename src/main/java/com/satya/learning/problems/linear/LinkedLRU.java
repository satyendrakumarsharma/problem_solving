package com.satya.learning.problems.linear;

/**
 * Least Recently Used cache implementation:<br/>
 * Page Replacement Algorithm<br/>
 * 
 * This implementation uses the Linked lists of nodes based on the recentness
 * and also hashes them into buckets for fast access.
 * 
 * @author Satyendra
 *
 * @param <E>
 *            the type of value of a page.
 */
public class LinkedLRU<E> implements LRU<E> {

	private final int LIMIT;

	private Node<E>[] cache;
	private int size = 0;
	private Node<E> head;
	private Node<E> tail;

	private static class Node<E> {
		private E page;
		private Node<E> prev;
		private Node<E> next;
		private Node<E> chainNext;

		public Node(E data) {
			this.page = data;
		}

		public int hashCode() {
			return page.hashCode();
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			return page.equals(obj);
		}

		@Override
		public String toString() {
			return page.toString();
		}
	}

	public LinkedLRU() {
		this(16); // DEFAULT LIMIT
	}

	@SuppressWarnings("unchecked")
	public LinkedLRU(int limit) {
		this.LIMIT = limit;
		this.cache = new Node[LIMIT];
	}

	/**
	 * This method access the given page and marks it as
	 * most-recently used.
	 * 
	 * @param page
	 *            target page.
	 */
	public void accessPage(E page) {
		int idxTgt = hashIndexFor(page);
		Node<E> pageInCache = cachedPage(page, idxTgt);
		if (pageInCache == null) {
			/**
			 * PAGE FAULT :
			 * 1. Place new node of page to front.
			 * 2. Remove excess node from tail.
			 */
			Node<E> newNode = newNode(page);

			newNode.next = head;
			if (head != null) {
				head.prev = newNode;
			} else {
				tail = newNode;
			}
			head = newNode;

			placeInChain(newNode, idxTgt);
			size++;

			removeLeastRecent();
		} else {
			/**
			 * Existing Page :
			 * 1. Remove the page from current position of list.
			 * 2. Place the new node of page to front. 
			 * 2b. IF current position is at tail, then correct the tail.
			 */
			Node<E> existingNode = pageInCache;
			if (existingNode == head) {
				return; // No operations for the page already at head
			}
			Node<E> prevNode = existingNode.prev;
			Node<E> nextNode = existingNode.next;
			// Pull out the node from current location
			if (prevNode != null) {
				prevNode.next = nextNode;
			}
			if (nextNode != null) {
				nextNode.prev = prevNode;
			}
			existingNode.next = head; // Placed just before head
			existingNode.prev = null; // Nothing before first page
			head = existingNode; // Head redirected to first page
			if (existingNode == tail) {
				tail = tail.prev; // Tail nodes move second-last to last
			}
		}
	}

	/**
	 * This method places the node at given index, and ensures that chaining is
	 * maintained.
	 * 
	 * @param newNode
	 *            the node to be placed
	 * @param idxTgt
	 *            index of cache
	 */
	private void placeInChain(Node<E> newNode, int idxTgt) {
		Node<E> chain = cache[idxTgt];
		cache[idxTgt] = newNode; // Place at chain-head.
		newNode.chainNext = chain;
	}

	private void removeLeastRecent() {
		if (size <= LIMIT) {
			return;
		}
		if (tail != null) {
			E page = tail.page;
			int idxTail = hashIndexFor(page);
			Node<E> node = cache[idxTail];
			if (node != null) {
				if (node.equals(page)) {
					cache[idxTail] = node.chainNext;
				} else {
					Node<E> p = node;
					node = node.chainNext;
					while (node != null) {
						if (node.equals(page)) {
							p.chainNext = node.chainNext;
						}
						p = node;
						node = node.chainNext;
					}
				}
			}

			Node<E> secondLast = tail.prev;
			if (secondLast != null) {
				secondLast.next = null; // un-plug from tail
			}
			tail = secondLast; // shift tail to second-last
		}
		size--;
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
	 * it a Page Fault.<br/>
	 * <b>Page Fault</b>: Given page not available in cache, needs to be
	 * fetched.
	 * 
	 * @param page
	 *            the targeted page
	 * @return the {@code Node} if it exists, else null for PageFault
	 */
	private Node<E> cachedPage(E page, int idxTgt) {
		Node<E> chainHead = cache[idxTgt];
		Node<E> node = chainHead;
		while (node != null) {
			if (node.equals(page)) {
				return node;
			}
			node = node.chainNext;
		}
		return null;
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

	@Override
	public E recentPage() {
		return head.page;
	}

	@Override
	public E leastRecentPage() {
		return tail.page;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(">>>\n");
		sb.append("CACHE:\n");
		for (int i = 0; i < LIMIT; i++) {
			Node<E> chainNode = cache[i];
			sb.append(i + ": ");
			while (chainNode != null) {
				sb.append("[" + chainNode.page + "] -> ");
				chainNode = chainNode.chainNext;
			}
			sb.append("NULL\n");
		}
		sb.append("\nLRU:\n");
		Node<E> lruNode = head;
		sb.append("[HEAD] -> ");
		while (lruNode != null) {
			sb.append("[" + lruNode.page + "] -> ");
			lruNode = lruNode.next;
		}
		sb.append("[TAIL]");
		sb.append("\n<<<");
		return sb.toString();
	}

}