package ds.list;

public interface List<E> {
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear();

	/**
	 * Inserts the specified element at the specified position in this list.
	 * 
	 * @param pos
	 *            index at which the specified element is to be inserted
	 * @param item
	 *            element to be inserted
	 */
	public void insert(int pos, E item);

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 * 
	 * @param pos
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public E remove(int pos);

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */
	public int length();

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param pos
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 */
	public E getValue(int pos);
}
