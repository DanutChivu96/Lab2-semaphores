package prod_cons;

import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Semaphore;

public class Cons extends Thread {
	
	Queue<Integer> queue; 
	Semaphore semFree;
	Semaphore semFull;
   	private Lock lock;
    
	public Cons(Queue<Integer> queue, Lock lock, Semaphore semFree, Semaphore semFull) {

		this.queue = queue;
		this.lock=lock;
		this.semFree = semFree;
		this.semFull = semFull;
	}
    
	public void consume(int nr)  {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void run()
    {
    	int nr=0;
    	while(true){//cat e adevarata 
    		
    		
    		
    		try {
				semFull.acquire();//ocupa semaforul
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		synchronized(lock){
    			
    			if(!queue.isEmpty()){//daca coada nu e goala
        	        nr=queue.remove();//scoate un element
        	    	System.out.println("S-a extras"+nr);
        	    }
    			
    			semFree.release();//se face release la semafor
    		}
               consume(nr);//consuma
    			  
    	}	
    } 
}
