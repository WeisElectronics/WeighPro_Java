/* This class performs the
 * comparison of the address
 * with the address stored 
 * in the database
 * 
* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.verificationHandler;

import java.net.SocketException;
import java.rmi.dgc.VMID;
import java.sql.SQLException;

public class VerificationHandler {

	private boolean valid = false;

	//constructor to initialize the database connection and perform the verification
	public VerificationHandler() throws SocketException {//may throw a SocketException because of getting the address
														//of the network card

		VMID vim = new VMID();
		String address = vim.toString();

		//this block gets the unique verification id stored in the database and compares it with the network card address
		try {
			UniqueIDVerifier mv = new UniqueIDVerifier();//creating an instance of the unique ID verifier class
			if (mv.getUniqueID().equals(address)) {//if address stored in the database is equal to the obtained address
				valid = true;//designed to run on this system only
			} else {
				System.out.println("This is not designed for your system");//if address does not match
				valid = false;//then this program has not been designed for this sytem
			}

		} catch (SQLException e1) {//this exception is thrown when the verification table does not exist in the database
			try {
				new VerifierCreation(address);//create a new verifier creation class instance
				valid = true;//designed for this system
			} catch (SQLException e) {//this exception is thrown when database connectivity might be a problem
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	//method to return the compatibility of the current device
	public boolean isValid(){
		return this.valid;
	}
}
