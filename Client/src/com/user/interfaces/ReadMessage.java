package com.user.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class ReadMessage {

	private JFrame frame;
	private JTextField tfSifrovanaPoruka;
	private JTextField tfKljuc;
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
					ReadMessage window = new ReadMessage();
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
	public ReadMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea taTekst = new JTextArea();
		taTekst.setFont(new Font("Monospaced", Font.PLAIN, 16));
		taTekst.setWrapStyleWord(true);
		taTekst.setLineWrap(true);
		taTekst.setBounds(22, 122, 482, 225);
		frame.getContentPane().add(taTekst);
		
		tfSifrovanaPoruka = new JTextField();
		tfSifrovanaPoruka.setBounds(22, 13, 482, 22);
		frame.getContentPane().add(tfSifrovanaPoruka);
		tfSifrovanaPoruka.setColumns(10);
		
		tfKljuc = new JTextField();
		tfKljuc.setBounds(22, 48, 482, 22);
		frame.getContentPane().add(tfKljuc);
		tfKljuc.setColumns(10);
		
		JButton bDesifruj = new JButton("Desifruj");
		bDesifruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				byte[] kljucByte = Base64.getDecoder().decode(kljuc);
				SecretKey skKljuc = new SecretKeySpec(kljucByte, 0, kljucByte.length, "AES");
				
				String desifrovanaPoruka = decrypt(sifrovanaPoruka, skKljuc);
				taTekst.setText(desifrovanaPoruka);
			}
		});
		bDesifruj.setBounds(212, 83, 97, 25);
		frame.getContentPane().add(bDesifruj);
		
		tfSifrovanaPoruka.setText(sifrovanaPoruka);
		tfKljuc.setText(kljuc);
	}
	
	public String decrypt(String poruka, SecretKey kljuc)
	{
		String rezultat = "";
		
		try
		{
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, kljuc);
			rezultat = new String(c.doFinal(Base64.getDecoder().decode(poruka)));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greska kod dekriptovanja "+e);
		}
		
		return rezultat;
	}
}
