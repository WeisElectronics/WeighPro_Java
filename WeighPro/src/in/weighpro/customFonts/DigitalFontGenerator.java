package in.weighpro.customFonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class DigitalFontGenerator {
	public static Font digitalFont;

	public DigitalFontGenerator() {
		if (digitalFont == null) {
			InputStream is = getClass().getResourceAsStream("digital.ttf");
			try {
				digitalFont = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			digitalFont = digitalFont.deriveFont(60f);
		}
	}
}
