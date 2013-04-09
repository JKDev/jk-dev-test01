package test.thread;

import com.jk.task.IAction;

//
public class UserAction implements IAction<TItem, String>{

	public UserAction(){
		
	}
	
	@Override
	public boolean execute(TItem item) {
	
		if( "STOP".equals(item.getCmd()) ){
			long nId = Thread.currentThread().getId();
			System.out.println( " STOP " + " thread-" + nId  );
			return true;
		}
		// doAction
		
		System.out.println( " ##### cmd=" + item.getCmd() );
		
		return false;
	}

	@Override
	public String doEnd() {
		
		long nId = Thread.currentThread().getId();
		String result = "[thread-"+nId+"] end";
		return result;
	}

	@Override
	public TItem addCommand() {
		
		return null;
	}

}
