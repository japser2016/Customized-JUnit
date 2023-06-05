//import static org.junit.Assert.*;
//import org.junit.*;
import java.util.*;

public class ListTests {
	
	@Test 
	public void BB() {
		System.out.println("BB");
	}
	
	@Test 
	public void CC() {
		System.out.println("CC");
	}
	
	@Test 
	public void AA() {
		System.out.println("AA");
	}
}


/*

//import static org.junit.Assert.*;
//import org.junit.*;
import java.util.*;

public class ListTests {
	static List<Object> l;
	static Object o;
	
	public @AfterClass static void endup(){
		;
	}
	
	public @After void aft2(){
		;
	}
	
	
	
	public @Test void testIsEmpty(){
		List<Object> l = new LinkedList<>();
		//assertTrue("list should be empty", l.isEmpty());
	}
	
	public @Test void testAdd(){
		l.add(o);
		//assertTrue("list should contain o", l.contains(o));	
	}
	
	public @After void aft1(){
		;
	}
	
	public @Test void testRemove() {
		l.remove(o);
		//assertFalse("list should not contain o", l.contains(o));
	}
	
	
	public @BeforeClass static void setup(){
		l = new LinkedList<>();
		o = new Object();
	}
	public @Before void bef(){
		l.clear();
	}
	
	
}

public class ListTests {
	@Test 
	public void testAdd() {
		List<Object> l = new LinkedList<>();
		Object o = new Object();
		l.add(o);
		//assertTrue("list should contain o", l.contains(o));
	}
	@Test 
	public void testIsEmpty() {
		List<Object> l = new LinkedList<>();
		//assertTrue("list should be empty", l.isEmpty());
	}
}
*/