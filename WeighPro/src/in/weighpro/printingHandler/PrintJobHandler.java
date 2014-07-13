package in.weighpro.printingHandler;

import in.weighpro.dataComponents.Entry;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.util.Arrays;

public class PrintJobHandler {
	public PrintJobHandler(String[][] values,String entryDate,String exitDate,String customer,String material) {
		PrinterJob printJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		PageFormat documentPageFormat = new PageFormat();
		documentPageFormat.setOrientation(PageFormat.LANDSCAPE);
		Paper paper = new Paper();
		double margin = 5;
		paper.setImageableArea(margin, margin, paper.getWidth() - 10,
				paper.getHeight() - 10);

		documentPageFormat.setPaper(paper);
		if (values.length > 25) {
			book.append(new FirstPage(Arrays.copyOfRange(values, 0, 25),entryDate,exitDate,customer,material),
					documentPageFormat);
			values = Arrays.copyOfRange(values, 25, values.length);
			int i;
			String[][] truncValues;
			for (i = 0; i < values.length / 28; i++) {
				truncValues = Arrays.copyOfRange(values, (i * 28), (i + 1) * 28);
				book.append(new GeneralDocument(truncValues),
						documentPageFormat);
			}
			truncValues = Arrays.copyOfRange(values, i * 28, values.length);
			book.append(new GeneralDocument(truncValues), documentPageFormat);
		} else {
			book.append(
					new FirstPage(Arrays.copyOfRange(values, 0, values.length),entryDate,exitDate,customer,material),
					documentPageFormat);

		}
		printJob.setPageable(book);
		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (Exception PrintException) {
				PrintException.printStackTrace();
			}
		}
	}
	
	public PrintJobHandler(Entry entry) throws ParseException{
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = pj.defaultPage();
		Paper paper = new Paper();
		double margin = 0;
		paper.setImageableArea(margin, margin, paper.getWidth(),
				paper.getHeight() + 20);

		pf.setPaper(paper);

		pj.setPrintable(new ReceiptDocument(entry), pf);
		if (pj.printDialog()) {
			try {
				pj.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		}
	}

	}

