/* This class initializes the
* the menu font which is used 
* in the menu buttons

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.customFonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;


//this class generates a font object from copperplate Gothic font(present in the same package) using an input stream
public class MenuFontGenerator {
	public static Font menuPanelFont;

//Constructor to initialize the font object used in the menu buttons
	public MenuFontGenerator() {
		if (menuPanelFont == null) {//Check to see if the font object has been initialized before
					//Avoiding unnecessary processing overhead
			InputStream is = getClass().getResourceAsStream(
					"copperPlateGothic.ttf");//Getting an input stream from the font resource present
								//in the same package
			try {
				menuPanelFont = Font.createFont(Font.TRUETYPE_FONT, is);//Initializing a font object from the
											//input stream
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuPanelFont = menuPanelFont.deriveFont(19f);//Getting a font of the required size 
		}
	}
}
