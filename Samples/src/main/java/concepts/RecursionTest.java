package main.java.concepts;

public class RecursionTest {

	public static void main(String[] args) {
		RecursionTest r = new RecursionTest();
		r.f(5);
	}

	public void f(int i) {
		if (i == 0)
			return;
		Integer iObj = new Integer(i);
		f(i - 1);
		System.out.println(iObj.intValue());
	}

}
