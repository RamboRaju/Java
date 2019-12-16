/*
The threads are run in such a way that it will be 
able to acquire lock on the first object but when 
it’s trying to acquire lock on second object, it 
goes on wait state because it’s already locked by 
another thread. 

This forms a cyclic dependency for resource between 
Threads causing deadlock.

To get thread dump ->
ps -eaf | grep java  then  jstack PID or jstack PID >> mydumps.tdump

alternatively
kill -3 PID

alternatively (java 8 or higher use this instead of jstack)
jcmd PID Thread.print

How to avoid deadlock in java - 
Avoid Nested Locks
Lock Only What is Required
Avoid waiting indefinitely

*/

public class ThreadDeadLock {
	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
		Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
		Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

		t1.start();
		Thread.sleep(5000);

		t2.start();
		Thread.sleep(5000);

		t3.start();
		Thread.sleep(5000);
	}
}

class SyncThread implements Runnable {
	private Object obj1;
	private Object obj2;

	public SyncThread(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}

	@Override
	// dead lock
	/*public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " acquiring lock on " + obj1);
		synchronized (obj1) {
			System.out.println(name + " acquired lock on " + obj1);
			work();
			System.out.println(name + " acquiring lock on " + obj2);
			synchronized (obj2) {
				System.out.println(name + " acquired lock on " + obj2);
				work();
			}
			System.out.println(name + " released lock on " + obj2);
		}
		System.out.println(name + " released lock on "+obj1);
        System.out.println(name + " finished execution.");
	}*/

	// solution avoiding nested locking
	public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " acquiring lock on " + obj1);
        synchronized (obj1) {
            System.out.println(name + " acquired lock on " + obj1);
            work();
        }
        System.out.println(name + " released lock on " + obj1);
        System.out.println(name + " acquiring lock on " + obj2);
        synchronized (obj2) {
            System.out.println(name + " acquired lock on " + obj2);
            work();
        }
        System.out.println(name + " released lock on " + obj2);

        System.out.println(name + " finished execution.");
    }

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}