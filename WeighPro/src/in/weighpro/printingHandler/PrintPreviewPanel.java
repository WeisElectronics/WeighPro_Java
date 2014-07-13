package in.weighpro.printingHandler;

import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PrintPreviewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6037472813321106471L;

	public PrintPreviewPanel() {
		setLayout(null);
		setOpaque(false);
		setBorder(new LineBorder(Color.white, 1));
		setBounds(500, 85, 345, 435);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.drawRect(8, 8, 327, 134);
		g2.drawRect(8, 150, 327, 134);
		g2.drawRect(8, 292, 327, 134);
		CurrentWallpaperRetriever cwr;
		try {
			cwr = new CurrentWallpaperRetriever();
		
		for (int i = 0; i <= 284; i += 142) {
			g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
			FontMetrics fm = g2.getFontMetrics();
			int width = fm.stringWidth(cwr.getCompany());
			g2.drawString(cwr.getCompany(), (327-width)/2, 27 + i);
			g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
			g2.drawString("Serial No.", 18, 43 + i);
			g2.drawString("Customer", 18, 56 + i);
			g2.drawString("Vehicle Number", 180, 43 + i);
			g2.drawString("Material", 18, 69 + i);
			g2.drawString("Tare", 18, 87 + i);
			g2.drawString("Gross", 18, 100 + i);
			g2.drawString("Net", 18, 113 + i);
			g2.drawString("Charges", 18, 126 + i);
			g2.drawString("Date", 120, 87 + i);
			g2.drawString("Date", 120, 100 + i);
			g2.drawString("Date", 120, 113 + i);
			g2.drawString("Time", 230, 87 + i);
			g2.drawString("Time", 230, 100 + i);
			g2.drawString("Time", 230, 113 + i);
			g2.drawString("Operator's Signature", 180, 135 + i);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
