/* This class initializes the
digital font which is used for 
displaying the weight field

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.customFonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;


//this class uses the digital font (present in the same package) as a resource to generate a Font object
public class DigitalFontGenerator {
	public static Font digitalFont;


//Constructor to get the stream from the resource and initialize a font object using that
	public DigitalFontGenerator() {
		if (digitalFont == null) {//Check to see if the font has been initialized before to avoid the processing
					//overhead
			InputStream is = getClass().getResourceAsStream("digital.ttf");//Get a stream to access the resource
			try {
				digitalFont = Font.createFont(Font.TRUETYPE_FONT, is);//Creating a font object from the stream
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			digitalFont = digitalFont.deriveFont(60f);//getting a font of the desired size 
		}
	}
}
