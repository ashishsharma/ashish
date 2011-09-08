package main.java.threads;

// Problem :
// producer, that creates the data, and the consumer, that does something with it. 
// The two threads communicate using a shared object. Coordination is essential: 
// the consumer thread must not attempt to retrieve the data before the producer thread has delivered it, 
// and the producer thread must not attempt to deliver new data if the consumer hasn't retrieved the old data.

public class WaitNotify_ProducerConsumer {
	
	public static void main(String[] args) {
		MessagePasser mp = new MessagePasser();
		
		Producer p = new Producer();
		p.setMessagePasser(mp);
		Thread producerThread = new Thread(p);
		producerThread.start();
		
		Consumer c = new Consumer();
		c.setMessagePasser(mp);
		Thread consumerThread = new Thread(c);
		consumerThread.start();
	}

}

/* ********************* This is the object that makes thread to wait & notify ********************* */ 

class MessagePasser {

	private String message = null;
	private boolean isEmpty = true;

	
	// IMPORTANT - When a thread invokes wait, it must own the intrinsic lock for the object Ñ otherwise at runtime java.lang.IllegalMonitorStateException is thrown. 
	// Invoking wait inside a synchronized method is a simple way to acquire the intrinsic lock.
	
	public synchronized String get() {
		
		// wait should always in while loop and check of a condition
		// because any time InterruptedException is thrown on a thread, you don't know if that exception if for wait or anything else.
		// there may be a Thread.sleep in program which might have been interrupted. So, wait is also interrupted but we don't want to come out of wait() until message is isEmpty
		// so we should not use if (isEmpty) { wait(); } - should always be in a conditional while(..) and surrounded by try catch for InterruptedException.
		
		while (isEmpty) {
			try {
				System.out.println("get in wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Once the wait was interrupted we again checked (because of while loop) - and if it came out of while - that means isEmpty is false. That means there is message that can be comsumed. 
		System.out.println("Hurray! Consuming message - " + this.message);
		
		// Once message is consumed, we should enable isEmpty so that again the new message can be put.
		isEmpty = true;
		
		// notifying other waiting threads so that new message can be put
		notifyAll();
		return message;
	}

	// Note: There is a second notification method, notify(), which wakes up a single thread. 
	// Because notify doesn't allow you to specify the thread that is woken up, it is useful only in massively parallel applications Ñ 
	// that is, programs with a large number of threads, all doing similar chores. In such an application, you don't care which thread gets woken up
	
	public synchronized void put(String m) {

		while (!isEmpty) {
			try {
				System.out.println("put in wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.message = "HELLO! " + m;
		System.out.println("Put Message - " + this.message);
		isEmpty = false;
		notifyAll();
	}
}

/* ********************* Producer ********************* */ 


// wait & notify are called for the thread on the OBJECT in which they are declared
// Earlier I did a mistake of creating a new instance of MessageParser in Producer & Consumer.
// So, every thread got its own MessageParser object
// So, any wait and notify that were being called, they were called on separate objects on separate threads and thus, there was no communication between the threads. 
// Always remember, in order for threads to communicate via wait and notify, threads should share the one object that has the logic for wait & notify
// So, I created the MessagePasser object outside and passed the same reference to Producer & Consumer threads

class Producer implements Runnable {	
	
	MessagePasser mp = null;
	
	public void setMessagePasser(MessagePasser mp) {
		this.mp = mp;
	}
	
	// this is a mistake ->
	// MessagePasser mp = new MessagePasser();
	// threads should share the one object that has the logic for wait & notify
	
	public void run() {
		for (int i=0; i< 10; i++) {
			mp.put("" + i);
		}
	}
}



/* ********************* Consumer ********************* */ 

class Consumer implements Runnable {
	
	MessagePasser mp = null;
	
	public void setMessagePasser(MessagePasser mp) {
		this.mp = mp;
	}
	
	public void run() {
		for (int i=0; i< 10; i++) {
			mp.get();
		}
	}
}