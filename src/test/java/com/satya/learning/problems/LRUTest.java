package com.satya.learning.problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.satya.learning.problems.linear.LRU;
import com.satya.learning.problems.linear.LinkedLRU;

/**
 * 
 * 
 * @author Satyendra
 *
 */
public class LRUTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.satya.learning.problems.linear.LinkedLRU#accessPage(java.lang.Object)}.
	 */
	@Test
	public void testaccessPage() {
		LRU<Integer> lru = new LinkedLRU<>(3);
		System.out.println(lru);
		lru.accessPage(4);
		System.out.println(lru);
		lru.accessPage(2);
		System.out.println(lru);
		lru.accessPage(1);
		System.out.println(lru);
		lru.accessPage(1);
		System.out.println(lru);
		lru.accessPage(7);
		System.out.println(lru);
		lru.accessPage(2);
		System.out.println(lru);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
