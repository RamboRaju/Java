	/*
	we know that multiple threads created from same 
	Object share object variables and this can lead
	to data inconsistency when the threads are used 
	read and update the shared data

	The reason for data inconsistency is because updating 
	any field value is not an atomic process, it requires 
	three steps; 
	first to read the current value, 
	second to do the necessary operations to get the updated value 
	and third to assign the updated value to the field reference.
	*/

	public class ThreadSafety {
	public static void main(String[] args) throws InterruptedException {
		ProcessingThread pt = new ProcessingThread();
		Thread t1 = new Thread(pt, "t1");
		t1.start();
		Thread t2 = new Thread(pt, "t2");
		t2.start();
		//wait for threads to finish processing
		t1.join();
		t2.join();
		System.out.println("Processing count= " + pt.getCount());
	}
	}

	class ProcessingThread implements Runnable {
	private int count;

	@Override
	public void run() {
		for(int i=1; i<5; i++){
			processSomething(i);
			count++;
		}
	}

	public int getCount() { return this.count; }

	private void processSomething(int i) {
		// processing some job
		try {
			Thread.sleep(i*1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	}