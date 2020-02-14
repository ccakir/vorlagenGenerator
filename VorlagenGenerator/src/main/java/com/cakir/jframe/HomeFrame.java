package com.cakir.jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame();
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
	public HomeFrame() {
		setForeground(Color.LIGHT_GRAY);
		setBounds(100, 100, 712, 680);
		setResizable(false);
		setTitle("VORLAGEN GENERATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLieferant = new JButton("LIEFERANTEN");
		
		btnLieferant.setBackground(Color.DARK_GRAY);
		btnLieferant.setForeground(Color.WHITE);
		btnLieferant.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLieferant.setBounds(45, 405, 142, 36);
		contentPane.add(btnLieferant);
		
		JButton btnBetreuer = new JButton("BETREUER");
		
		btnBetreuer.setForeground(Color.WHITE);
		btnBetreuer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBetreuer.setBackground(Color.DARK_GRAY);
		btnBetreuer.setBounds(224, 405, 142, 36);
		contentPane.add(btnBetreuer);
		
		JButton btnNewButton = new JButton("TEIL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeilFrame tframe = new TeilFrame();
				tframe.setLocationRelativeTo(null);
				tframe.setVisible(true);
			}
		});
		btnNewButton.setBounds(55, 282, 89, 23);
		contentPane.add(btnNewButton);
		
		btnLieferant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LieferantFrame lieferantFrame = new LieferantFrame();
				lieferantFrame.setLocationRelativeTo(null);
				lieferantFrame.setVisible(true);
				
				
			}
		});
		
		btnBetreuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MitarbeiterFrame betreuerFrame = new MitarbeiterFrame();
				betreuerFrame.setLocationRelativeTo(null);
				betreuerFrame.setVisible(true);
				
			}
		});
	}
}
