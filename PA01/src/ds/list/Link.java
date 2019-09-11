package ds.list;

public class Link<E> {
	private E element;
	private Link<E> next;

	Link(E item, Link<E> next) {
		this.element = item;
		this.next = next;
	}

	public Link<E> next() {
		return next;
	}

	public Link<E> setNext(Link<E> next) {
		this.next = next;
		return next;
	}

	public E getElement() {
		return this.element;
	}

	public E setElement(E item) {
		this.element = item;
		return item;
	}
}
