//import static org.junit.Assert.*;
//import org.junit.*;
import java.util.*;

public class PPPTest {
	
	@Property
	public boolean absNonNeg(@IntRange(min=-10, max=2) Integer i) {
		return i.intValue() >= 0;
	}
	
	
	@Property
	public boolean t1(@IntRange(min=-10, max=2) Integer i, @StringSet(strings={"hello"}) String s) {
		return i.intValue() >= 0;
	}
	
	
	@Test
	public void BB() {
		System.out.println("BB");
	}
	
	@BeforeClass 
	public void CC() {
		System.out.println("CC");
	}
	
	@BeforeClass 
	public void AA() {
		System.out.println("AA");
	}
	
	
	@Property
	public boolean s1(@StringSet(strings={"hello", "sdf", "abc"}) String s) {
		return s.contains("e");
	}
	
	
	@Property
	//public boolean f1(@ListLength(min=0, max=1) List<@ListLength(min=0, max=2) List<@IntRange(min=5, max=7) Integer>> ints){
	public boolean f1(@ListLength(min=0, max=2) List<@IntRange(min=5, max=7) Integer> ints){
		if (ints.size() == 0){
			return false;
		}
		//return ints.get(0) >= 3;
		return true;
	}
	
	int count = 0;  
	public Object genIntSet() {  
		Set s = new HashSet();  
		for (int i=0; i<count; i++) { 
			s.add(i); 
		}  
		count++;  
		return s;  
	}  
	
	@Property
	public boolean o1(@ForAll(name="genIntSet", times=10) Object o) {
		Set s = (Set) o;  
		s.add("foo");  
		return s.contains("foo");  
	}
	
	
	
	
}