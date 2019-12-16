/*
Use synchronized block inside the if loop and volatile variable

Pros:

    Thread safety is guaranteed
    Client application can pass arguments
    Lazy initialization achieved
    Synchronization overhead is minimal and applicable only for 
    first few threads when the variable is null.

Cons:

    Extra if condition

Simply put, the volatile keyword marks a variable that always goes to the main memory, 
for both reads and writes, of the multiple threads accessing it (visibility feature).

Local variable result seems unnecessary. But it’s there to improve the performance of our code. 
In cases where the instance is already initialized (most of the time), 
the volatile field is only accessed once (due to “return result;” instead of “return instance;”). 
This can improve the method’s overall performance by as much as 25 percent.

*/
public class ASingleton {
	private static volatile ASingleton instance;
	private static Object mutex = new Object();

	private ASingleton() { }

	public static ASingleton getInstance() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " thread getting instance");

		ASingleton result = instance;
		if (result == null) {
			synchronized (mutex) {
				System.out.println(name + " thread inside synchronized block");
				result = instance;
				if(result == null) {
					System.out.println(name + " thread inside null-check in synchronized block");
					instance = result = new ASingleton(); 
				}
			}
		}
		return result;
	}

}