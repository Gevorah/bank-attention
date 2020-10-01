package datastructure;

public interface IMinHeap<E> {
	public void minHeap();
	public void insert(E e);
	public E extractMin();
	public E minimum();
}
