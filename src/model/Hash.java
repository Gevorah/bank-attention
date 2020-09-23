package model;

public class Hash<K,V> implements IHash<K,V>{
	Node<K, V>[] slots;
    int maxs;
    int size;

    public Hash() {
        maxs = 100;
        slots = new Node[maxs];
    }
    public int hashFunction(K key) {return Math.abs(key.hashCode()%maxs);}
    public void put(K key, V value) throws ClassCastException {
    	int index = hashFunction(key);
    	Node<K,V> f = slots[index];
    	Node<K,V> toAdd = new Node<K,V>(key, value);
    	if (f == null) {
    		slots[index] = toAdd;
    		size++;
    	} else {
    		while (f != null) {
    			if (f.getKey().equals(key)) {
    				f.setValue(value);
    				size++;
    				break;
    			}
    			f = f.getNext();
    		}
    		if (f == null) {
    			f = slots[index];
    			toAdd.setNext(f);
    			slots[index] = toAdd;
    			size++;
    		}
    	}
    	if ((1.0*size)/maxs > 0.7) {
    		Node<K, V>[] tmp = slots;
    		slots = new Node[maxs];
    		maxs = 2 * maxs;
    		for (Node<K,V> theE : tmp) {
    			while (theE != null) {
    				put(theE.getKey(), theE.getValue());
    				theE.setNext(theE.getNext());
    			}
    		}

    	}

    }
    public V get(K key) throws ClassCastException {

        int index = hashFunction(key);

        Node<K, V> f = slots[index];
        while (f != null) {
            if (f.getKey().equals(key)) {
                return f.getValue();
            }
            f = f.getNext();
        }
        return null;
    }

    public V remove(K key) throws ClassCastException {
        int index = hashFunction(key);
        Node<K, V> f = slots[index];
        if (f == null) {
            return null;
        }
        if (f.getKey().equals(key)) {
            V val = f.getValue();
            f = f.getNext();
            slots[index] = f;
            size--;
            return val;
        } else {
        	Node<K, V> prev = null;
            while (f != null) {

                if (f.getKey().equals(key)) {
                    prev.setNext(f.getNext());
                    size--;
                    return f.getValue();
                }
                prev = f;
                f = f.getNext();
            }
            size--;
            return null;
        }
    }

    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}
	
	public class Node<K,V> {
		private K key;
		private V value;
		public Node<K,V> next;
		public Node(K k, V v){
			key=k;
			value=v;
		}
		public K getKey() {return key;}
		public void setKey(K key) {this.key=key;}
		    
		public V getValue() {return value;}
		public void setValue(V value) {this.value=value;}

	    public Node<K,V> getNext() {return next;}
	    public void setNext(Node<K,V> next) {this.next=next;}
	}
	public static void main(String[] args) {
		Hash<String,String> h = new Hash<>();
		h.put("A","Hola");
		h.put("B", "Mundo");
		h.remove("B");
		System.out.println(h.get("B"));
	}
}
