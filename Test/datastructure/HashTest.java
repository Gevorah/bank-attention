package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HashTest {
	Hash<String,String> hS;
	Hash<Integer,String> hI;
	Hash<Character,Integer> hC;
	
	void setup1(){
		hS = new Hash<String,String>();
		hS.put("Spanish","Hola");
		hS.put("English", "Hello");
		hS.put("Korean", "Annyeonghaseyo");
	}
	void setup2(){
		hI = new Hash<Integer,String>();
		hI.put(4,"Sublime");
		hI.put(126,"SL");
		hI.put(2015, "SKT T1");
	}
	void setup3(){
		hC = new Hash<Character,Integer>();
		hC.put('F',106);
		hC.put('A',101);
		hC.put('K',113);
		hC.put('E',105);
		hC.put('R',122);
	}
	@Test
	void getTest() {
		setup1();
		assertEquals("Annyeonghaseyo",hS.get("Korean"));
		assertEquals("Hello",hS.get("English"));
		
		setup2();
		assertEquals("SKT T1",hI.get(2015));
		assertEquals("Sublime",hI.get(4));
		
		setup3();
		assertEquals(106,hC.get('F'));
		assertEquals(113,hC.get('K'));
	}
	
	@Test
	void removeTest() {
		setup1();
		assertEquals("Hola",hS.remove("Spanish"));
		assertEquals("Hello",hS.remove("English"));
		
		
		setup2();
		assertEquals("SL",hI.remove(126));
		assertEquals("Sublime",hI.remove(4));
		
		setup3();
		assertEquals(101,hC.remove('A'));
		assertEquals(105,hC.remove('E'));
		assertEquals(113,hC.remove('K'));
		assertEquals(106,hC.remove('F'));
		assertEquals(122,hC.remove('R'));
	}
	
	@Test
	void sizeTest() {
		
		setup1();
		assertEquals(3, hS.size());
		
		
		setup2();
		assertEquals(3, hI.size());
		
		setup3();
		assertEquals(5, hC.size());
	}

}
