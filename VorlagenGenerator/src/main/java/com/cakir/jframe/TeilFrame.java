package com.cakir.jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.cakir.model.Lieferant;
import com.cakir.model.Mitarbeiter;
import com.cakir.service.LieferantServiceImpl;
import com.cakir.service.MitarbeiterServiceImpl;
import com.cakir.swtconfig.Item;
import com.cakir.swtconfig.TableConfig;

public class TeilFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldTeilname;
	private JTextField textFieldTeilenummer;
	private JTable tableTeil;
	private JComboBox comboBoxMitarbeiter, comboBoxLieferant;
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	LieferantServiceImpl lieferantService = new LieferantServiceImpl();

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
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 783, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(63, 136, 143));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(50, 37, 100, 14);
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
		
		textFieldTeilname = new JTextField();
		textFieldTeilname.setEnabled(false);
		textFieldTeilname.setEditable(false);
		textFieldTeilname.setColumns(10);
		textFieldTeilname.setBorder(null);
		textFieldTeilname.setBounds(170, 60, 161, 20);
		contentPane.add(textFieldTeilname);
		
		textFieldTeilenummer = new JTextField();
		textFieldTeilenummer.setEnabled(false);
		textFieldTeilenummer.setEditable(false);
		textFieldTeilenummer.setColumns(10);
		textFieldTeilenummer.setBorder(null);
		textFieldTeilenummer.setBounds(170, 85, 161, 20);
		contentPane.add(textFieldTeilenummer);
		
		comboBoxMitarbeiter = new JComboBox<Item<Object>>();
		comboBoxMitarbeiter.setBounds(170, 110, 161, 20);
		contentPane.add(comboBoxMitarbeiter);
		comboBoxMitarbeiterList();
		
		comboBoxLieferant = new JComboBox<Item<Object>>();
		comboBoxLieferant.setBounds(170, 135, 161, 20);
		contentPane.add(comboBoxLieferant);
		comboBoxLieferantList();
		
		JButton btnSpeichern = new JButton("SPEICHERN");
		btnSpeichern.setBackground(Color.ORANGE);
		btnSpeichern.setForeground(Color.WHITE);
		btnSpeichern.setBounds(170, 168, 89, 23);
		btnSpeichern.setBorder(null);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBorder(null);
		btnReset.setBackground(Color.ORANGE);
		btnReset.setBounds(284, 168, 89, 23);
		contentPane.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 231, 747, 295);
		contentPane.add(scrollPane);
		
		tableTeil = new JTable();
		tableTeil.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Teilname", "Teilenummer", "Mitarbeiter", "Lieferant", ""
			}
		));
		
		TableConfig tableConfig = new TableConfig();
		tableConfig.setJTableColumnsWidth(tableTeil, 480, 5, 20, 20, 25, 30, 0);
		tableConfig.tableAnsicht(tableTeil, 5);
		
		scrollPane.setViewportView(tableTeil);
		
		JButton btnLschen = new JButton("LÖSCHEN");
		btnLschen.setForeground(Color.WHITE);
		btnLschen.setBorder(null);
		btnLschen.setBackground(Color.ORANGE);
		btnLschen.setBounds(10, 537, 89, 23);
		contentPane.add(btnLschen);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setBounds(126, 537, 89, 23);
		contentPane.add(btnUpdate);
	}

	private void comboBoxLieferantList() {
		
		List<Lieferant> lieferantList = lieferantService.alleLieferanten();
		
		for(Lieferant lieferant : lieferantList) {
			
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
