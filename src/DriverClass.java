/**
 * 
 */

/**
 * @author Sagar Shinde
 *
 */
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* Consider a small warehouse where we have two products Pen and Book
 * stored at locations "First Location" and "Second Location" respectively.
 * */
public class DriverClass{
	static IMS warehouse;
	public static void main(String[] args) {
		// Code to create a small warehouse starts
		Product pen=new Product("PenID", 50, "First Location");	// initializing product Pen with quantity 50 and product Book with quantity 150
		Product book=new Product("BookID", 150, "Second Location");	// assuming Pen and Book are the only two products in our warehouse
		Set<Product> hs = Collections.newSetFromMap(new ConcurrentHashMap<Product, Boolean>());
		hs.add(pen);
		hs.add(book);
		warehouse=new IMS(hs);
		// Code to create a small warehouse ends
		
		//This code is for multithreading which creates two threads
		DriverClass d=new DriverClass();
		new Demo(d, "First Thread");
		new Demo(d, "Second Thread");
	}
	void caller(){
		Thread thread=Thread.currentThread();
		if(thread.getName().equals("First Thread")){
			// Below line is for picking up 10 quantities of product Pen
			PickingResult pr=warehouse.pickProduct("PenID", 10);
			System.out.println("Quantity of "+pr.productId+" located at '"+pr.location+"' after executing thread "+thread.getName()+" is "+pr.quantity);
		}else if(thread.getName().equals("Second Thread")){
			// Below line is for picking up 20 quantities of product Pen
			PickingResult pr=warehouse.pickProduct("PenID", 20);
			System.out.println("Quantity of "+pr.productId+" located at '"+pr.location+"' after executing thread "+thread.getName()+" is "+pr.quantity);
		}
		/*
		 * In the above two threads, as we are calling methods for the same product, i.e., Pen,
		 * the synchronization needs to be implemented
		 * */
	}
}

class Demo implements Runnable{	// for multithreading
	DriverClass dc;
	Thread thread;
	Demo(){
	}
	Demo(DriverClass driverclass, String threadName){
		this.dc=driverclass;
		thread=new Thread(this, threadName);
		thread.start();
	}
	public void run(){
		dc.caller();
	}
}