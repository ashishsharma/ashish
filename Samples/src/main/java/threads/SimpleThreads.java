package main.java.threads;

// Note - these threads are present in java.lang.thread

public class SimpleThreads implements Runnable {

	public static void main(String[] args) {

		SimpleThreads st = new SimpleThreads();
		Thread t = new Thread(st);
		t.start();
		
		// Try the program with interrupt statement and without it.
		// It basically interrupts thread t and it stops what it was doing and goes into catch.
		if(t.isAlive())
			t.interrupt();

		// If t.join is not there, "Finishing main!" will be printed directly after the thread t is started (and is still executing)
		// So in threads, as soon as a thread is invoked - the INVOKING thread (main thread here) moves forward and continues with its processing
		try{
			// With t.join() in main thread - we tell main thread to wait until thread t finishes
			// A.join() tells the thread executing this code to wait for thread A till A finishes
		 t.join();
		} catch (InterruptedException e) {
			System.out.println("Catched Interruption");
		}
		
		System.out.println("Finishing main!");
	}

	public void run() {
		try {
			for (int i = 0; i < 3; i++) {
				System.out.println(i);
				System.out.println("Going to Sleep");
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}

	}
}

// Notes: 
// yield() - Causes the currently executing thread object to temporarily pause and allow other threads to execute.

// Thread priorities
// setPriority(...) and getPriority() methods. setPriority(...) - passing in an integer representing its priority. The number should lie in the range of two constants MIN_PRIORITY and MAX_PRIORITY defined on Thread, and will typically reference NORM_PRIORITY, the default priority of a thread if we don't set it to anything else.
// depending on your OS and JVM version, Thread.setPriority() may actually do nothing at all
// what thread priorities mean to the thread scheduler differs from scheduler to scheduler