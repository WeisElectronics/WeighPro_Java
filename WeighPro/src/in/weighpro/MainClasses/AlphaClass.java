/* This class is the first class
*to be executed 
*Contains the main method

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.MainClasses;

import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

//This class contains the main method which is the first method which is executed
public class AlphaClass {

	//The static method-main method which is the first piece of code to be executed
	public static void main(String[] args) throws SocketException, SQLException {
		new ResourceInitializer();//This initializer basically initializes all the resources needed by this program
					//Like the database initialization
					//and the font initialization
	//	VerificationHandler vh = new VerificationHandler(); //Has to be completed at a later stage 
								//for the sake of software control
		
		
		//if(vh.isValid()){//If it passes the verification test then proceed
	
	//All the swing functionality is generally called within this method which ensures that the GUI
	//is performed on a thread other than the main thread
		try { 
			SwingUtilities.invokeAndWait(new Runnable() {//Blocking thread which waits till all the 
								//swing functionality has been executed
								//Has to be implemented as a seperate runnable object
				
		//This method is the execution part of the thread
				@Override
				public void run() {
					new MainFrame();   //MainFrame is the frame which contains all the smaller components
						//See MainFrame class in this package
				}

			});
			//The swing initialization may at times throw an Exception which is caught here
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//}
}
