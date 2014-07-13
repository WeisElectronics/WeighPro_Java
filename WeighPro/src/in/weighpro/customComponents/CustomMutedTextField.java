/* This class provides the pluggable muted text fields
 *  used in the second entry and the print panel
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */


package in.weighpro.customComponents;


//This is a direct child of the custom standard text field class(Look it up in the same package)
public class CustomMutedTextField extends CustomStandardTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444138463531116432L;

	//Constructor to make the text field muted (non editable)
	public CustomMutedTextField(String text) {
		super(text);
		setEditable(false);
	}

}