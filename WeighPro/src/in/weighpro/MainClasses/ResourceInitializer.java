package in.weighpro.MainClasses;

import in.weighpro.customFonts.DigitalFontGenerator;
import in.weighpro.customFonts.MenuFontGenerator;

public class ResourceInitializer {
	public ResourceInitializer() {
		new MenuFontGenerator();
		new DigitalFontGenerator();
	}
}