package com.cakir.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
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
import com.cakir.service.LieferantServiceImpl;
import com.cakir.service.MitarbeiterServiceImpl;
import com.cakir.swtconfig.Item;
import com.cakir.swtconfig.SwtComponentsSettings;
import com.cakir.swtconfig.TableConfig;
import com.cakir.validation.StringOperations;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class LieferantFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldName;
	private JTextField textFieldTelefon;
	private JTable tableLieferant;
	private JComboBox<Item<Object>> comboBoxMitarbeiter;
	private JLabel lblNewLabel_2;

	LieferantServiceImpl lieferantService = new LieferantServiceImpl();
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	StringOperations operation = new StringOperations();
	SwtComponentsSettings swSettings = new SwtComponentsSettings();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LieferantFrame frame = new LieferantFrame();
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
	@SuppressWarnings("serial")
	public LieferantFrame() {
		setBackground(Color.LIGHT_GRAY);
		setTitle("LIEFERANTEN");

		setBounds(100, 100, 761, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LIEFERANTENNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(48, 65, 127, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TELEFON");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(48, 90, 127, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(48, 40, 127, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);

		JLabel lblNewLabel_3 = new JLabel("MITARBEITER");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(48, 115, 127, 14);
		contentPane.add(lblNewLabel_3);

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setEditable(false);
		textFieldId.setBounds(195, 38, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setBorder(null);
		textFieldId.setVisible(false);

		textFieldName = new JTextField();
		textFieldName.setBounds(195, 63, 227, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setBorder(null);

		textFieldTelefon = new JTextField();
		textFieldTelefon.setBounds(195, 88, 227, 20);
		contentPane.add(textFieldTelefon);
		textFieldTelefon.setColumns(10);
		textFieldTelefon.setBorder(null);

		comboBoxMitarbeiter = new JComboBox<Item<Object>>();
		comboBoxMitarbeiter.setBounds(195, 113, 227, 20);
		contentPane.add(comboBoxMitarbeiter);
		comboBoxMitarbeiterList();

		JButton btnSpeichern = new JButton("SPEICHERN");

		swSettings.jButtonSettins(btnSpeichern);
		
		btnSpeichern.setBounds(195, 155, 89, 23);
		
		contentPane.add(btnSpeichern);

		JButton btnReset = new JButton("RESET");
		
		swSettings.jButtonSettins(btnReset);
		
		btnReset.setBounds(303, 155, 89, 23);
		contentPane.add(btnReset);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 725, 280);
		contentPane.add(scrollPane);

		tableLieferant = new JTable();
		tableLieferant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLieferant.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Lieferantenname", "Telefon", "Mitarbeiter", "" }) {
			boolean[] columnEditables = new boolean[] { false, true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "unused", "rawtypes" })
			private Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class,
					Object.class };
		});

		TableConfig tableConfig = new TableConfig();
		tableConfig.setJTableColumnsWidth(tableLieferant, 480, 5, 50, 20, 25, 0);
		tableConfig.tableAnsicht(tableLieferant, 4);
				
		getAlleLieferanten();
		scrollPane.setViewportView(tableLieferant);

		JButton btnLschen = new JButton("LÖSCHEN");

		swSettings.jButtonSettins(btnLschen);
		
		btnLschen.setBounds(10, 514, 89, 23);
		contentPane.add(btnLschen);

		JButton btnUpdate = new JButton("UPDATE");

		swSettings.jButtonSettins(btnUpdate);
		
		btnUpdate.setBounds(123, 514, 89, 23);
		contentPane.add(btnUpdate);

		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLeereFelder();
			}
		});
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableLieferant.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					Lieferant updateLieferant = lieferantService.findLieferantById(
							(long) tableLieferant.getModel().getValueAt(tableLieferant.getSelectedRow(), 4));
					lieferantFelderUpdate(updateLieferant);
				}
			}
		});

		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableLieferant.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					long id = (long) tableLieferant.getModel().getValueAt(tableLieferant.getSelectedRow(), 4);
					int result = JOptionPane.showConfirmDialog(null, "Möchten Sie die Daten löschen?");

					switch (result) {
					case 0:
						if (lieferantService.deleteLieferant(id)) {
							JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich gelöscht.");
						} else {
							JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten!", "Fehler",
									JOptionPane.ERROR_MESSAGE);
						}
						break;

					default:
						break;
					}
					getAlleLieferanten();
				}
			}
		});

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("rawtypes")
				Item item = (Item) comboBoxMitarbeiter.getSelectedItem();
				Object mitarbeiter = item.getValue();

				Lieferant lieferant = new Lieferant();
				lieferant.setName(textFieldName.getText().toUpperCase());
				lieferant.setTel(textFieldTelefon.getText());
				lieferant.setMitarbeiter((Mitarbeiter) mitarbeiter);

				if (!textFieldId.getText().isEmpty()) {
					lieferant.setId(Long.parseLong(textFieldId.getText()));
					lieferantService.updateLieferant(lieferant);
				} else {
					lieferantService.speichernLieferant(lieferant);
				}
				getLeereFelder();
				felderHidden();
				getAlleLieferanten();
			}
		});
	}

	protected void lieferantFelderUpdate(Lieferant updateLieferant) {

		lblNewLabel_2.setVisible(true);
		textFieldId.setVisible(true);

		textFieldId.setText(String.valueOf(updateLieferant.getId()));
		textFieldName.setText(updateLieferant.getName());
		textFieldTelefon.setText(updateLieferant.getTel());
		comboBoxMitarbeiter.setSelectedItem(new Item<Object>(updateLieferant.getMitarbeiter(),
				updateLieferant.getMitarbeiter().getVorname() + " " + updateLieferant.getMitarbeiter().getNachname()));

	}

	protected void felderHidden() {

		textFieldId.setVisible(false);
		lblNewLabel_2.setVisible(false);

	}

	protected void getLeereFelder() {
		textFieldName.setText("");
		textFieldTelefon.setText("");
		comboBoxMitarbeiter.setSelectedIndex(0);
		textFieldId.setText("");
	}

	private void comboBoxMitarbeiterList() {

		List<Mitarbeiter> mitarbeiterList = mitarbeiterService.alleMitarbeiter();

		for (Mitarbeiter mitarbeiter : mitarbeiterList) {

			comboBoxMitarbeiter
					.addItem(new Item<Object>(mitarbeiter, mitarbeiter.getVorname() + " " + mitarbeiter.getNachname()));
		}
	}

	private void getAlleLieferanten() {
		int no = 1;
		DefaultTableModel modelLieferant = (DefaultTableModel) tableLieferant.getModel();

		modelLieferant.setRowCount(0);

		List<Lieferant> list = lieferantService.alleLieferanten();

		for (Lieferant lieferant : list) {

			modelLieferant.addRow(new Object[] { no, lieferant.getName(), lieferant.getTel(),
					lieferant.getMitarbeiter().getVorname() + " " + lieferant.getMitarbeiter().getNachname(),
					lieferant.getId() });
			no++;
		}
	}
}
