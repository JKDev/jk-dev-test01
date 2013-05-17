package test.ui.web;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class App {

	Display display = null;
	Shell shell = null;
	
	private AppContext context = null;
	
	public App( AppContext context ){
		this.context = context;
		onInit();
	}
	
	private void onInit() {
		// TODO Auto-generated method stub
		shell = new Shell( context.getDisplay() );
		shell.setText(context.getTitle());
		
		
		shell.setBounds(context.getBounds());
		
		// pattern
		// hard coding
		shell.setLayout(new FillLayout());
		
		Browser b = new Browser(shell, SWT.BORDER);
		// String url = "http://boongtakkuma.tumblr.com/post/48775188893";
		String url = "http://www.naver.com";
		
		b.setUrl(url);
		
		b.addLocationListener(new LocationListener() {
			
			@Override
			public void changing(LocationEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("[changing]" +  event );
			}
			
			@Override
			public void changed(LocationEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("[changed]" + event );
			}
		});
		
		
		// swt - browser
		b.addProgressListener(new ProgressListener() {
			
			@Override
			public void completed(ProgressEvent event) {
				// TODO Auto-generated method stub
				System.out.println( "[completed]" + event );
				Browser browser = (Browser)event.widget;
				String html = browser.getText();
				System.out.println( html ); // save File  , String을 temp파일로 저장함.
			}
			
			@Override
			public void changed(ProgressEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		//
		b.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Auto-generated method stub
				//System.out.println( "[keyReleased]" + event );
				Browser b = (Browser)event.widget;
				//System.out.println( "[keyReleased]" + b );
				if(b!=null){
					
				}
				
				// show
				if( event.character == 'l' ){
					Browser browser = (Browser)event.widget;
					String html = browser.getText();
					System.out.println( html ); // save File
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Auto-generated method stub
				//System.out.println( "[keyPressed]" + event );
				Browser b = (Browser)event.widget;
				//System.out.println( "[keyPressed]" + b );
				if(b!=null){
					
				}
			}
		});
		
		
		
		//
		
	}

	public void open(){
		
		shell.open();
		
		while(!shell.isDisposed()){
			if( !context.getDisplay().readAndDispatch() ){
				context.getDisplay().sleep();
			}
		}
		
		dispose();
	}

	private void dispose() {
		// TODO Auto-generated method stub
		shell.dispose();
	}
}
