package core;

import java.awt.EventQueue;

import view.Window;

/**
 * @author SAGA LLC
 */

public class SudokuMain {

	public static void main(String[] args){
	    EventQueue.invokeLater(new Runnable(){
	          public void run(){    
	        	Window window = new Window();
	            window.setVisible(true);
	         }
	     });
	}
}
