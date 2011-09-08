package main.java.threads;

// HIGH LEVEL CONCURRENCY - through java.util.concurrent

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Executor Interface:
// The Executor interface provides a single method, execute, to replace (new Thread(r)).start(); with e.execute(r);
// It creates & launches new thread immediately. It uses an existing worker thread to run r, or to place r in a queue to wait for a worker thread to become available. 

// ExecutorService Interface: Subinterface of Executor
// It provides more versatile submit method. Like execute, submit accepts Runnable objects, but ALSO accepts Callable objects, 
// which allow the task to return a value. The submit method returns a Future object, which is used to retrieve the Callable return.
// It provides a number of methods for managing the shutdown of the executor. 

// The ScheduledExecutorService interface : Subinterface of ExecutorService
// It executes a Runnable or Callable task after a specified delay or repeated intervals (scheduleAtFixedRate and scheduleWithFixedDelay)

public class Executor_ThreadPools {

	public static final int nThreads = 10;
	
	// Note - There is no "new" instantiation here
	// It creates a thread pool of nThreads and allocate these worker threads to execute Runnable passed into .execute(..)
	// When thread finishes - it comes back to the pool and again is allocated to another waiting runnable in a queue
	
	public static ExecutorService e = Executors.newFixedThreadPool(nThreads);
	public static Random random = new Random();
	
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			MyTask runnableObj = new MyTask();
			runnableObj.setNum(i);
			
			// assigning new runnable objects to threads in a pool
			// note that the task that we submit has to be a thread class (implementing runnabe or extending thread)
			e.execute(runnableObj);
		}

		// initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted. 
		// Invocation has no additional effect if already shut down.
		e.shutdown();
		try {
			// Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.
			e.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

class MyTask implements Runnable {

	private int num;

	public void setNum(int n) {
		this.num = n;
	}

	public void run() {
		//doToRandomSleep();
		System.out.println("This is the num - " + this.num);
	}
	
	private void doRandomSleep() {
		try {
			Thread.sleep(Executor_ThreadPools.random.nextInt(5)  * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}