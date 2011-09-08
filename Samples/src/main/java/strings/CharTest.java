import java.util.ArrayList;


public class CharTest {

	public static void main(String[] args) throws InterruptedException {
		
		while (true) {
		Thread.sleep(2000);
		
		System.out.println(args == null);
		
		
		ArrayList<String> aa = new ArrayList<String>();
		Object a = 1;
		
		//int aa = a;
		
		TestStatic s = new TestStatic();
		
		s.var = 20;
		
		System.out.println(TestStatic.var);

		s.myfxn();
		
		TestStatic.myfxn();
		
		
		//System.out.println(32000 * 32);
		//System.out.println(Math.pow(2, 10));
		long l = 4000000000L;
		
		long r = l / 8;
		System.out.println(r);
		
		//int a = 1700;
		
		//byte a = 122;
		
		//System.out.println(Integer.toBinaryString(a).length());
		char[] c = { 'a', 'b' };

		// Integer.toBinaryString(4)

		//System.out.println(bitSwapRequired(3, 7));
		}
	}

	public static int bitSwapRequired(int a, int b) {
		int count = 0;
		for (int c = a ^ b; c != 0; c = c >> 1) {
			count += c & 1;
		}
		return count;
	}

}


class TestStatic {
	 
	public static int var = 10;
	
	public static void myfxn() {
		System.out.println("ffff");
	}
	
}
