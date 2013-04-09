package com.jk.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import test.thread.TItem;


public class CallableMaker<C, R> {

	private BlockingQueue<C> queue = null;
	private int cap = 0;
	
	public CallableMaker(int size){
		cap = size;
		onInit();
	}

	private void onInit() {
		queue = new ArrayBlockingQueue<C>(cap);
	}
	

	public void add(C tmp) {
		queue.add(tmp);
	}
	
	public void dispose(){
		queue.clear();
	}
	
	
	//
	public Callable<R> newPrdCallable(final IAction<C, R> act){
		return new Callable<R>(){
			
			private boolean bFlag = true;
			private final IAction callAct = act;
			
			@Override
			public R call() throws Exception {
				
				R endMessage = null;
				C item = null;
				while(bFlag){
					item = act.addCommand(); 
					if( item != null ){
						queue.add(item);
					}else{
						bFlag = false;
					}
				}
				
				System.out.println( " roop out (" + Thread.currentThread().getId() + ")" );
				endMessage = act.doEnd();
				return endMessage;
			}
			
		};
	}
	
	
	//net 통신으로 처리해야 한다. 음.. 
	public Callable<R> newCallable( final IAction<C, R> act ){
		
		return new Callable<R>(){
			
			private boolean bFlag = true;
			private final IAction callAct = act;
			
			@Override
			public R call() throws Exception {
				System.out.println( " start thread-(" + Thread.currentThread().getId() + ")" );
				
				R endMessage = null;
				C item = null;
				while(bFlag){
					//
					item = queue.take();
					if( act.execute(item) ){
						queue.add(item);
						bFlag = false;
					}
				}
				System.out.println( " roop out (" + Thread.currentThread().getId() + ")" );
				
				endMessage = act.doEnd();
				return endMessage;
			}
			
		};
	}
	
}
