package in.weighpro.panels;

import in.weighpro.customComponents.CompanyLabel;
import in.weighpro.customComponents.DateDayLabel;
import in.weighpro.customComponents.TimeLabel;
import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TimePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2683615964049326092L;
	private TimeLabel time;
	private DateDayLabel day;
	private DateDayLabel date;
	private CompanyLabel company;
	private Calendar calendar;
	private Thread thread;

	public TimePanel() throws SQLException {

		setLayout(null);
		setOpaque(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setBounds(PanelBounds.timePanelXBound, PanelBounds.timePanelYBound,
				PanelDimensions.timePanelWidth, PanelDimensions.timePanelHeight);
		setVisible(true);

		time = new TimeLabel();
		time.setBounds(530, 0, 219, 90);
		add(time);

		date = new DateDayLabel();
		date.setBounds(720, 45, 100, 30);
		add(date);

		day = new DateDayLabel();
		day.setBounds(720, 15, 100, 35);
		add(day);

		CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();
		company = new CompanyLabel(cwr.getCompany());
		company.setBounds(50, 0, 350, 90);
		add(company);

		thread = new Thread(this, "Time Thread");
		updateTime();
		thread.start();
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(new Color(255, 255, 255, 50));
		g2.drawLine(459, 15, 459, 75);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	}

	void updateTime() {
		time.setText(setTime());
		day.setText(setDay());
		date.setText(setDate());
	}

	@Override
	public void run() {
		while (true) {
			updateTime();
		}
	}

	String setTime() {
		calendar = Calendar.getInstance();
		Formatter fmt = new Formatter();
		fmt.format("%tI:%<tM", calendar);
		String str = fmt.toString();
		fmt.close();
		return str;
	}

	String setDay() {
		calendar = Calendar.getInstance();
		Formatter fmt = new Formatter();
		fmt.format("%tA", calendar);
		String str = fmt.toString();
		fmt.close();
		return str;
	}

	String setDate() {
		calendar = Calendar.getInstance();
		Formatter fmt = new Formatter();
		fmt.format("%td %<tb %<tY", calendar);
		String str = fmt.toString();
		fmt.close();
		return str;
	}
	
	public void setCompanyName(String name){
		this.company.setText(name);
	}
	
	

}
