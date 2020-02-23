package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.connect.DatabaseConnection;
import com.cakir.model.Schicht;
import com.cakir.model.Teil;
import com.cakir.model.Zettel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.cakir.model.Abteilung;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.ZettelType;
import com.cakir.service.MitarbeiterServiceImpl;
import com.cakir.service.TeilServiceImpl;
import com.cakir.service.ZettelServiceImpl;
import com.cakir.swtconfig.Item;
import com.cakir.swtconfig.SwtComponentsSettings;
import com.cakir.swtconfig.TableConfig;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

@SuppressWarnings("unchecked")
public class ZettelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldId;

	private Connection conn;

	DatabaseConnection connect = new DatabaseConnection();
	private JTextField textFieldGrund;
	private JTextField textFieldBemerkung;
	private JTextField textFieldMaschiene;
	private JLabel lblNewLabel;

	private JFormattedTextField textFieldDatum;

	public JComboBox comboBoxTeil, comboBoxSchicht, comboBoxAbteilung, comboBoxType, comboBoxMitarbeiter;

	SwtComponentsSettings swtSettings = new SwtComponentsSettings();

	TeilServiceImpl teilService = new TeilServiceImpl();
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	ZettelServiceImpl zettelService = new ZettelServiceImpl();

	private JFormattedTextField textFieldQuantity;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JButton btnSpeichern;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZettelFrame frame = new ZettelFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param updateZettel
	 */
	public ZettelFrame() {
		setTitle("ZETTEL");
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 717, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(56, 46, 162, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		JLabel lblDatum = new JLabel("DATUM");
		lblDatum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatum.setBounds(56, 70, 162, 14);
		contentPane.add(lblDatum);

		JLabel lblGrund = new JLabel("GRUND");
		lblGrund.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGrund.setBounds(56, 95, 162, 14);
		contentPane.add(lblGrund);

		JLabel lblBemerkung = new JLabel("BEMERKUNG");
		lblBemerkung.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBemerkung.setBounds(56, 119, 162, 14);
		contentPane.add(lblBemerkung);

		JLabel lblSchicht = new JLabel("SCHICHT");
		lblSchicht.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSchicht.setBounds(56, 145, 162, 14);
		contentPane.add(lblSchicht);

		JLabel lblAbteilung = new JLabel("ABTEILUNG");
		lblAbteilung.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAbteilung.setBounds(56, 169, 162, 14);
		contentPane.add(lblAbteilung);

		JLabel lblFrMaschiene = new JLabel("FÜR MASCHIENE");
		lblFrMaschiene.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrMaschiene.setBounds(56, 194, 162, 14);
		contentPane.add(lblFrMaschiene);

		JLabel lblStck = new JLabel("STÜCK");
		lblStck.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStck.setBounds(56, 218, 162, 14);
		contentPane.add(lblStck);

		JLabel lblMitarbeiter = new JLabel("MITARBEITER");
		lblMitarbeiter.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMitarbeiter.setBounds(56, 268, 162, 14);
		contentPane.add(lblMitarbeiter);

		JLabel lblTeilnameteilenummer = new JLabel("TEILNAME/TEILENUMMER");
		lblTeilnameteilenummer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTeilnameteilenummer.setBounds(56, 243, 162, 14);
		contentPane.add(lblTeilnameteilenummer);

		JLabel lblZetteltype = new JLabel("ZETTELTYPE");
		lblZetteltype.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblZetteltype.setBounds(56, 292, 162, 14);
		contentPane.add(lblZetteltype);

		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setEditable(false);
		textFieldId.setBounds(261, 44, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setBorder(null);
		textFieldId.setVisible(false);

		textFieldGrund = new JTextField();
		textFieldGrund.setColumns(10);
		textFieldGrund.setBorder(null);
		textFieldGrund.setBounds(261, 93, 354, 20);
		contentPane.add(textFieldGrund);

		textFieldBemerkung = new JTextField();
		textFieldBemerkung.setColumns(10);
		textFieldBemerkung.setBorder(null);
		textFieldBemerkung.setBounds(261, 117, 354, 20);
		contentPane.add(textFieldBemerkung);

		textFieldMaschiene = new JTextField();
		textFieldMaschiene.setColumns(10);
		textFieldMaschiene.setBorder(null);
		textFieldMaschiene.setBounds(261, 192, 179, 20);
		contentPane.add(textFieldMaschiene);

		comboBoxSchicht = new JComboBox();
		comboBoxSchicht.setModel(new DefaultComboBoxModel(Schicht.values()));
		comboBoxSchicht.setBounds(261, 143, 70, 20);
		contentPane.add(comboBoxSchicht);

		comboBoxAbteilung = new JComboBox();
		comboBoxAbteilung.setModel(new DefaultComboBoxModel(Abteilung.values()));
		comboBoxAbteilung.setBounds(261, 167, 70, 20);
		contentPane.add(comboBoxAbteilung);

		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(ZettelType.values()));
		comboBoxType.setBounds(261, 290, 179, 20);
		contentPane.add(comboBoxType);

		comboBoxTeil = new JComboBox<Item<Object>>();
		comboBoxTeil.setBounds(261, 241, 291, 20);
		contentPane.add(comboBoxTeil);
		comboBoxTeilList();

		comboBoxMitarbeiter = new JComboBox<Item<Object>>();
		comboBoxMitarbeiter.setBounds(261, 266, 291, 20);
		contentPane.add(comboBoxMitarbeiter);
		comboBoxMitarbeiterList();

		textFieldDatum = new JFormattedTextField(new Date());
		textFieldDatum.setBounds(261, 68, 86, 20);
		textFieldDatum.setBorder(null);
		contentPane.add(textFieldDatum);

		textFieldQuantity = new JFormattedTextField(NumberFormat.getInstance());
		textFieldQuantity.setBounds(261, 216, 70, 20);
		textFieldQuantity.setBorder(null);
		contentPane.add(textFieldQuantity);

		lblNewLabel_1 = new JLabel("* Pflichtfeld");
		lblNewLabel_1.setBounds(56, 331, 162, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setBounds(357, 71, 46, 14);
		contentPane.add(lblNewLabel_2);

		label = new JLabel("*");
		label.setBounds(625, 96, 46, 14);
		contentPane.add(label);

		label_1 = new JLabel("*");
		label_1.setBounds(341, 219, 46, 14);
		contentPane.add(label_1);

		label_2 = new JLabel("*");
		label_2.setBounds(562, 244, 46, 14);
		contentPane.add(label_2);

		label_3 = new JLabel("*");
		label_3.setBounds(562, 269, 46, 14);
		contentPane.add(label_3);

		label_4 = new JLabel("*");
		label_4.setBounds(450, 293, 46, 14);
		contentPane.add(label_4);

		btnSpeichern = new JButton("SPEICHERN");
		btnSpeichern.setBounds(261, 327, 89, 23);
		swtSettings.jButtonSettins(btnSpeichern);
		btnSpeichern.setIcon(new ImageIcon(
				new File(getClass().getClassLoader().getResource("icons/plus.png").getFile()).getAbsolutePath()));
		contentPane.add(btnSpeichern);

		btnReset = new JButton("RESET");
		btnReset.setBounds(375, 327, 89, 23);
		swtSettings.jButtonSettins(btnReset);
		btnReset.setIcon(
				new ImageIcon(new File(getClass().getClassLoader().getResource("icons/zuruecksetzen.png").getFile())
						.getAbsolutePath()));
		contentPane.add(btnReset);

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Item<Object> itemTeil = (Item<Object>) comboBoxTeil.getSelectedItem();
				Object teil = itemTeil.getValue();

				Item<Object> itemMitarbeiter = (Item<Object>) comboBoxMitarbeiter.getSelectedItem();
				Object mitarbeiter = itemMitarbeiter.getValue();

				if (textFieldDatum.getText().isEmpty() || textFieldGrund.getText().isEmpty()
						|| textFieldQuantity.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Füllen Sie Pflichtfelder(*) aus.", "FEHLER",
							JOptionPane.WARNING_MESSAGE);

				} else {

					Zettel zettel = new Zettel();
					zettel.setDatum(textFieldDatum.getText());
					zettel.setGrund(textFieldGrund.getText());
					zettel.setBemerkung(textFieldBemerkung.getText());
					zettel.setSchicht(comboBoxSchicht.getSelectedItem().toString());
					zettel.setAbteilung(comboBoxAbteilung.getSelectedItem().toString());
					zettel.setMaschiene(textFieldMaschiene.getText());
					zettel.setQuantity(Integer.parseInt(textFieldQuantity.getText()));
					zettel.setType(comboBoxType.getSelectedItem().toString());
					zettel.setTeil((Teil) teil);
					zettel.setMitarbeiter((Mitarbeiter) mitarbeiter);

					if (!textFieldId.getText().isEmpty()) {
						zettel.setId(Long.parseLong(textFieldId.getText()));
						if (zettelService.updateZettel(zettel)) {

							JOptionPane.showMessageDialog(null,
									"Die Daten wurden aktualisiert. Sie werden direkt auf Hauptseite geleitet." + "\n"
											+ "Aktualisieren Sie die Tabelle",
									"AKTUALISIEREN ERFOLGREICH", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Beim Daten Aktualisieren ein Fehler ist aufgetreten." + "\n"
											+ "Versuchen sie nochmal.",
									"AKTUALISIEREN FEHLER", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						zettelService.speichernZettel(zettel);
					}
					leerFelder();
					felderHidden();
				}

			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				leerFelder();
			}
		});

	}

	protected void felderHidden() {
		lblNewLabel.setVisible(false);
		textFieldId.setText("");
		textFieldId.setVisible(false);
	}

	protected void leerFelder() {

		textFieldBemerkung.setText("");
		textFieldDatum.setText("");
		textFieldGrund.setText("");
		textFieldMaschiene.setText("");
		textFieldQuantity.setText("");

		comboBoxAbteilung.setSelectedIndex(0);
		comboBoxSchicht.setSelectedIndex(0);
		comboBoxTeil.setSelectedIndex(0);
		comboBoxMitarbeiter.setSelectedIndex(0);
		comboBoxType.setSelectedIndex(0);

	}

	private void comboBoxMitarbeiterList() {

		List<Mitarbeiter> listMitarbeiter = mitarbeiterService.alleMitarbeiter();

		for (Mitarbeiter mitarbeiter : listMitarbeiter) {

			comboBoxMitarbeiter
					.addItem(new Item<Object>(mitarbeiter, mitarbeiter.getVorname() + " " + mitarbeiter.getNachname()));
		}
	}

	private void comboBoxTeilList() {

		List<Teil> listTeil = teilService.alleTeile();

		for (Teil teil : listTeil) {

			comboBoxTeil.addItem(new Item<Object>(teil, teil.getTeilname() + " - " + teil.getTeilenummer()));
		}
	}

	public void updateFelder(Zettel updateZettel) {

		lblNewLabel.setVisible(true);
		textFieldId.setVisible(true);

		textFieldId.setText(String.valueOf(updateZettel.getId()));
		textFieldGrund.setText(updateZettel.getGrund());
		textFieldDatum.setText(updateZettel.getDatum());
		textFieldBemerkung.setText(updateZettel.getBemerkung());
		textFieldQuantity.setText(String.valueOf(updateZettel.getQuantity()));
		textFieldMaschiene.setText(updateZettel.getMaschiene());

		comboBoxAbteilung.addItem(updateZettel.getAbteilung());
		comboBoxAbteilung.setSelectedItem(updateZettel.getAbteilung());

		comboBoxMitarbeiter.setSelectedItem(new Item<Object>(updateZettel.getMitarbeiter(),
				updateZettel.getMitarbeiter().getVorname() + " " + updateZettel.getMitarbeiter().getNachname()));

		comboBoxTeil.addItem(new Item<Object>(updateZettel.getTeil(),
				updateZettel.getTeil().getTeilname() + " - " + updateZettel.getTeil().getTeilenummer()));
		comboBoxTeil.setSelectedItem(new Item<Object>(updateZettel.getTeil(),
				updateZettel.getTeil().getTeilname() + " - " + updateZettel.getTeil().getTeilenummer()));

		comboBoxType.addItem(updateZettel.getType());
		comboBoxType.setSelectedItem(updateZettel.getType());

		comboBoxSchicht.addItem(updateZettel.getSchicht());
		comboBoxSchicht.setSelectedItem(updateZettel.getSchicht());
	}
}
