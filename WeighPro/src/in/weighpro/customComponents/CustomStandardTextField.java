/* This class provides the pluggable text field component
 *  used in the various panels
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CustomStandardTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444138463531116432L;

	//----Constructor to initialize the component----------------
	public CustomStandardTextField(int size) {
		super(size);//Call to the constructor of the JTextField 
		setBorder(new LineBorder(new Color(255, 255, 255, 255), 1));//White border of thickness 1
		setForeground(new Color(255, 255, 255, 240));//white colored text
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));//Setting the font of the text box
		setCaretColor(new Color(255, 255, 255));//Setting the color of the cursor
		setHorizontalAlignment(JTextField.CENTER);//center alignment for the text field
		setOpaque(false);//transparent
		
		//Adding a focus listener 
		//When an non allowed value is entered the text field border turns red
		//When such a box gains focus on a click it's border turns white 
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				setBorder(new LineBorder(new Color(255, 255, 255, 255), 1));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		
	}

	
	//-----constructor to initialize a text field with an initial value
	//used for muted text fields 
	//See custom muted text box field for an example of use
	public CustomStandardTextField(String text) {
		super(text);//Call to the constructor of the JTextField class
		setBorder(new LineBorder(new Color(255, 255, 255, 255), 1));//white border of thickness 1
		setForeground(new Color(255, 255, 255, 240));//white colored text
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));//setting the font
		setCaretColor(new Color(255, 255, 255));//cursor color to white
		setHorizontalAlignment(JTextField.CENTER);//center alignment for the text field
		setOpaque(false);//transparent
		
	}

}