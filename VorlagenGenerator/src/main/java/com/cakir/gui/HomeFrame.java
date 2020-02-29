package com.cakir.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.cakir.connect.DatabaseConnection;
import com.cakir.model.Zettel;
import com.cakir.service.TeilServiceImpl;
import com.cakir.service.ZettelServiceImpl;
import com.cakir.swtconfig.CreatePDF;
import com.cakir.swtconfig.SwtComponentsSettings;
import com.cakir.swtconfig.TableConfig;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Connect extends Thread {
	public void run() {
		
DatabaseConnection connect = new DatabaseConnection();
		
		try {
			connect.verbindung();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}

public class HomeFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	

	ZettelServiceImpl zettelService = new ZettelServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();
	SwtComponentsSettings swSettings = new SwtComponentsSettings();

	private JPanel contentPane;
	private JTable tableOk, tableMA, tableNacharbeit, tableSuspekt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeFrame() {
		
		Connect connect = new Connect();
		connect.start();
		setForeground(Color.LIGHT_GRAY);
		setBounds(100, 100, 1200, 900);
		setResizable(false);
		setTitle("VORLAGEN GENERATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		tableOk = new JTable();
		tableOk.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOk.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Datum", "Grund", "Stück", "Teil", "Mitarbeiter", "Type", "" }));

		TableConfig tableConfig = new TableConfig();
		tableConfig.tableAnsicht(tableOk, 7);
		tableConfig.setJTableColumnsWidth(tableOk, 1175, 3, 10, 30, 7, 25, 10, 15, 0);

		tableMA = new JTable();
		tableMA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMA.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Datum", "Grund", "Stück", "Teil", "Mitarbeiter", "Type", "" }));

		tableConfig.tableAnsicht(tableMA, 7);
		tableConfig.setJTableColumnsWidth(tableMA, 1175, 3, 10, 30, 7, 25, 10, 15, 0);

		tableNacharbeit = new JTable();
		tableNacharbeit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNacharbeit.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Datum", "Grund", "Stück", "Teil", "Mitarbeiter", "Type", "" }));

		tableConfig.tableAnsicht(tableNacharbeit, 7);
		tableConfig.setJTableColumnsWidth(tableNacharbeit, 1175, 3, 10, 30, 7, 25, 10, 15, 0);

		tableSuspekt = new JTable();
		tableSuspekt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSuspekt.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Datum", "Grund", "Stück", "Teil", "Mitarbeiter", "Type", "" }));

		tableConfig.tableAnsicht(tableSuspekt, 7);
		tableConfig.setJTableColumnsWidth(tableSuspekt, 1175, 3, 10, 30, 7, 25, 10, 15, 0);

		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 632, 94);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu",
				TitledBorder.LEFT, TitledBorder.TOP));
		contentPane.add(panel);

		JButton btnZettel = new JButton("ZETTEL");
		btnZettel.setBounds(10, 31, 142, 36);
		panel.add(btnZettel);
		swSettings.jButtonSettins(btnZettel);

		JButton btnLieferant = new JButton("LIEFERANTEN");
		btnLieferant.setBounds(162, 31, 142, 36);
		panel.add(btnLieferant);
		swSettings.jButtonSettins(btnLieferant);

		JButton btnTeil = new JButton("TEIL");
		btnTeil.setBounds(314, 31, 143, 36);
		panel.add(btnTeil);
		swSettings.jButtonSettins(btnTeil);

		JButton btnBetreuer = new JButton("BETREUER");
		btnBetreuer.setBounds(467, 31, 142, 36);
		panel.add(btnBetreuer);
		swSettings.jButtonSettins(btnBetreuer);

		btnBetreuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MitarbeiterFrame betreuerFrame = new MitarbeiterFrame();
				betreuerFrame.setLocationRelativeTo(null);
				betreuerFrame.setVisible(true);

			}
		});

		btnTeil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeilFrame tframe = new TeilFrame();
				tframe.setLocationRelativeTo(null);
				tframe.setVisible(true);
			}
		});

		btnLieferant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LieferantFrame lieferantFrame = new LieferantFrame();
				lieferantFrame.setLocationRelativeTo(null);
				lieferantFrame.setVisible(true);

			}
		});

		btnZettel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZettelFrame zFrame = new ZettelFrame();
				zFrame.setLocationRelativeTo(null);
				zFrame.setVisible(true);

			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1000, 32, 185, 94);
		panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Datenbankverbindung Status", TitledBorder.LEFT, TitledBorder.TOP));
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel();
		panel_1.add(lblNewLabel);
		//lblNewLabel.setIcon(new ImageIcon(iconPath));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(10, 222, 1175, 581);

		contentPane.add(tabbedPane);

		JPanel panel_Ok = new JPanel();
		panel_Ok.setBorder(null);
		tabbedPane.addTab("OK TEILE", null, panel_Ok, null);
		panel_Ok.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 1150, 589);
		scrollPane_1.setViewportView(tableOk);
		panel_Ok.add(scrollPane_1);
		getAlleZettel("OK", tableOk);
		tabbedPane.setBackgroundAt(0, new Color(0, 128, 0));

		JPanel panel_Ma = new JPanel();
		tabbedPane.addTab("MATERIALAUSSCHUSS", null, panel_Ma, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 0, 0));
		panel_Ma.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1150, 589);
		scrollPane.setViewportView(tableMA);
		getAlleZettel("MATERIALAUSSCHUSS", tableMA);
		panel_Ma.add(scrollPane);

		JPanel panel_Suspekt = new JPanel();
		tabbedPane.addTab("SUSPEKTE TEILE", null, panel_Suspekt, null);
		tabbedPane.setBackgroundAt(2, new Color(255, 255, 0));
		panel_Suspekt.setLayout(null);

		JScrollPane jScrollPane_Suspekt = new JScrollPane();
		jScrollPane_Suspekt.setBounds(10, 11, 1150, 589);
		jScrollPane_Suspekt.setViewportView(tableSuspekt);
		getAlleZettel("SUSPEKT", tableSuspekt);
		panel_Suspekt.add(jScrollPane_Suspekt);

		JPanel panel_Na = new JPanel();
		tabbedPane.addTab("NACHARBEIT", null, panel_Na, null);
		tabbedPane.setBackgroundAt(3, new Color(30, 144, 255));
		panel_Na.setLayout(null);

		JScrollPane jScrollPane_NA = new JScrollPane();
		jScrollPane_NA.setBounds(10, 11, 1150, 589);
		jScrollPane_NA.setViewportView(tableNacharbeit);
		getAlleZettel("NACHARBEIT", tableNacharbeit);
		panel_Na.add(jScrollPane_NA);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 814, 832, 46);
		panel_2.setLayout(null);
		contentPane.add(panel_2);

		JButton btnDelete = new JButton("LÖSCHEN");
		
		
		btnDelete.setBounds(10, 11, 155, 23);
		swSettings.jButtonSettins(btnDelete);
		panel_2.add(btnDelete);

		JButton btnUpdate = new JButton("UPDATE");

		btnUpdate.setBounds(186, 11, 155, 23);
		
		
		swSettings.jButtonSettins(btnUpdate);
		panel_2.add(btnUpdate);

		JButton btnDrucken = new JButton("PDF VORSCHAU");

		
		
		btnDrucken.setBounds(369, 11, 155, 23);
		swSettings.jButtonSettins(btnDrucken);
		panel_2.add(btnDrucken);

		JButton btnAktualisieren = new JButton("TABELLE AKTUALISIEREN");

		
		
		btnAktualisieren.setBounds(974, 213, 211, 23);
		swSettings.jButtonSettins(btnAktualisieren);
		contentPane.add(btnAktualisieren);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 137, 632, 63);
		panel_3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Zettel mit Farben", TitledBorder.LEFT, TitledBorder.TOP));
		
		contentPane.add(panel_3);
		
		
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("OK ZETTEL - GRÜN");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setBounds(10, 25, 118, 14);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblMaterialausschussRot = new JLabel("MATERIALAUSSCHUSS - ROT  ");
		lblMaterialausschussRot.setForeground(new Color(255, 0, 0));
		lblMaterialausschussRot.setBounds(159, 25, 170, 14);
		panel_3.add(lblMaterialausschussRot);
		
		JLabel lblSuspektGelb = new JLabel("SUSPEKT - GELB");
		lblSuspektGelb.setForeground(new Color(255, 255, 0));
		lblSuspektGelb.setBounds(364, 25, 118, 14);
		panel_3.add(lblSuspektGelb);
		
		JLabel lblNacharbeitBlau = new JLabel("NACHARBEIT - BLAU");
		lblNacharbeitBlau.setForeground(new Color(0, 0, 255));
		lblNacharbeitBlau.setBounds(492, 25, 130, 14);
		panel_3.add(lblNacharbeitBlau);

		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					getAlleZettel("OK", tableOk);
					break;

				case 1:
					getAlleZettel("MATERIALAUSSCHUSS", tableMA);
					break;

				case 2:
					getAlleZettel("SUSPEKT", tableSuspekt);
					break;

				case 3:
					getAlleZettel("NACHARBEIT", tableNacharbeit);
					break;

				default:
					break;
				}
			}
		});

		btnDrucken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable activeTable = getActiveTable(tabbedPane.getSelectedIndex());

				if (activeTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					long id = (long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 7);

					String[] zettelWahl = { "A4 (4 Zettel)", "A4 (1 Zettel)" };

					String result = (String) JOptionPane.showInputDialog(null, "Wählen Sie eine Vorlage aus.", "Zettel Type",
							JOptionPane.QUESTION_MESSAGE, null, zettelWahl, zettelWahl[0]);
					
					CreatePDF pdf = new CreatePDF();
					
					
					switch (result) {
					case "A4 (4 Zettel)":
						pdf.pdfA4x4(id);
						break;

					case "A4 (1 Zettel)":
						pdf.pdfA4x1(id);
						break;
					default:
						break;
					}
					
					
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable activeTable = getActiveTable(tabbedPane.getSelectedIndex());

				if (activeTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					Zettel updateZettel = zettelService
							.findZettelById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 7));
					ZettelFrame updateFrame = new ZettelFrame();
					updateFrame.updateFelder(updateZettel);
					updateFrame.setLocationRelativeTo(null);
					updateFrame.setVisible(true);
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable activeTable = getActiveTable(tabbedPane.getSelectedIndex());

				if (activeTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					long id = (long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 7);
					int result = JOptionPane.showConfirmDialog(null, "Möchten Sie die Daten löschen?");

					switch (result) {
					case 0:
						if (zettelService.deleteZettel(id)) {
							JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich gelöscht.");
						} else {
							JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten!", "Fehler",
									JOptionPane.ERROR_MESSAGE);
						}
						break;

					default:
						break;
					}
				}

			}

		});

	}

	
	private JTable getActiveTable(int selectedIndex) {

		switch (selectedIndex) {
		case 0:
			return tableOk;
		case 1:
			return tableMA;
		case 2:
			return tableSuspekt;
		case 3:
			return tableNacharbeit;

		default:
			break;
		}
		return null;

	}

	private void getAlleZettel(String type, JTable table) {

		int no = 1;
		DefaultTableModel modelZettel = (DefaultTableModel) table.getModel();
		modelZettel.setRowCount(0);

		List<Zettel> listZettel = zettelService.alleZettel(type);

		if(listZettel != null) {
			for (Zettel zettel : listZettel) {

				modelZettel.addRow(new Object[] { no, zettel.getDatum(), zettel.getGrund(), zettel.getQuantity(),
						zettel.getTeil().getTeilname() + " - " + zettel.getTeil().getTeilenummer(),
						zettel.getMitarbeiter().getVorname() + " " + zettel.getMitarbeiter().getNachname(),
						zettel.getType(), zettel.getId() });
				no++;
			}
		}
		
	}
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
