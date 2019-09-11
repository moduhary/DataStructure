package CCFinder;

public class ParPtrTree {
	private Integer[] array; // Node array

	public ParPtrTree(int size) {
		array = new Integer[size]; // Create node array
		for (int i = 0; i < size; i++)
			array[i] = null;
	}

	/** Determine if nodes are in different trees */
	public boolean differ(int node1, int node2) {
		// fill your code
		return true;
	}

	/** Merge two subtrees */
	public void UNION(int node1, int node2) {
		// fill your code
	}

	public Integer FIND(Integer node) {
		// fill your code
		return 0;
	}
}
