package main.java.threads;

// Deadlock describes a situation where two or more threads are blocked forever, waiting for each other

// Problem: Two friends bow each other. When I have bowed you, you should also bow me.. and it goes on and on...

public class Synchronized_Deadlock_BowerExample{
	
	
	static FriendBow ashish = new FriendBow("Ashish");
	static FriendBow param = new FriendBow("Param");

	
	public static void main(String[] args) {
		
		AshishBowParam ap = new AshishBowParam();
		Thread apt = new Thread(ap);
		apt.start();
		
		ParamBowAshish pa = new ParamBowAshish();
		Thread pat = new Thread(pa);
		pat.start();
	}

}

class AshishBowParam implements Runnable{
	public void run() {
		Synchronized_Deadlock_BowerExample.ashish.bow(Synchronized_Deadlock_BowerExample.param);
	}
}

class ParamBowAshish implements Runnable{
	public void run() {
		Synchronized_Deadlock_BowerExample.param.bow(Synchronized_Deadlock_BowerExample.ashish);
	}
}

class FriendBow {
	
	String name = null;
	
	public FriendBow(String n) {
		this.name = n;
	}
	
	// when a thread comes here, it acquires lock of an instance of FriendBow - ashish OR param here.
	// when ashish.bow.param is run from one thread - thread comes here, acquires lock of ashish, executes bow, prints message
	// and then goes to bowMeBack, Now bowMeBack says param.bow.ashish - so it tries to acquire lock on Object param so that it can execute param.bow(ashish)
	// but Object param has already been locked by thread "pat" from main class, when it would have first executed run() from ParamBowAshish class.
	// and that "pat" thread would be waiting to get lock of ashish Object when it would have entered bowMeBack.
	
	// thus, it leads to a race condition when a deadlock has occured
	public synchronized void bow(FriendBow myFriend) {
		System.out.println(this.name + " bowed " + myFriend.name);
		bowMeBack(myFriend);
	}
	
	public synchronized void bowMeBack (FriendBow myFriend) {
		myFriend.bow(this);
	}
}