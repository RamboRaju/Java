/*
Thread.sleep() method can be used 
to pause the execution of current thread for specified time in milliseconds. 
The argument value for milliseconds can’t be negative, 
else it throws IllegalArgumentException
	*/
public class ThreadSleep {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread.sleep(2000);
		System.out.println("Sleep time in ms = " + (System.currentTimeMillis() - start));
	}
}