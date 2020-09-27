package datastructure;

public class Queue<T1,T2> implements QueueInterface<T1,T2> {

	public class Node<T1,T2> {
		T1 t1;
		T2 t2;
		Node<T1,T2> next;

		public Node(T1 t1, T2 t2) {
			this.t1=t1;
			this.t2=t2;
		}
		public T1 getT1() {return t1;};
		public T2 getT2() {return t2;};
	}

	Node<T1,T2> first;
	Node<T1,T2> end;
	int size;

	public Queue() {
		size = 0;
	}

	public void enqueue(T1 t1,T2 t2) {
		Node<T1,T2> new_node = new Node<T1,T2>(t1,t2);
		if (first == null) {
			first = new_node;
			end = new_node;
		} else {
			end.next = new_node;
			end = new_node;
		}
		size++;
	}

	public Node<T1,T2> dequeue() {
		if(first == null) return null;
		Node<T1,T2> n = first;
		first = first.next;
		size--;
		return n;
	} 

	public boolean isEmpty() {return (size==0);}

	public int size() {return size;}

	public Node<T1,T2> first() {
		if (first == null) return null;
		else return first;
	}

	 public String print() {
		 String list="";
		 Node<T1,T2> curr = first;
	        while (curr!=null) {
	            list=( curr.getT2()+"\n");
	            curr=curr.next;
	        }
	       return list;
	    }
}
