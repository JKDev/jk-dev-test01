package test.ui.web;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SwtBrowser implements Callable<String>{

	public SwtBrowser(){
		launcher();
	}

	//
	private void launcher() {
		
		//Callable<String> exe_ = new Callable<String>(this);
		FutureTask<String> future = new FutureTask<String>(this);
		//Thread t = new Thread( future, "swtBrowser-run"  );
		
		final ExecutorService exService = Executors.newSingleThreadExecutor();
		final Future<String> callFuture = exService.submit(this);
		
		try {
			
			String message = callFuture.get();
			System.out.println( "[message]" + message );
			
			boolean b = exService.isTerminated();
			System.out.println( " b:" + b );	//
			exService.awaitTermination(1, TimeUnit.SECONDS);
			exService.shutdownNow();
			
			b = exService.isTerminated();
			System.out.println( " b:" + b );	//
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		//String msg = t.start(); //
		
		
		
	}

	@Override
	public String call() throws Exception {
		String message = "App destory";
		
		boolean b = true;
		int count = 0;
		int millis = 10;
		
		// xml 설정값
		final AppContext context = new AppContext("browser");
		context.setBound(20, 20, 800, 800);

		//
		
		App app = new App( context );
		app.open();
		
		context.dispose();
		
		return message;
	}
	
}
