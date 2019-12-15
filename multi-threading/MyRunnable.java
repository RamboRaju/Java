public class MyRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("MyRunnable - START " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyRunnable - END " + Thread.currentThread().getName());
	}
}