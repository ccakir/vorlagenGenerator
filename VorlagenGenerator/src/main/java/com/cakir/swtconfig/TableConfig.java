package com.cakir.swtconfig;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class TableConfig {
	
	public  void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
	        double... percentages) {
	    double total = 0;
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        TableColumn column = table.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)
	                (tablePreferredWidth * (percentages[i] / total)));
	    }
	}
	
	public void tableAnsicht(JTable table, int removeColumn) {
		
		table.setRowHeight(25);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.orange);
		header.setForeground(Color.white);
		header.setFont(new Font("Tahoma", Font.BOLD, 15));

		table.setIntercellSpacing(new Dimension(5, 10));
		table.removeColumn(table.getColumnModel().getColumn(removeColumn));
	}

}
