package main.java.threads;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Future_Callable {

	private static final int nThreads = 3;
	public static ExecutorService e = Executors.newFixedThreadPool(nThreads);

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		List<String> outputs = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			MyCallable myCallable = new MyCallable();
			myCallable.setNum(i);
			
			// On the executor you can use the method submit to "submit" (instead of .execute(..)) a Callable and to get a future. 
			// To retrieve the result of the future use the get() 
			Future<String> future = e.submit(myCallable);
			outputs.add(future.get());
		}

		for (String o : outputs) {
			System.out.println(o);
		}
		
		e.shutdown();
		
	}
}

// For threads to return a computed result/ per thread- use java.util.concurrent.Callable. 
// Callable uses generic to define the type of object which is returned.
// It implements Callable<T> instead of Runnable in normal thread
// it should provide an implementation of call() function instead of run()
// It should return a value

class MyCallable implements Callable<String> {

	private int num;

	public void setNum(int n) {
		this.num = n;
	}

	public String call() {
		int sum = 0;
		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		return "Sum of " + this.num + " numbers is: " + sum;
	}

}