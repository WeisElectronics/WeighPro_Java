package in.weighpro.verificationHandler;

import java.net.SocketException;
import java.rmi.dgc.VMID;
import java.sql.SQLException;

public class VerificationHandler {

	private boolean valid = false;

	public VerificationHandler() throws SocketException {

		VMID vim = new VMID();
		String address = vim.toString();

		try {
			UniqueIDVerifier mv = new UniqueIDVerifier();
			if (mv.getUniqueID().equals(address)) {
				valid = true;
			} else {
				System.out.println("This is not designed for your system");
				valid = false;
			}

		} catch (SQLException e1) {
			try {
				new VerifierCreation(address);
				valid = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public boolean isValid(){
		return this.valid;
	}
}
