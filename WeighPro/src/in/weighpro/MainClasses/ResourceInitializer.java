/* This class initializes the
* database connections and the
* fonts which will be required in
* the application

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.MainClasses;

import in.weighpro.customFonts.DigitalFontGenerator;
import in.weighpro.customFonts.MenuFontGenerator;

public class ResourceInitializer {
	
	//Constructor to initialize the connection and the fonts
	public ResourceInitializer() {
		new MenuFontGenerator();//Class that generates the font used in the menu buttons
		new DigitalFontGenerator();//Class that generates the font used by the weight field
	}
}
