import java.util.*;
public class D {
	int i;
	
	public D(int ii) {
		i = ii;
	}
	
	public D isEqualTo(int i2){
		if (i != i2)
		{
			throw new RuntimeException("D: int not equal");
		}
		return new D(i);
	}
	public D isLessThan(int i2){
		if (i >= i2)
		{
			throw new RuntimeException("D: int not less than");
		}
		return new D(i);
	}
	public D isGreaterThan(int i2){
		if (i <= i2)
		{
			throw new RuntimeException("D: int not greater than");
		}
		return new D(i);
	}
	
	
}