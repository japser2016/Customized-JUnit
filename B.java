import java.util.*;
public class B {
	String o;
	
	public B(String oo) {
		o = oo;
	}
	
	public B isNotNull(){
		if (o == null)
		{
			throw new RuntimeException("The object is null!");
		}
		return new B(o);
	}
	public B isNull(){
		if (o != null)
		{
			throw new RuntimeException("The object is not null!");
		}
		return new B(o);
	}
	public B isEqualTo(Object o2){
		if (!o.equals(o2))
		{
			throw new RuntimeException("They are not equal!");
		}
		return new B(o);
	}
	public B isNotEqualTo(Object o2){
		if (o.equals(o2))
		{
			throw new RuntimeException("They are equal!");
		}
		return new B(o);
	}
	public B startsWith(String s2){
		if (!o.startsWith(s2))
		{
			throw new RuntimeException("Not Start With!");
		}
		return new B(o);
	}
	public B isEmpty(){
		if (o.length() != 0)
		{
			throw new RuntimeException("Not Empty!");
		}
		return new B(o);
	}
	public B contains(String s2){
		if (!o.contains(s2))
		{
			throw new RuntimeException("Not contains!");
		}
		return new B(o);
	}
	
	
}