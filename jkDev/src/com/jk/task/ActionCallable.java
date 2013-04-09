package com.jk.task;

import java.util.concurrent.Callable;

public class ActionCallable implements Callable<String> {

	private boolean bIs = true;
	private String name = "";
	public ActionCallable( String name ){
		this.name = name;
	}
	
	//
	@Override
	public String call() throws Exception {
		long nId = Thread.currentThread().getId();
		String info = "Thread-" + nId;
		
		long count = 0;
		//
		try{
			while( bIs ){
				++count;
				
				Thread.currentThread().sleep(1);
				// doAction 
			}
		}catch(InterruptedException ie){
			
		}
		
		return info + " count:" + count + " name:" + name;
	}

}
