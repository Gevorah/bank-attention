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

	public MinHeap(int maxsize) { 
		this.maxsize = maxsize; 
		this.size = 0; 
		heap = new Array<E>(this.maxsize+1);
	} 
	private int parent(int index) {return (index-1)/2;} 

	private int leftChild(int index) {return (2*index)+1;} 

	private int rightChild(int index) {return (2*index)+2;} 

	private boolean isLeaf(int index) { 
		if(index>=(size/2) && index<=size) return true; 
		else return false; 
	} 

	private void swap(int fpos, int spos) { 
		E tmp = heap.get(fpos); 
		heap.set(fpos, heap.get(spos)); 
		heap.set(spos, tmp); 
	} 
 
	private void minHeapify(int index) { 
		if(!isLeaf(index)) { 
			if((heap.get(rightChild(index))!=null && heap.get(index).compareTo(heap.get(leftChild(index)))>0) || 
				(heap.get(rightChild(index))!=null && heap.get(index).compareTo(heap.get(rightChild(index)))>0)) { 
				if(heap.get(leftChild(index)).compareTo(heap.get(rightChild(index)))<0) { 
					swap(index, leftChild(index)); 
					minHeapify(leftChild(index)); 
				} else { 
					swap(index, rightChild(index)); 
					minHeapify(rightChild(index)); 
				} 
			}
		} 
	} 

	public void insert(E element) { 
		if(heap.get(0)==null) {
			heap.set(size++,element);
			return;
		}
		if(size>=maxsize) {return;} 
		heap.set(size, element); 
		int current = size++; 
		while(heap.get(parent(current)).compareTo(heap.get(current))>0) { 
			swap(current, parent(current)); 
			current = parent(current); 
		}
	} 

	public String print() { 
		String show = heap.get(0).toString();
		for (int i=0; i <= size/2; i++) { 
			if(heap.get(leftChild(i))!=null) show += heap.get(leftChild(i)).toString();
			if(heap.get(rightChild(i))!=null)show += "\n"+heap.get(rightChild(i)).toString();
		}
		return show;
	} 

	public void minHeap() { 
		for (int pos = (size/2)-1; pos>=0; pos--) { 
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
	
	public int size() {
		return size;
	}
	
}
