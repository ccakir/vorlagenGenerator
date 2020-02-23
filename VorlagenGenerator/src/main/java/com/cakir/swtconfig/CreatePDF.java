package com.cakir.swtconfig;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.cakir.model.Zettel;
import com.cakir.service.ZettelServiceImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	
	
	
	private final String filePath = new File(
			getClass().getClassLoader().getResource("Vorschau.pdf").getFile()
		).getAbsolutePath();
	
	ZettelServiceImpl zettelService = new ZettelServiceImpl();

	public void pdfA4x4(long id) {

		Zettel zettel = zettelService.findZettelById(id);

		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Font bigfont = new Font(FontFamily.HELVETICA, 15);
		Font boldfont = new Font(FontFamily.HELVETICA, 12);
		document.setMargins(10, 10, 10, 10);

		document.open();

		for (int tableRow = 0; tableRow < 4; tableRow++) {
			PdfPTable table = new PdfPTable(7);

			try {
				table.setTotalWidth(new float[] { 50, 80, 150, 120, 40, 50, 70 });
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			table.setLockedWidth(true);

			// first row

			String zettelTitle = zettel.getType();
			PdfPCell cell = new PdfPCell(new Phrase(zettelTitle, bigfont));

			cell.setFixedHeight(30);

			cell.setBorder(Rectangle.OUT_BOTTOM);
			cell.setBorder(Rectangle.OUT_RIGHT);
			cell.setBorder(Rectangle.OUT_LEFT);
			cell.setBorder(Rectangle.OUT_TOP);

			cell.setColspan(7);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell);

			// zweite row
			// erste und zweite cell
			cell = new PdfPCell(new Phrase("Teilname" + "\n\n" + zettel.getTeil().getTeilname(), boldfont));

			cell.setFixedHeight(42);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// dritte und vierte cells

			cell = new PdfPCell(new Phrase("Teilnummer" + "\n\n" + zettel.getTeil().getTeilenummer(), boldfont));
			cell.setColspan(3);
			cell.setFixedHeight(42);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// fünfte und sechste und siebente cell

			cell = new PdfPCell(new Phrase("Stück" + "\n\n" + zettel.getQuantity(), boldfont));
			cell.setFixedHeight(42);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// dritte row
			// erste und zweite cell
			cell = new PdfPCell(new Phrase("Datum" + "\n\n" + zettel.getDatum(), boldfont));

			cell.setFixedHeight(42);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// dritte cells
			String schicht = "";
			if(!zettel.getSchicht().equals("Leer")) {schicht = zettel.getSchicht();}
			cell = new PdfPCell(new Phrase("Schicht" + "\n\n" + schicht, boldfont));
			cell.setFixedHeight(42);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// vierte cells
			String abteilung = "";
			if(!zettel.getAbteilung().equals("Leer")) {abteilung = zettel.getAbteilung();}
			cell = new PdfPCell(new Phrase("Abteilung" + "\n\n" + abteilung, boldfont));
			cell.setFixedHeight(42);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// fünfte und sechste und siebente cell

			cell = new PdfPCell(new Phrase("Für Maschiene/Operation" + "\n\n" + zettel.getMaschiene(), boldfont));
			cell.setFixedHeight(42);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// vierte row
			// alle cells
			cell = new PdfPCell(new Phrase("Grund / Ursache" + "\n\n" + zettel.getGrund(), boldfont));

			cell.setFixedHeight(45);
			cell.setColspan(7);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// fünfte row
			// 1-4 cells
			cell = new PdfPCell(new Phrase(
					"Name/Telefonnummer" + "\n\n" + zettel.getMitarbeiter().getVorname() + " "
							+ zettel.getMitarbeiter().getNachname() + " / " + zettel.getMitarbeiter().getTel(),
					boldfont));

			cell.setFixedHeight(45);
			cell.setColspan(3);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// 5-7 cells
			cell = new PdfPCell(new Phrase("Bemerkung" + "\n\n" + zettel.getBemerkung(), boldfont));

			cell.setFixedHeight(45);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			try {
				document.add(table);

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		document.close();
		File file = new File(filePath);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pdfA4x1(long id) {
		
	
		
	


		Zettel zettel = zettelService.findZettelById(id);
		
		Document document = new Document(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Font bigfont = new Font(FontFamily.HELVETICA, 25, Font.BOLD);
		Font boldfont = new Font(FontFamily.HELVETICA, 12);
		document.setMargins(10, 10, 10, 10);
		
		document.open();
		
		PdfPTable table = new PdfPTable(7);

        try {
			table.setTotalWidth(new float[]{ 75,105, 175, 145, 65,75,95 });
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        table.setLockedWidth(true);

        

        // first row
        String zettelTitle = zettel.getType();
        PdfPCell cell = new PdfPCell(new Phrase(zettelTitle, bigfont));
        

        cell.setFixedHeight(100);

        cell.setBorder(Rectangle.OUT_BOTTOM);
        cell.setBorder(Rectangle.OUT_RIGHT);
        cell.setBorder(Rectangle.OUT_LEFT);
        cell.setBorder(Rectangle.OUT_TOP);

        cell.setColspan(7);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell);

        // zweite row
        // erste und zweite cell
        cell = new PdfPCell(new Phrase("Teilname"+"\n\n\n"+zettel.getTeil().getTeilname(), boldfont));
        
        cell.setFixedHeight(84);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        //dritte und vierte cells
        
        cell = new PdfPCell(new Phrase("Teilnummer"+"\n\n\n"+zettel.getTeil().getTeilenummer(), boldfont));
        cell.setColspan(3);
        cell.setFixedHeight(84);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        // fünfte und sechste und siebente cell
        
        cell = new PdfPCell(new Phrase("Stück"+"\n\n\n"+zettel.getQuantity(), boldfont));
        cell.setFixedHeight(84);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
    	// dritte row
        // erste und zweite cell
        cell = new PdfPCell(new Phrase("Datum"+"\n\n\n"+zettel.getDatum(), boldfont));
        
        cell.setFixedHeight(84);
        cell.setColspan(2);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        //dritte cells
        String schicht = "";
		if(!zettel.getSchicht().equals("Leer")) {schicht = zettel.getSchicht();}
        cell = new PdfPCell(new Phrase("Schicht"+"\n\n\n"+schicht, boldfont));
        cell.setFixedHeight(84);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        //vierte cells
        String abteilung = "";
		if(!zettel.getAbteilung().equals("Leer")) {abteilung = zettel.getAbteilung();}
        cell = new PdfPCell(new Phrase("Abteilung"+"\n\n\n"+abteilung, boldfont));
        cell.setFixedHeight(84);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        // fünfte und sechste und siebente cell
        
        cell = new PdfPCell(new Phrase("Für Maschiene/Operation"+"\n\n\n"+zettel.getMaschiene(), boldfont));
        cell.setFixedHeight(84);
        cell.setColspan(4);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        // vierte row
        // alle cells
        cell = new PdfPCell(new Phrase("Grund / Ursache"+"\n\n\n"+zettel.getGrund(), boldfont));
        
        cell.setFixedHeight(100);
        cell.setColspan(7);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        // fünfte row
        // 1-4 cells
        cell = new PdfPCell(new Phrase("Name"+"\n\n\n"+zettel.getMitarbeiter().getVorname()+" "+zettel.getMitarbeiter().getNachname(), boldfont));
        
        cell.setFixedHeight(90);
        cell.setColspan(3);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        //5-7 cells
        cell = new PdfPCell(new Phrase("Telefonnummer"+"\n\n\n"+zettel.getMitarbeiter().getTel(), boldfont));
        
        cell.setFixedHeight(90);
        cell.setColspan(4);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        // 6. row
        // alle cells
        cell = new PdfPCell(new Phrase("Bemerkung"+"\n\n\n"+zettel.getBemerkung(), boldfont));
        
        cell.setFixedHeight(84);
        cell.setColspan(7);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
        
        document.close();
		File file = new File(filePath);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}		

}
