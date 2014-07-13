/* This class provides the 
 * weight indicator label used in both
 * first screen and second screen
 * 
 * Author - Weis Electronics Pvt. Ltd.
 * Contribution by emdroidery
 * */

package in.weighpro.commonComponents;

import java.io.IOException;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.WeightLabel;

import javax.swing.JPanel;

public class WeightPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1404125779943653940L;

	private WeightLabel weight; // The label which shows the actual weight
	public Thread thread; // The weight updating thread that gets the weight
							// from the serial port
	private boolean isRunning = false;
	// -----Constructor to initialize all the variables and start the
	// thread-----------------------
	public WeightPanel() {
		setLayout(null); // Custom placement of components
		setOpaque(false); // Transparent background
		weight = new WeightLabel("00000"); // Initializing the label which shows
											// the weight
		weight.setBounds(0, 0, 200, 40); // Setting the bounds of the weight
											// label
		add(weight); // Adding the label to the panel
		thread = new Thread(this, "weight updating thread");// Initializing the
															// weight updating
															// thread
		
		thread.start(); // Starting the weight updating thread

	}

	// -----This method implements the code that the thread must perform

	@Override
	public void run() {
		isRunning = true;
		try {
		while (isRunning) {
			
				weight.setText(MainFrame.settingsPanel.getData());// Get weight
																	// from the
																	// synchronized
																	// method
																	// This
																	// method is
																	// close to
																	// a token
																	// system
																	// for
																	// threads
																	// to obtain
																	// weight
			
		}
		MainFrame.settingsPanel.getData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------Method to kill the unused
	// thread----------------------------------------
	//@SuppressWarnings("deprecation")
	public void stopThread() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------Method to kill the thread if it is not before during the
	// garbage collection process--------
	
	// -----------Method to get the text shown by the weight
	// label------------------------
	public String getText() {
		return weight.getText().trim();
	}
	
	public boolean isRunning(){
		return this.isRunning;
	}

}
