package main.java.threads;

import java.util.concurrent.locks.*;

public class Lock_Solving_Deadlock {
	
	
	static FriendBower ashish = new FriendBower("Ashish");
	static FriendBower param = new FriendBower("Param");

	
	public static void main(String[] args) {
		BowLoop apLoop = new BowLoop(ashish, param);
		Thread t = new Thread(apLoop);
		t.start();
		
		BowLoop paLoop = new BowLoop(param, ashish);
		Thread t2 = new Thread(paLoop);
		t2.start();
	}

}

class BowLoop implements Runnable {
	
	FriendBower me = null;
	FriendBower myFriend = null;
	
	public BowLoop(FriendBower me, FriendBower myFriend){
		this.me = me;
		this.myFriend = myFriend;
	}
	
	public void run() {
		while(true) {
			me.bow(myFriend);
		}
	}
}


class FriendBower {
	
	private final Lock lock = new ReentrantLock();
	
	private String name;
	
	public FriendBower (String n) {
		this.name = n;
	}
	
	public void bow(FriendBower myFriend) {
		// acquire both locks first before doing anything
		boolean locksAcquired = acquireLocks(myFriend);
		if(locksAcquired) {
			try {
				System.out.println(this.name + " bowed " + myFriend.name);
				bowMeBack(myFriend);
			} finally {
				// remember to unlock
				lock.unlock();
				myFriend.lock.unlock();
			}
		}			
	}
	
	public void bowMeBack(FriendBower myFriend) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(myFriend.name + " bowed " + this.name);
	}
	
	private boolean acquireLocks(FriendBower myFriend) {
		boolean myLock = this.lock.tryLock();
		boolean myFriendLock = myFriend.lock.tryLock();
		if(myLock && myFriendLock)
			return true;
		else {
			if(myLock)
				this.lock.unlock();
			if(myFriendLock)
				myFriend.lock.unlock();
		}
		return false;
	}
	
	
	
	
}
