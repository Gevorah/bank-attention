package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class QueueTest {

	Queue<Integer, String> qI;
	Queue<Character, Integer> qC;
	Queue<String, String> qS;
	Queue<Integer, String> qK;


	void setUp1() {
		qI = new Queue<Integer, String>();
		qI.enqueue(7, "Niño");
		qI.enqueue(15, "Joven");
		qI.enqueue(53, "Adulto");
	}

	void setUp2() {
		qC= new Queue<Character, Integer>();
		qC.enqueue('A', 1);
		qC.enqueue('Ñ', 15);
		qC.enqueue('Z', 27);
		qC.enqueue('D', 4);
	}

	void setUp3() {
		qS= new Queue<String, String>();
		qS.enqueue("perro", "firulais");
		qS.enqueue("gato", "misifus");
		qS.enqueue("vaca", "lola");
		qS.enqueue("pato", "donald");
		qS.enqueue("pajaro", "lucas");
		qS.enqueue("mono", "jorge");
		
	}
	
	void setUp4() {
		qK = new Queue<Integer, String>();
	}


	@Test
	void dequeueTest() {

		setUp1();
		assertEquals(qI.getFirst(), qI.dequeue());
		
		
		setUp2();
		assertEquals(qC.getFirst(), qC.dequeue());
		assertEquals(qC.getFirst(), qC.dequeue());
		assertEquals(qC.getFirst(), qC.dequeue());
		
		setUp3();
		assertEquals(qS.getFirst(), qS.dequeue());
		assertEquals(qS.getFirst(), qS.dequeue());
		assertEquals(qS.getFirst(), qS.dequeue());
		assertEquals(qS.getFirst(), qS.dequeue());
		assertEquals(qS.getFirst(), qS.dequeue());
		assertEquals(qS.getFirst(), qS.dequeue());
		try {
		assertEquals(null, qS.dequeue());
		}
		catch(NullPointerException e) {
			System.out.println("La lista esta vacia");
		}
		
	}

	@Test
	void isEmptyTest() {
		setUp1();
		assertEquals(false, qI.isEmpty());
		
		setUp2();
		assertEquals(false, qC.isEmpty());
		
		setUp4();
		assertEquals(true, qK.isEmpty());
	
	}
}
