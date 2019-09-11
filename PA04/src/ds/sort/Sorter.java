package ds.sort;

public interface Sorter<K extends Comparable<? super K>> {
	void sort(Pair<K, ?>[] array, int left, int right);
}
