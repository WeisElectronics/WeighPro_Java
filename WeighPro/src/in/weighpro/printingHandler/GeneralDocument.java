package in.weighpro.printingHandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class GeneralDocument implements Printable {

	private String[][] values;

	public GeneralDocument(String[][] values) {
		this.values = values;
	}

	public int print(Graphics g, PageFormat pageFormat, int page) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		g2d.setPaint(Color.black);

		g2d.setStroke(new BasicStroke(1));
		
		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		g2d.drawString("ID", 12, 26);
		g2d.drawString("Vehicle No.", 55, 26);
		g2d.drawString("Customer", 160, 26);
		g2d.drawString("Material", 240, 26);
		g2d.drawString("Amount", 305, 26);
		g2d.drawString("Tare", 370, 26);
		g2d.drawString("Gross", 415, 26);
		g2d.drawString("Net", 470, 26);
		g2d.drawString("Tare Date", 540, 26);
		g2d.drawString("Gross Date", 675, 26);

		g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

		if (values.length == 28) {
			g2d.drawLine(39, 11, 39, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(145, 11, 145, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(235, 11, 235, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(300, 11, 300, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(360, 11, 360, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(410, 11, 410, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(460, 11, 460, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(510, 11, 510, (int) pageFormat.getImageableHeight()-10);
			g2d.drawLine(645, 11, 645, (int) pageFormat.getImageableHeight()-10);
		} else {
			g2d.drawLine(39, 11, 39, (values.length - 1) * 20 + 52);
			g2d.drawLine(145, 11, 145, (values.length - 1) * 20 + 52);
			g2d.drawLine(235, 11, 235, (values.length - 1) * 20 + 52);
			g2d.drawLine(300, 11, 300, (values.length - 1) * 20 + 52);
			g2d.drawLine(360, 11, 360, (values.length - 1) * 20 + 52);
			g2d.drawLine(410, 11, 410, (values.length - 1) * 20 + 52);
			g2d.drawLine(460, 11, 460, (values.length - 1) * 20 + 52);
			g2d.drawLine(510, 11, 510, (values.length - 1) * 20 + 52);
			g2d.drawLine(645, 11, 645, (values.length - 1) * 20 + 52);

		}

		FontMetrics fm = g2d.getFontMetrics();
		for (int i = 0; i < values.length; i++) {
			g2d.drawString(values[i][0], 30 - fm.stringWidth(values[i][0]),
					49 + i * 20);
			g2d.drawString(values[i][1], 45, 49 + i * 20);
			g2d.drawString(values[i][2], 150, 49 + i * 20);
			g2d.drawString(values[i][3], 240, 49 + i * 20);
			g2d.drawString(values[i][4], 350 - fm.stringWidth(values[i][4]),
					49 + i * 20);
			g2d.drawString(values[i][5], 405 - fm.stringWidth(values[i][5]),
					49 + i * 20);
			g2d.drawString(values[i][6], 455 - fm.stringWidth(values[i][6]),
					49 + i * 20);
			g2d.drawString(values[i][7], 505 - fm.stringWidth(values[i][7]),
					49 + i * 20);
			g2d.drawString(values[i][8], 515, 49 + i * 20);
			g2d.drawString(values[i][9], 650, 49 + i * 20);
			g2d.drawLine(0, 52 + i * 20, (int) pageFormat.getImageableWidth(),
					52 + i * 20);
		}

		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(0, 32, (int) pageFormat.getImageableWidth(), 32);
		g2d.drawLine(0, 11, (int) pageFormat.getImageableWidth(), 11);

		return (PAGE_EXISTS);

	}
}
