package com.jk.task;

public interface IAction<C, R> {
	public C addCommand();				// add
	public boolean execute(C item);		// put
	public R doEnd();
	
	
}
