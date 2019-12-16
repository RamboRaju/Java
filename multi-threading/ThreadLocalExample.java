/*
Java ThreadLocal is used to create thread local variables. 
We know that all threads of an Object share it’s variables, 
so the variable is not thread safe. We can use synchronization 
for thread safety but if we want to avoid synchronization, 
we can use ThreadLocal variables.

*/
import java.text.SimpleDateFormat;
import java.util.Random;

public class ThreadLocalExample implements Runnable {
	// SimpleDateFormat is not thread-safe, so give one to each thread
	/*private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat intialValue() {
			return new SimpleDateFormat("yyyyMMdd HHmm");
		}
	};*/

	// lambda 
	private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.<SimpleDateFormat>withInitial(
		() -> { return new SimpleDateFormat("yyyyMMdd HHmm"); });

	public static void main(String[] args) throws InterruptedException {
		ThreadLocalExample obj = new ThreadLocalExample();
		for(int i=0; i<10; i++) {
			Thread t = new Thread(obj, ""+i);
			Thread.sleep(new Random().nextInt(1000));
			t.start();
		}		
	}

	@Override
	public void run() {
		System.out.println("Thread name= " + Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		//formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
	}
}