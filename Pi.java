import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi {

	public static void main(String[] args) {
		
		AtomicInteger count = new AtomicInteger(0);
		int numThreads = Integer.parseInt(args[0]);
		int iterations = Integer.parseInt(args[1]);
		double pi;
		 
		 
		ArrayList<Thread> allThreads = new ArrayList<Thread>();
		
		for(int i=0;i<numThreads;i++){
			
			   Thread temp = new Thread(() -> {
				   double x,y;
				   
				   double z;
						   for (int j=0; j<iterations/numThreads; j++) {
							      x = ThreadLocalRandom.current().nextDouble();
							      y = ThreadLocalRandom.current().nextDouble();
							      z = x*x+y*y;
							      if (z<=1) count.incrementAndGet();
							      }
					
				    });	  
			allThreads.add(temp);
		}
	
		
		 
		  
		for(Thread temp: allThreads){
			temp.start();
		}
		
		  
		
			
			try {
				for(Thread temp: allThreads){
					temp.join();
				}
			} catch (InterruptedException iex) { }

		  
		   double ratio = (double) count.get()/iterations;
		   pi = ratio *4;
		   System.out.println("Total: " + iterations);
		   System.out.println("Inside: " + count.get());
		   System.out.println("Ratio: " + ratio);
		   System.out.println("Pi: " + pi);
		  

		
	}
	
	


	
}
