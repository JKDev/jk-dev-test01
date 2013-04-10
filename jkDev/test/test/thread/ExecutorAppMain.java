package test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.jk.task.ActionCallable;
import com.jk.task.CallableMaker;
import com.jk.task.IAction;

public class ExecutorAppMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testCall();
	}

	//++++
	private static void testCall() {
		// TODO Auto-generated method stub
		int nThreads = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		
		//
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);  //
		
		CallableMaker<TItem, String> m = new CallableMaker( 30 );
		Callable<String> c1 = m.newCallable(new UserAction());  // 최종 return 되는 값
		Callable<String> c2 = m.newCallable(new UserAction());  // 최종 return 되는 값
		Callable<String> c3 = m.newCallable(new UserAction());  // 최종 return 되는 값
		
		completionService.submit( c1 );
		completionService.submit( c2 );
		completionService.submit( c3 );
		
		TItem tmp = null;
		for(int i=0;i<400;i++){ //
			tmp = new TItem();
			tmp.setCmd("cmd_" + i);
			m.add( tmp );
			try{
				Thread.currentThread().sleep(10);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		
		//
		tmp = new TItem();
		tmp.setCmd("STOP");
		m.add(tmp);
		
		System.out.println( " step001 " );
		//
		for(int i=0;i<3;i++){
			try {
			
				Future<String> future = completionService.take(); // stop // 1.2.3.	
				String endMessage = future.get();
				System.out.println( "Final endMessage:" + endMessage );
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1, TimeUnit.SECONDS);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
				
		
		//Runtime.getRuntime().addShutdownHook(t);
		
	}

}
