package datastructure;

public interface IHash<K,V> {
	public void put(K k, V v);
	public V get(K k);
	public V remove(K k);
	public int size();
    public boolean isEmpty();
    }
