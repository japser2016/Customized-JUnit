import java.util.*;

public class L1 {
	
	@Test
	public void BB() {
		System.out.println("BB@Test");
	}
	
	@BeforeClass 
	public static void ZZ() {
		System.out.println("ZZ@BeforeClass ");
	}
	
	@AfterClass 
	public static void XX() {
		System.out.println("XX@AfterClass ");
	}
	
	@AfterClass 
	public static void YY() {
		System.out.println("YY@AfterClass ");
	}
	
	@BeforeClass 
	public static void DD() {
		System.out.println("DD@BeforeClass ");
	}
	
	@Test
	public void WW() {
		System.out.println("WW@Test");
	}
	
	@AfterClass 
	public static void CC() {
		System.out.println("CC@AfterClass ");
	}
	
	@Test
	public void QQ() {
		System.out.println("QQ@Test");
	}
	
	@BeforeClass 
	public static void AA() {
		System.out.println("AA@BeforeClass ");
	}
	
	
	
	@Before
	public void HH() {
		System.out.println("HH@Before");
	}
	
	@After
	public void KK() {
		System.out.println("KK@After");
	}
	
	@Before
	public void JJ() {
		System.out.println("JJ@Before");
	}
	
	@After
	public void GG() {
		System.out.println("GG@After");
	}
	
	@Before
	public void EE() {
		System.out.println("EE@Before");
	}
	
	
	@After
	public void FF() {
		System.out.println("FF@After");
	}
	
	
}