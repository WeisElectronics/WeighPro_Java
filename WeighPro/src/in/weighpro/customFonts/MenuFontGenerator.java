package in.weighpro.customFonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class MenuFontGenerator {
	public static Font menuPanelFont;

	public MenuFontGenerator() {
		if (menuPanelFont == null) {
			InputStream is = getClass().getResourceAsStream(
					"copperPlateGothic.ttf");
			try {
				menuPanelFont = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuPanelFont = menuPanelFont.deriveFont(19f);
		}
	}
}
