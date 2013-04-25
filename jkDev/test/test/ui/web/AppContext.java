package test.ui.web;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.gdip.Rect;
import org.eclipse.swt.widgets.Display;

public class AppContext {

	private Display display = null;
	private String title = "";	
	private Rectangle rect = null;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public AppContext(){
		onInit(); 
	}
	
	
	public AppContext(String title) {
		this.title = title;
		onInit();
	}
	private void onInit() {
		// TODO Auto-generated method stub
		display = new Display();
	}


	public Display getDisplay() {
		return display;
	}
	
	public void setBound(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		rect = new Rectangle(x, y, width, height);
	}
	
	public void dispose() {
		display.dispose();
	}
	public Rectangle getBounds() {
		if(rect == null){
			rect = new Rectangle(10, 10, 600, 600);
		}
		return rect;
	}
	

}
