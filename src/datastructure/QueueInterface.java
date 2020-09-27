package datastructure;

public interface QueueInterface<T1,T2> {

	public void enqueue(T1 t1,T2 t2);

	public Object dequeue();

	public boolean isEmpty();

	public int size();

	public Object first();
}
