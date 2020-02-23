package com.cakir.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cakir.model.Lieferant;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Teil;
import com.cakir.service.LieferantServiceImpl;
import com.cakir.service.MitarbeiterServiceImpl;
import com.cakir.service.TeilServiceImpl;
import com.cakir.swtconfig.Item;
import com.cakir.swtconfig.SwtComponentsSettings;
import com.cakir.swtconfig.TableConfig;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class TeilFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6451362754868198000L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldTeilname;
	private JTextField textFieldTeilenummer;
	private JTable tableTeil;
	private JComboBox<Item<Object>> comboBoxMitarbeiter, comboBoxLieferant;
	private JLabel lblNewLabel;

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	LieferantServiceImpl lieferantService = new LieferantServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();
	SwtComponentsSettings swSettings = new SwtComponentsSettings();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeilFrame frame = new TeilFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeilFrame() {
		setTitle("TEILE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 783, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(50, 37, 100, 14);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TEILNAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(50, 62, 100, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("TEILENUMMER");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(50, 87, 100, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("MITARBEITER");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(50, 112, 100, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("LIEFERANT");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(50, 137, 100, 14);
		contentPane.add(lblNewLabel_4);

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setEditable(false);
		textFieldId.setBounds(170, 35, 58, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setBorder(null);
		textFieldId.setVisible(false);

		textFieldTeilname = new JTextField();
		textFieldTeilname.setColumns(10);
		textFieldTeilname.setBorder(null);
		textFieldTeilname.setBounds(170, 60, 161, 20);
		contentPane.add(textFieldTeilname);

		textFieldTeilenummer = new JTextField();
		textFieldTeilenummer.setColumns(10);
		textFieldTeilenummer.setBorder(null);
		textFieldTeilenummer.setBounds(170, 85, 161, 20);
		contentPane.add(textFieldTeilenummer);

		comboBoxMitarbeiter = new JComboBox<Item<Object>>();
		comboBoxMitarbeiter.setBounds(170, 110, 161, 20);
		contentPane.add(comboBoxMitarbeiter);
		comboBoxMitarbeiterList();

		comboBoxLieferant = new JComboBox<Item<Object>>();
		comboBoxLieferant.setBounds(170, 135, 203, 20);
		contentPane.add(comboBoxLieferant);
		comboBoxLieferantList();

		JButton btnSpeichern = new JButton("SPEICHERN");

		
		btnSpeichern.setBounds(170, 168, 132, 23);
		swSettings.jButtonSettins(btnSpeichern);
		btnSpeichern.setIcon(new ImageIcon(new File(
				getClass().getClassLoader().getResource("icons/plus.png").getFile()
			).getAbsolutePath()));
		contentPane.add(btnSpeichern);

		JButton btnReset = new JButton("RESET");

		swSettings.jButtonSettins(btnReset);
		btnReset.setIcon(new ImageIcon(new File(
				getClass().getClassLoader().getResource("icons/zuruecksetzen.png").getFile()
			).getAbsolutePath()));
		btnReset.setBounds(332, 168, 89, 23);
		contentPane.add(btnReset);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 231, 747, 295);
		contentPane.add(scrollPane);

		tableTeil = new JTable();
		tableTeil.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Teilname", "Teilenummer", "Mitarbeiter", "Lieferant", "" }));

		TableConfig tableConfig = new TableConfig();
		tableConfig.setJTableColumnsWidth(tableTeil, 480, 5, 20, 20, 25, 30, 0);
		tableConfig.tableAnsicht(tableTeil, 5);

		scrollPane.setViewportView(tableTeil);
		getAlleTeile();

		JButton btnLschen = new JButton("LÖSCHEN");
		
		swSettings.jButtonSettins(btnLschen);
		btnLschen.setIcon(new ImageIcon(new File(
				getClass().getClassLoader().getResource("icons/behaelter.png").getFile()
			).getAbsolutePath()));
		btnLschen.setBounds(10, 537, 89, 23);
		contentPane.add(btnLschen);

		JButton btnUpdate = new JButton("UPDATE");

		swSettings.jButtonSettins(btnUpdate);
		btnUpdate.setIcon(new ImageIcon(new File(
				getClass().getClassLoader().getResource("icons/aktualisierung.png").getFile()
			).getAbsolutePath()));
		btnUpdate.setBounds(126, 537, 89, 23);
		contentPane.add(btnUpdate);

		
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tableTeil.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					long id = (long) tableTeil.getModel().getValueAt(tableTeil.getSelectedRow(), 5);
					
					int result =  JOptionPane.showConfirmDialog(null, "Möchten Sie die Daten löschen?");

					if(result == 0) {
						if(teilService.deleteTeil(id)) {
							JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich gelöscht.");
						} else {
							JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten!", "Fehler",
									JOptionPane.ERROR_MESSAGE);
						}
					} 
				getAlleTeile();}
			}
		});
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableTeil.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					Teil updateTeil = teilService
							.findTeilById((long) tableTeil.getModel().getValueAt(tableTeil.getSelectedRow(), 5));
					
					updateFelder(updateTeil);
				}
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLeereFelder();
			}
		});

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Item<?> itemMitarbeiter = (Item<?>) comboBoxMitarbeiter.getSelectedItem();
				Object mitarbeiter = itemMitarbeiter.getValue();

				Item<?> itemLieferant = (Item<?>) comboBoxLieferant.getSelectedItem();
				Object lieferant = itemLieferant.getValue();

				Teil teil = new Teil();
				teil.setTeilname(textFieldTeilname.getText());
				teil.setTeilenummer(textFieldTeilenummer.getText());
				teil.setLieferant((Lieferant) lieferant);
				teil.setMitarbeiter((Mitarbeiter) mitarbeiter);

				if (!textFieldId.getText().isEmpty()) {
					teil.setId(Long.parseLong(textFieldId.getText()));
					teilService.updateTeil(teil);
					
				} else {
					teilService.speichernTeil(teil);
				}

				getLeereFelder();
				felderHidden();
				getAlleTeile();
			}
		});

	}

	protected void updateFelder(Teil updateTeil) {
		
		lblNewLabel.setVisible(true);
		textFieldId.setVisible(true);
		
		textFieldId.setText(String.valueOf(updateTeil.getId()));
		textFieldTeilname.setText(updateTeil.getTeilname());
		textFieldTeilenummer.setText(updateTeil.getTeilenummer());
		
		comboBoxMitarbeiter.setSelectedItem(new Item<Object>(updateTeil.getMitarbeiter(),
				updateTeil.getMitarbeiter().getVorname()+" "+updateTeil.getMitarbeiter().getNachname()));
		
		comboBoxLieferant.addItem(new Item<Object>(updateTeil.getLieferant(),updateTeil.getLieferant().getName()));
		
	}

	protected void getAlleTeile() {

		DefaultTableModel modelTeil = (DefaultTableModel) tableTeil.getModel();
		int no = 1;

		modelTeil.setRowCount(0);
		List<Teil> listTeil = teilService.alleTeile();

		for (Teil teil : listTeil) {

			modelTeil.addRow(new Object[] { no, teil.getTeilname(), teil.getTeilenummer(),
					teil.getMitarbeiter().getVorname() + " " + teil.getMitarbeiter().getNachname(),
					teil.getLieferant().getName(), teil.getId() });
			no++;
		}
	}

	protected void felderHidden() {

		textFieldId.setVisible(false);
		lblNewLabel.setVisible(false);
		
		textFieldId.setText("");
	}

	protected void getLeereFelder() {

		textFieldTeilenummer.setText("");
		textFieldTeilname.setText("");
		comboBoxLieferant.setSelectedIndex(0);
		comboBoxMitarbeiter.setSelectedIndex(0);

	}

	private void comboBoxLieferantList() {

		List<Lieferant> lieferantList = lieferantService.alleLieferanten();

		for (Lieferant lieferant : lieferantList) {

			comboBoxLieferant.addItem(new Item<Object>(lieferant, lieferant.getName()));
		}

	}

	private void comboBoxMitarbeiterList() {
		List<Mitarbeiter> mitarbeiterList = mitarbeiterService.alleMitarbeiter();

		for (Mitarbeiter mitarbeiter : mitarbeiterList) {

			comboBoxMitarbeiter
					.addItem(new Item<Object>(mitarbeiter, mitarbeiter.getVorname() + " " + mitarbeiter.getNachname()));
		}
	}
}
