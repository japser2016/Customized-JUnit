import java.util.*;

public class A {
	Object o;
	
	public A(Object oo) {
		o = oo;
	}
	
	public A isNotNull(){
		if (o == null)
		{
			throw new RuntimeException("The object is null!");
		}
		return new A(o);
	}
	public A isNull(){
		if (o != null)
		{
			throw new RuntimeException("The object is not null!");
		}
		return new A(o);
	}
	public A isEqualTo(Object o2){
		if (!o.equals(o2))
		{
			throw new RuntimeException("They are not equal!");
		}
		return new A(o);
	}
	public A isNotEqualTo(Object o2){
		if (o.equals(o2))
		{
			throw new RuntimeException("They are equal!");
		}
		return new A(o);
	}
	public A isInstanceOf(Class c){
		if ( !(c.isInstance(o)))
		{
			throw new RuntimeException("Not instanceof!");
		}
		return new A(o);
	}
	
}