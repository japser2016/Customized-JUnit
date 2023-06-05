import java.util.*;
public class C {
	boolean b;
	
	public C(boolean bb) {
		b = bb;
	}
	
	public C isEqualTo(boolean b2){
		if (b != b2)
		{
			throw new RuntimeException("C: not equal");
		}
		return new C(b);
	}
	public C isTrue(){
		if (b == false)
		{
			throw new RuntimeException("C: not true");
		}
		return new C(b);
	}
	public C isFalse(){
		if (b == true)
		{
			throw new RuntimeException("C: not false");
		}
		return new C(b);
	}
	
}