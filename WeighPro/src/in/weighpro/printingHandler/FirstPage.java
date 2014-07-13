package in.weighpro.printingHandler;

import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.sql.SQLException;

public class FirstPage implements Printable {

	private String[][] values;
	private String entryDate;
	private String exitDate;
	private String customer;
	private String material;

	public FirstPage(String[][] values, String entryDate, String exitDate,
			String customer, String material) {
		this.values = values;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.customer = customer;
		this.material = material;
	}

	public int print(Graphics g, PageFormat pageFormat, int page) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		g2d.setPaint(Color.black);

		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		try {
			CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();
			FontMetrics fm = g2d.getFontMetrics();
			int width = fm.stringWidth(cwr.getCompany());
			g2d.drawString(cwr.getCompany(),
					(int) ((pageFormat.getImageableWidth() - width) / 2), 35);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		g2d.drawString("From :", 30, 60);
		g2d.drawString(entryDate, 70, 60);

		g2d.drawString("To:", 220, 60);
		g2d.drawString(exitDate, 240, 60);

		g2d.drawString("Customer:", 400, 60);
		g2d.drawString(customer, 465, 60);

		g2d.drawString("Material:", 580, 60);
		g2d.drawString(material, 635, 60);

		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		g2d.drawString("ID", 12, 86);
		g2d.drawString("Vehicle No.", 55, 86);
		g2d.drawString("Customer", 160, 86);
		g2d.drawString("Material", 240, 86);
		g2d.drawString("Amount", 305, 86);
		g2d.drawString("Tare", 370, 86);
		g2d.drawString("Gross", 415, 86);
		g2d.drawString("Net", 470, 86);
		g2d.drawString("Tare Date", 540, 86);
		g2d.drawString("Gross Date", 675, 86);

		g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

		if (values.length == 25) {
			g2d.drawLine(39, 71, 39, (int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(145, 71, 145,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(235, 71, 235,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(300, 71, 300,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(360, 71, 360,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(410, 71, 410,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(460, 71, 460,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(510, 71, 510,
					(int) pageFormat.getImageableHeight() - 12);
			g2d.drawLine(645, 71, 645,
					(int) pageFormat.getImageableHeight() - 12);
			
		} else {
		g2d.drawLine(39, 71, 39, (values.length - 1) * 20 + 111);
			g2d.drawLine(145, 71, 145, (values.length - 1) * 20 + 111);
			g2d.drawLine(235, 71, 235, (values.length - 1) * 20 + 111);
			g2d.drawLine(300, 71, 300, (values.length - 1) * 20 + 111);
			g2d.drawLine(360, 71, 360, (values.length - 1) * 20 + 111);
			g2d.drawLine(410, 71, 410, (values.length - 1) * 20 + 111);
			g2d.drawLine(460, 71, 460, (values.length - 1) * 20 + 111);
			g2d.drawLine(510, 71, 510, (values.length - 1) * 20 + 111);
			g2d.drawLine(645, 71, 645, (values.length - 1) * 20 + 111);
			
		}

		FontMetrics fm = g2d.getFontMetrics();
		for (int i = 0; i < values.length; i++) {
			g2d.drawString(values[i][0], 30 - fm.stringWidth(values[i][0]),
					109 + i * 20);
			g2d.drawString(values[i][1], 45, 109 + i * 20);
			g2d.drawString(values[i][2], 150, 109 + i * 20);
			g2d.drawString(values[i][3], 240, 109 + i * 20);
			g2d.drawString(values[i][4], 350 - fm.stringWidth(values[i][4]),
					109 + i * 20);
			g2d.drawString(values[i][5], 405 - fm.stringWidth(values[i][5]),
					109 + i * 20);
			g2d.drawString(values[i][6], 455 - fm.stringWidth(values[i][6]),
					109 + i * 20);
			g2d.drawString(values[i][7], 505 - fm.stringWidth(values[i][7]),
					109 + i * 20);
			g2d.drawString(values[i][8], 515, 109 + i * 20);
			g2d.drawString(values[i][9], 650, 109 + i * 20);
			g2d.drawLine(0, 111 + i * 20, (int) pageFormat.getImageableWidth(),
					111 + i * 20);
		}

		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(0, 92, (int) pageFormat.getImageableWidth(), 92);
		g2d.drawLine(0, 71, (int) pageFormat.getImageableWidth(), 71);

		return (PAGE_EXISTS);

	}
}
