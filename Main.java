import java.util.*;
public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void test_Tests() {
		//System.out.println(Unit.testClass("ListTests"));
		Map<String, Throwable> t = Unit.testClass("ListTests");
		Map<String, Throwable> expect = new HashMap<>();
		expect.put("AA",null);
		expect.put("BB",null);
		expect.put("CC",null);
		assert expect.equals(t);
    }
	
	public static void test_L1() {
		Map<String, Throwable> t = Unit.testClass("L1");
	}
	
	public static void test_qc() {
		Map<String, Object[]> result = Unit.quickCheckClass("PPPTest");
	}

	public static void test1(){
		//test_Tests();
		test_L1();
		

	}
	
	public static void test2(){
		//test_qc();

	}
	

    
    public static void main(String[] args) {
		test1();
		test2();
    }

}
