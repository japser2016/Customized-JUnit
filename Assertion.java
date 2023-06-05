public class Assertion {
    /* You'll need to change the return type of the assertThat methods */
    static A assertThat(Object o) {
	//throw new UnsupportedOperationException();
		return new A(o);
    }
    static B assertThat(String s) {
	//throw new UnsupportedOperationException();
		return new B(s);
    }
    static C assertThat(boolean b) {
	//throw new UnsupportedOperationException();
		return new C(b);
    }
    static D assertThat(int i) {
	//throw new UnsupportedOperationException();
		return new D(i);	
    }
}