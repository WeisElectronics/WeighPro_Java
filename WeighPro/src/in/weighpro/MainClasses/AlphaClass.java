package in.weighpro.MainClasses;

import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;


public class AlphaClass {

	public static void main(String[] args) throws SocketException, SQLException {
		new ResourceInitializer();
	//	VerificationHandler vh = new VerificationHandler();
		
		
		//if(vh.isValid()){
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					new MainFrame();
				}

			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//}
}
