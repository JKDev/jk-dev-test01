package test.thread;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadEndExeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// proccess - dispose
		Runtime.getRuntime().addShutdownHook(newStopThread());
		
	}

	// JDK7
	private static Thread newStopThread() {
		
		return new Thread( new FutureTask<Void>( new Callable<Void>(){

			@Override
			public Void call() throws IOException {
				// TODO Auto-generated method stub
				System.out.println( "dispose()" );
				
				
				try{
					Thread.currentThread().sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				return null;
			}
			
		} ));
		
	}

}
