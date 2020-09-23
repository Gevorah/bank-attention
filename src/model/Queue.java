package model;

import model.Queue.Node;

public class Queue<T> implements QueueInterface<T> {

	class Node <T> {

		T elem;
		String name;
		String id;
		Node <T> Next;

		public Node(T t, String name, String id) {

			elem = t;
			this.name=name;
			this.id=id;
			Next = null;

		}

	}

	Node <T> first;

	Node <T> end;

	int size;

	public Queue() {

	    end = null;

	    size = 0;

	  }


	public void enqueue(T t, String name, String id) {

		Node <T> new_node = new Node <T>(t, name, id);

		if (first == null) {

			first = new_node;

			end = new_node;

		} else {

			end.Next = new_node;

			end = new_node;

		}

		size++;

	}; 



		public T dequeue() {

			if (first == null)

				return null;

			;

			T o = first.elem;

			first = first.Next;

			size--;

			return o;

		} 



		public boolean isEmpty() {

			return (size == 0);

		}



		public int size() {

			return size;

		}



		public T first() {

			if (first == null)

				return null;

			else

				return first.elem;

		}

	}
