package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Set;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Hashmap implementation of a set
 *
 * @author Aaron Harvey
 */
public class SetHash<T> implements Set<T> {

	private int size = 0;
	private int prime = 6661;
	private ArrayList<T>[] buckets;

	public SetHash() {
		buckets = new ArrayList[prime];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayList<>();
		}
	}

	/**
	 * Method to hash the data
	 *
	 * @param e value to hash
	 * @return hashed value based on the prime
	 */
	private int hash(T e) {
		return (e.hashCode() & 0x7fffffff) % prime;
	}

	/**
	 * Add element e to the set, unless e already exists in the set or e is null.
	 *
	 * @param e Item to add to the set
	 */
	@Override
	public void add(T e) {
		if (e != null) {
			ArrayList<T> bucket = buckets[hash(e)];
			if (!contains(e)) {
				bucket.add(e);
				size++;
			}
		}
	}

	/**
	 * Remove element e from the set, unless e does not exist in the set, or e is null
	 *
	 * @param e Item to remove from the set
	 */
	@Override
	public void remove(T e) {
		if (e != null) {
			ArrayList<T> bucket = buckets[hash(e)];
			if (!contains(e)) {
				bucket.remove(e);
				size--;
			}
		}
	}

	/**
	 * Test whether e is a member of this set.
	 *
	 * @param e item to check membership of
	 * @return true if e is a member of this set, false if not or e is null.
	 */
	@Override
	public boolean contains(T e) {
		if (e != null && buckets[hash(e)].contains(e)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to generate an iterator to iterate across the contents of the set.
	 *
	 * @return Iterator for this set.
	 */
	@Override
	public Iterator<T> iterator() {
		ArrayList<T> temp = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			temp.addAll(buckets[i]);
		}
		return temp.iterator();
	}

	/**
	 * Check if the set is empty
	 *
	 * @return true if there are no items in the set, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size != 0;
	}

	/**
	 * Adds all items from Set s to this set, if those items are not already in this set.
	 * This is equivalent to the set union operation.
	 *
	 * @param s Set containing the items to be added to this set.
	 */
	@Override
	public void addAll(Set<T> s) {
		for (Iterator<T> it = s.iterator(); it.hasNext(); ) {
			T e = it.next();
			if (!contains(e)) {
				add(e);
				size++;
			}
		}
	}

	/**
	 * Removes all items from this set, which are not items contained in the provided set.
	 * This is equivalent to the set intersection operation.
	 *
	 * @param s The set defining which items are to be kept in this set.
	 */
	@Override
	public void retainAll(Set<T> s) {
		for (Iterator<T> it = this.iterator(); it.hasNext();) {
			T e = it.next();
			if (!contains(e)) {
				remove(e);
				size--;
			}
		}
	}

	/**
	 * Removes all items found in the provided set from this set.
	 * This is equivalent to the set difference operation.
	 *
	 * @param s Set defining the items to be removed from this set.
	 */
	@Override
	public void removeAll(Set<T> s) {
		for (Iterator<T> it = this.iterator(); it.hasNext();) {
			T e = it.next();
			if (contains(e)) {
				remove(e);
				size--;
			}
		}
	}
}
