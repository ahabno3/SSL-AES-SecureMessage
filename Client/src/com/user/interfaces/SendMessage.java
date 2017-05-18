package com.user.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.SSL.AnswerFromServer;
import com.SSL.SSL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SendMessage {

	private JFrame frame;
	private JTextField tfUsername;
	public static String sifrovanaPoruka;
	public static String kljuc;
	
	/**
	 * Launch the application.
	 */
	public static void main(String sp, String k) {
		sifrovanaPoruka = sp;
		kljuc = k;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessage window = new SendMessage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SendMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 501, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(122, 13, 116, 22);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(33, 16, 88, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea taTekst = new JTextArea();
		taTekst.setWrapStyleWord(true);
		taTekst.setRows(20);
		
		taTekst.setLineWrap(true);
		taTekst.setFont(new Font("Monospaced", Font.PLAIN, 14));
		taTekst.setBounds(33, 86, 414, 226);
		frame.getContentPane().add(taTekst);
		
		JLabel lblPoruka = new JLabel("Poruka:");
		lblPoruka.setBounds(33, 56, 56, 16);
		frame.getContentPane().add(lblPoruka);
		
		JLabel lblDuzinaPostojanja = new JLabel("Duzina postojanja: ");
		lblDuzinaPostojanja.setBounds(33, 348, 128, 16);
		frame.getContentPane().add(lblDuzinaPostojanja);
		
		JComboBox cbDuzina = new JComboBox();
		cbDuzina.setBounds(186, 345, 116, 22);
		cbDuzina.addItem("");
		for (int brojac = 2; brojac < 25; brojac = brojac + 2) cbDuzina.addItem(String.valueOf(brojac)+" sata");
		frame.getContentPane().add(cbDuzina);
		
		JButton bPosalji = new JButton("Posalji");
		bPosalji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (tfUsername.getText().isEmpty() || taTekst.getText().isEmpty() || cbDuzina.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Morate unijeti username, tekst i duzinu trajanja poruke!");
				}
				else
				{
					SSL ssl = new SSL();
					try
					{
						String skracenaDuzina = (String) cbDuzina.getSelectedItem();
						skracenaDuzina = skracenaDuzina.trim().substring(0, skracenaDuzina.indexOf("s")-1);
						String poruka = taTekst.getText().replaceAll("\n", " ");
						
						ssl.posaljiPoruku("msg#"+tfUsername.getText()+"#"+poruka+"#"+skracenaDuzina);
						
						AnswerFromServer.main(null);
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "Greska kod slanja poruke na server "+e);
					}
				}
			}
		});
		bPosalji.setBounds(173, 422, 97, 25);
		frame.getContentPane().add(bPosalji);
		
		JButton bProcitajPoruku = new JButton("Procitaj poruku");
		bProcitajPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (sifrovanaPoruka != null && kljuc != null) ReadMessage.main(sifrovanaPoruka, kljuc);
				else JOptionPane.showMessageDialog(null, "Nemate novih poruka!");
			}
		});
		bProcitajPoruku.setBounds(33, 496, 414, 25);
		frame.getContentPane().add(bProcitajPoruku);
	}
}
