public class ASingletonTest {
	public static void main(String[] args) throws InterruptedException {
	    // ASingleton singleton = ASingleton.getInstance();
		MyRunnable1 mr = new MyRunnable1();
		Thread t1 = new Thread(mr, "t1");
		Thread t2 = new Thread(mr, "t2");
		Thread t3 = new Thread(mr, "t3");

		// start all the threads
		t1.start(); t2.start(); t3.start();
	}
}

class MyRunnable1 implements Runnable {
	private ASingleton singleton;

	public MyRunnable1() { }

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");		
		System.out.println(name + " singleton: " + ASingleton.getInstance());
	}
}