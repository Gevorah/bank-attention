package datastructure;

public class MinHeap<E extends Comparable<E>> implements IMinHeap<E> {
	public class Array<T>{
		private Object[] elements;
		public Array(int size) {elements = new Object[size];}
		@SuppressWarnings("unchecked")
		public T get(int i) {return (T)elements[i];};
		public void set(int i, T t) {elements[i]=t;};
	}

	private Array<E> heap; 
	private int size; 
	private int maxsize; 

	private static final int FRONT = 0; 

	//@SuppressWarnings("unchecked")
	public MinHeap(int maxsize) { 
		this.maxsize = maxsize; 
		this.size = 0; 
		heap = new Array<E>(this.maxsize+1);
		//heap.set(0,(E) new String(""+Integer.MIN_VALUE));
	} 
	private int parent(int pos) {return pos/2;} 

	private int leftChild(int pos) {return (2*pos)+1;} 

	private int rightChild(int pos)	{return (2*pos)+2;} 

	private boolean isLeaf(int pos)	{ 
		if(pos>=(size/2) && pos<=size) {return true;} 
		return false; 
	} 

	private void swap(int fpos, int spos) { 
		E tmp; 
		tmp = heap.get(fpos); 
		heap.set(fpos, heap.get(spos)); 
		heap.set(spos, tmp); 
	} 
 
	private void minHeapify(int pos) { 
		if (!isLeaf(pos)) { 
			if (heap.get(pos).compareTo(heap.get(leftChild(pos)))>0 
				|| heap.get(pos).compareTo(heap.get(rightChild(pos)))>0) { 
				if (heap.get(leftChild(pos)).compareTo(heap.get(rightChild(pos)))<0) { 
					swap(pos, leftChild(pos)); 
					minHeapify(leftChild(pos)); 
				} else { 
					swap(pos, rightChild(pos)); 
					minHeapify(rightChild(pos)); 
				} 
			} 
		} 
	} 

	public void insert(E element) { 
		if(heap.get(0)==null) {
			heap.set(size++,element);
			return;
		}
		if (size >= maxsize) {return;} 
		heap.set(size, element); 
		int current = size++; 
		while(heap.get(current).compareTo(heap.get(parent(current))) < 0) { 
			swap(current, parent(current)); 
			current = parent(current); 
		} 
	} 

	public void print() { 
		for (int i=0; i <= size/2; i++) { 
			System.out.print(" PARENT : " + heap.get(i) + " LEFT CHILD : " + heap.get(leftChild(i)) + " RIGHT CHILD :" + heap.get(rightChild(i)));
			System.out.println(); 
		} 
	} 

	// Function to build the min heap using 
	// the minHeapify 
	public void minHeap() { 
		for (int pos = (size / 2); pos >= 1; pos--) { 
			minHeapify(pos); 
		} 
	} 

	public E extractMin() { 
		E popped = heap.get(FRONT); 
		heap.set(FRONT, heap.get(--size));
		heap.set(size, null);
		minHeapify(FRONT); 
		return popped; 
	} 
	
	public E minimum() {
		return heap.get(0);
	}
	public static void main(String[] args) {
		MinHeap<String> a = new MinHeap<String>(10);
		a.insert("A");
		a.insert("B");
		a.insert("C");
		a.insert("D");
		a.extractMin();
		a.print();
	}
}
