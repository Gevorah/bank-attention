package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MinHeapTest {

	MinHeap<String> smh;
	MinHeap<Integer> imh;
	MinHeap<Character> cmh;
	
	void setup1() {
		imh = new MinHeap<Integer>(6);
		imh.insert(4);
		imh.insert(99);
		imh.insert(3);
		imh.insert(33);
		imh.insert(66);
		imh.insert(10);
	}
	
	void setup2() {
		smh = new MinHeap<String>(6);
		smh.insert("Orden 1");
		smh.insert("Orden 3");
		smh.insert("Orden 6");
		smh.insert("Orden 9");
		smh.insert("Orden 4");
		smh.insert("Orden 2");
	}
	
	void setup3() {
		cmh = new MinHeap<Character>(6);
		cmh.insert('A');
		cmh.insert('B');
		cmh.insert('C');
		cmh.insert('D');
		cmh.insert('E');
		cmh.insert('F');
	}
	
	@Test
	void swapTest() {
		setup1();
		imh.swap(0,1);
		assertEquals(33,imh.minimum());
		
		setup2();
		smh.swap(5,3);
		smh.swap(3,0);
		assertEquals("Orden 6",smh.minimum());
		
		setup3();
		cmh.swap(1,2);
		cmh.swap(2,3);
		cmh.swap(3,0);
		assertEquals('B',cmh.minimum());
	}
	
	@Test
	void minHeapifyTest() {
		swapTest();
		imh.minHeapify(0);
		assertEquals(3,imh.minimum());
	
		smh.minHeapify(0);
		assertEquals("Orden 2",smh.minimum());
		
		cmh.minHeapify(0);
		assertEquals('B',cmh.minimum());
	}
	
	@Test
	void insertTest() {
		setup1();
		imh.insert(0);
		assertEquals(3,imh.minimum());
		
		setup2();
		smh.insert("Orden 0");
		assertEquals("Orden 1",smh.minimum());
		
		setup3();
		cmh.insert('a');
		assertEquals('A',cmh.minimum());
	}
	
	@Test
	void minHeapTest() {
		swapTest();
		imh.minHeap();
		assertEquals(3,imh.minimum());
	
		smh.minHeap();
		assertEquals("Orden 1",smh.minimum());
		
		cmh.minHeap();
		assertEquals('A',cmh.minimum());
	}
	
	@Test
	void extractMinTest() {
		setup1();
		assertEquals(3,imh.extractMin());
		
		setup2();
		assertEquals("Orden 1",smh.extractMin());
		assertEquals("Orden 2",smh.extractMin());
		
		setup3();
		assertEquals('A',cmh.extractMin());
		assertEquals('B',cmh.extractMin());
		assertEquals('C',cmh.extractMin());
		
	}

}
