package com.cakir.jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.connect.DatabaseConnection;
import com.cakir.model.Mitarbeiter;
import com.cakir.service.MitarbeiterService;
import com.cakir.service.MitarbeiterServiceImpl;
import com.cakir.swtconfig.TableConfig;
import com.cakir.validation.StringOperations;
import com.mysql.cj.util.TestUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.ListSelectionModel;

public class MitarbeiterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNachname;
	private JTextField textFieldVorname;
	private JTextField textFieldEmail;
	private JTextField textFieldTel;
	private JLabel lblNewLabel, lblNewLabel_1;
	private JLabel lblNachname;
	private JLabel lblEmail;
	private JLabel lblTelefon;
	private JButton btnSpeichern;

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	StringOperations operation = new StringOperations();
	private JTable tableMitarbeiter;
	private JButton btnUpdate;
	private JTextField textFieldId;
	private JButton btnReset;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MitarbeiterFrame frame = new MitarbeiterFrame();
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
	public MitarbeiterFrame() {
		setTitle("BETREUER");

		setBounds(100, 100, 712, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(63, 136, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNachname = new JTextField();
		textFieldNachname.setBorder(null);
		textFieldNachname.setBounds(158, 77, 292, 20);
		contentPane.add(textFieldNachname);
		textFieldNachname.setColumns(10);

		textFieldVorname = new JTextField();
		textFieldVorname.setColumns(10);
		textFieldVorname.setBounds(158, 46, 292, 20);
		textFieldVorname.setBorder(null);
		contentPane.add(textFieldVorname);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(158, 108, 292, 20);
		textFieldEmail.setBorder(null);
		contentPane.add(textFieldEmail);

		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBorder(null);
		textFieldTel.setBounds(158, 139, 292, 20);
		contentPane.add(textFieldTel);

		lblNewLabel = new JLabel("VORNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(44, 52, 79, 14);
		contentPane.add(lblNewLabel);

		lblNachname = new JLabel("NACHNAME");
		lblNachname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNachname.setBounds(44, 83, 79, 14);
		contentPane.add(lblNachname);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(44, 114, 79, 14);
		contentPane.add(lblEmail);

		lblTelefon = new JLabel("TELEFON");
		lblTelefon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefon.setBounds(44, 145, 79, 14);
		contentPane.add(lblTelefon);

		btnSpeichern = new JButton("SPEICHERN");
		btnSpeichern.setForeground(new Color(255, 255, 255));
		btnSpeichern.setBorder(null);
		btnSpeichern.setBackground(Color.ORANGE);
		btnSpeichern.setBounds(158, 170, 119, 23);
		contentPane.add(btnSpeichern);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 229, 676, 347);
		contentPane.add(scrollPane);

		tableMitarbeiter = new JTable();
		tableMitarbeiter.setBackground(Color.white);
		tableMitarbeiter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMitarbeiter.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Vorname", "Nachname", "Email", "Telefon", "" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unused", "rawtypes" })
			private Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		});

		//Column Widths chance
		TableConfig tableConfig = new TableConfig();
		tableConfig.setJTableColumnsWidth(tableMitarbeiter, 480, 8, 23, 23, 23, 23, 0);
		tableConfig.tableAnsicht(tableMitarbeiter, 5);
		
		getAlleMitarbeiter();
		scrollPane.setViewportView(tableMitarbeiter);

		JButton btnDelete = new JButton("LÖSCHEN");

		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(10, 587, 113, 23);
		btnDelete.setBorder(null);
		contentPane.add(btnDelete);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBorder(null);
		btnUpdate.setBounds(144, 587, 89, 23);
		contentPane.add(btnUpdate);

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setBounds(158, 15, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setVisible(false);

		lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(44, 18, 46, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		
		btnReset = new JButton("RESET");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.ORANGE);
		btnReset.setBorder(null);
		btnReset.setBounds(302, 170, 89, 23);
		contentPane.add(btnReset);
		

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLeereFelder();
			}
		});
		
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Mitarbeiter mitarbeiter = new Mitarbeiter();
				mitarbeiter.setEmail(textFieldEmail.getText());
				mitarbeiter.setVorname(operation.firstCharToUpperCase(textFieldVorname.getText()));
				mitarbeiter.setNachname(operation.firstCharToUpperCase(textFieldNachname.getText()));
				mitarbeiter.setTel(textFieldTel.getText());

				if (!textFieldId.getText().isEmpty()) {

					mitarbeiter.setId(Long.parseLong(textFieldId.getText()));
					mitarbeiterService.updateMitarbeiter(mitarbeiter);

				} else {
					mitarbeiterService.speichernMitarbeiter(mitarbeiter);
				}

				getLeereFelder();
				felderHidden();
				getAlleMitarbeiter();

			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableMitarbeiter.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);

				} else {

					
					Mitarbeiter mitarbeiter = mitarbeiterService.findMitarbeiterById(
							(long) tableMitarbeiter.getModel().getValueAt(tableMitarbeiter.getSelectedRow(), 5));
					felderUpdate(mitarbeiter);
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableMitarbeiter.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					boolean result = mitarbeiterService.deleteMitarbeiter(
							(long) tableMitarbeiter.getModel().getValueAt(tableMitarbeiter.getSelectedRow(), 5));
					if (result) {
						JOptionPane.showMessageDialog(null, "Mitarbeiter wurde erfolgreich gelöscht.");
						getAlleMitarbeiter();
					} else {
						JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

	}

	

	protected void felderHidden() {
		
		textFieldId.setVisible(false);
		lblNewLabel_1.setVisible(false);
	}

	protected void felderUpdate(Mitarbeiter mitarbeiter) {
		
		textFieldId.setVisible(true);
		lblNewLabel_1.setVisible(true);

		textFieldId.setText(String.valueOf(mitarbeiter.getId()));
		textFieldVorname.setText(mitarbeiter.getVorname());
		textFieldNachname.setText(mitarbeiter.getNachname());
		textFieldEmail.setText(mitarbeiter.getEmail());
		textFieldTel.setText(mitarbeiter.getTel());

	}

	private void getAlleMitarbeiter() {

		int no = 1;
		DefaultTableModel modelMitarbiter = (DefaultTableModel) tableMitarbeiter.getModel();

		modelMitarbiter.setRowCount(0);

		List<Mitarbeiter> list = mitarbeiterService.alleMitarbeiter();

		for (Mitarbeiter mitarbeiter : list) {

			modelMitarbiter.addRow(new Object[] { no, mitarbeiter.getVorname(), mitarbeiter.getNachname(),
					mitarbeiter.getEmail(), mitarbeiter.getTel(), mitarbeiter.getId() });
			no++;
		}

	}

	protected void getLeereFelder() {

		textFieldEmail.setText("");
		textFieldNachname.setText("");
		textFieldTel.setText("");
		textFieldVorname.setText("");

	}
}
