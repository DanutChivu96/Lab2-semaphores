package prod_cons;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Semaphore;

public class Prod extends Thread {
	
	
	Queue<Integer> queue;
	int size=10;
	private Lock lock;
	Random rand=new Random();
	Semaphore semFree;
	Semaphore semFull;
	
	
	public Prod(Queue<Integer> queue, Lock lock, Semaphore semFree, Semaphore semfull, Semaphore semFull) {
		
		this.queue = queue;
		this.lock=lock;
		this.semFree=semFree;
		this.semFull=semFull;
	}
	
	public void produce(int nr){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int nr=0;
		while(true){//cat timp e adv 
			
			produce(nr); //mai intai produce
			
			try {
				semFree.acquire();//ocupa semaforul
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized(lock){
				if(queue.size()<size){//daca lungimea cozii mai mica decat nr maxim de elemente 
				     nr=rand.nextInt(size);//se genereaza nr
					queue.add(nr);//se adauga nr generat in coada
					System.out.println("S-a produs"+nr);
				}
				semFull.release();//se elibereaza semaforul
			}

		}
		
	}

}