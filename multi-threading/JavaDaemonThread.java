/*
When a thread is marked as daemon thread, JVM doesn’t wait it 
to finish to terminate the program. As soon as all the user 
threads are finished, JVM terminates the program as well as 
all the associated daemon threads.

Thread.setDaemon(true) is used to create a daemon thread in java. 
This method should be invoked before the thread is started otherwise 
it will throw IllegalThreadStateException.

We can check if a thread is daemon thread or not by calling 
isDaemon() method on it.
Another point is that when a thread is started, it inherits 
the daemon status of it’s parent thread.

When we execute above daemon thread program, JVM creates 
first user thread with main() method and then a daemon thread.

When main method is finished, the program terminates and daemon 
thread is also shut down by JVM.

*/

public class JavaDaemonThread {
	public static void main(String[] args) throws InterruptedException {
		Thread dt = new Thread(new DaemonThread(), "dt");
		dt.setDaemon(true);
		dt.start();
		// continue program
		Thread.sleep(10000);
		System.out.println("Finishing program");
	}
}

class DaemonThread implements Runnable {
	@Override
	public void run() {
		while(true) {
			processSomething();
		}
	}

	private void processSomething() {
		try {
			System.out.println("Processing daemon thread");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}