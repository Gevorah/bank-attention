package model;

public interface QueueInterface<T> {

	public void enqueue(T t, String name, String id);

	public T dequeue();

	public boolean isEmpty();

	public int size();

	public T first();
}
