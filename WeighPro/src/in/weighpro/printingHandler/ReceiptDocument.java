package in.weighpro.printingHandler;

import in.weighpro.dataComponents.Entry;
import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.ParseException;

public class ReceiptDocument implements Printable{
	
	private String ID;
	private String vehicleNumber;
	private String customer;
	private String material;
	private String tareWeight;
	private String grossWeight;
	private String netWeight;
	private String charges;
	private String tareDate="";
	private String tareTime="";
	private String grossDate="";
	private String grossTime="";
	private String netDate="";
	private String netTime="";
	private String companyName="";
	
	public ReceiptDocument(Entry entry) throws ParseException{
		ID = Integer.toString(entry.getIndex());
		vehicleNumber = entry.getVehicleNumber();
		customer = entry.getCustomer();
		material = entry.getMaterial();
		tareWeight = entry.getTareWeight();
		grossWeight = entry.getGrossWeight();
		netWeight = entry.getNetWeight();
		charges = Integer.toString(entry.getAmount());
		if(!entry.getTareDate().equals("")){
		tareDate = entry.getTareDate().split(" ")[0];
		tareTime = entry.getTareDate().split(" ")[1];
		}
		if(!entry.getGrossDate().equals("")){
		grossDate = entry.getGrossDate().split(" ")[0];
		grossTime = entry.getGrossDate().split(" ")[1];
		}
		if(!entry.getNetDate().equals("")){
		netDate = entry.getNetDate().split(" ")[0];
		netTime = entry.getNetDate().split(" ")[1];
		}
		try {
			CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();
			companyName = cwr.getCompany();
		} catch (SQLException e) {
			
		}
		
		}
	
	
	
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		Graphics2D graphics = (Graphics2D) g;
		graphics.setPaint(Color.black);
		
		for(int i=37;i<=557;i+=260){
			graphics.setFont(new Font("Arial", Font.PLAIN, 20));
			FontMetrics fm = graphics.getFontMetrics();
			int width = fm.stringWidth(companyName);
			
			graphics.drawString(companyName,(int) ((pf.getImageableWidth()-width)/2),i);
			
			//graphics.drawString("Weis Electronics Pvt. Ltd.", 180, i);
			
			
			graphics.setFont(new Font("Arial", Font.PLAIN, 8));
			
			
			graphics.drawString(
					"# 1-7-283/A. 1st Floor, R.No. 113, Jaya Mansion, 126, S.D. Road, Secunderabad - 500 003",
					120, i+15);
			graphics.drawString("Phone : 040-66200357, E-mail : weiselec@gmail.com", 200,
					i+30);

		
			graphics.setFont(new Font("Arial", Font.PLAIN, 12));
		
			
			graphics.drawString("Serial No.", 40, i+55);
			graphics.drawString("Customer", 40, i+75);
			graphics.drawString("Material", 40, i+95);
			graphics.drawString(" : ", 110, i+55);
			graphics.drawString(" : ", 110, i+75);
			graphics.drawString(" : ", 110, i+95);

			graphics.drawString("Vehicle No.", 350, i+55);
			graphics.drawString(" : ", 430, i+55);

			graphics.drawString("Tare", 40, i+125);
			graphics.drawString("Gross", 40, i+145);
			graphics.drawString("Net", 40, i+165);
			graphics.drawString("Charges", 40, i+185);
			graphics.drawString(" : ", 110, i+125);
			graphics.drawString(" : ", 110, i+145);
			graphics.drawString(" : ", 110, i+165);
			graphics.drawString(" : ", 110, i+185);

			graphics.drawString("Date : ", 240, i+125);
			graphics.drawString("Date : ", 240, i+145);
			graphics.drawString("Date : ", 240, i+165);

			graphics.drawString("Time : ", 398, i+125);
			graphics.drawString("Time : ", 398, i+145);
			graphics.drawString("Time : ", 398, i+165);

			graphics.drawString("Operator's Signature", 350, i+215);

		
			graphics.setFont(new Font("Arial", Font.BOLD, 12));
			graphics.drawString(ID, 130, i+55);
			graphics.drawString(customer, 130, i+75);
			graphics.drawString(material, 130, i+95);
			graphics.drawString(vehicleNumber, 450, i+55);
			graphics.drawString(tareWeight, 130, i+125);
			graphics.drawString(grossWeight, 130, i+145);
			graphics.drawString(netWeight, 130, i+165);
			graphics.drawString(charges, 130, i+185);
			graphics.drawString(tareDate, 290, i+125);
			graphics.drawString(grossDate, 290, i+145);
			graphics.drawString(netDate, 290, i+165);
			graphics.drawString(tareTime, 448, i+125);
			graphics.drawString(grossTime, 448, i+145);
			graphics.drawString(netTime, 448, i+165);

			graphics.drawRect(20, i-25, (int) (pf.getPaper().getWidth() - 40), 250);
			
		
		
		}
		
		return PAGE_EXISTS;
	}
}
