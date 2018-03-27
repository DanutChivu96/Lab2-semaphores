
package prod_cons;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Main {
	
	private static Lock lock;
 
	public static void main (String[] args) throws InterruptedException {
		int n=5;
		Queue<Integer> queue = new LinkedList<Integer>();
		lock = new ReentrantLock();
		Semaphore semFree = new Semaphore(n);
		Semaphore semFull = new Semaphore(0);
		Thread producer = new Thread(new Prod(queue, lock, semFree, semFull, semFull));
		Thread consumer = new Thread(new Cons(queue, lock, semFree, semFull));
		producer.start();
		consumer.start();
		try{
			producer.join();
			consumer.join();
		}
		catch (Exception exception){
			System.out.print(exception);
		}
		
		
	}	
}