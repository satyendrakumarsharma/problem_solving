package com.satya.learning.problems.linear;

/**
 * Least Recently Used [LRU] cache:<br/>
 * Page Replacement Algorithm
 * 
 * @author Satyendra
 *
 * @param <E>
 *            the type of value of a page.
 *
 */
public interface LRU<E> {
	
	public void accessPage(E page);

	public E recentPage();
	
	public E leastRecentPage();
	
	public int size();
	
}
