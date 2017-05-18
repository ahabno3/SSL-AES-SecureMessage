package com.user.interfaces;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Image;

import javax.crypto.SecretKey;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;

import com.SSL.AnswerFromServer;
import com.SSL.SSL;

import SHA256.SHA256Hash;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(204, 204, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		
		JLabel lLogo = new JLabel("");
		lLogo.setBounds(97, 13, 90, 62);
		lLogo.setIcon(new ImageIcon(new ImageIcon("pictures/logoklijent.png").getImage().getScaledInstance(lLogo.getWidth(), lLogo.getHeight(), Image.SCALE_DEFAULT)));
		frame.getContentPane().add(lLogo);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(111, 128, 76, 16);
		frame.getContentPane().add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(84, 157, 116, 22);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(111, 210, 76, 16);
		frame.getContentPane().add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(84, 239, 116, 22);
		frame.getContentPane().add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton bLogin = new JButton("Login");
		bLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (tfUsername.getText().isEmpty()) JOptionPane.showMessageDialog(null, "No username has been input!");
				if (tfPassword.getPassword().length == 0) JOptionPane.showMessageDialog(null, "No password has been input!");
				
				if (!tfUsername.getText().isEmpty() && tfPassword.getPassword().length != 0)
				{
					try
					{
						SHA256Hash sha = new SHA256Hash();
						SSL ssl = new SSL();
						
						String password = "";
						StringBuilder sb = new StringBuilder();
						for (char c : tfPassword.getPassword()) password = sb.append(c).toString();
						password = sha.hash(password);
						ssl.posaljiPoruku("log#"+tfUsername.getText()+"#"+password);
						
						AnswerFromServer.main(null);
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, "Greska kod slanja logina "+e);
					}
				}
			}
		});
		bLogin.setBounds(97, 316, 97, 25);
		frame.getContentPane().add(bLogin);
		frame.setBounds(100, 100, 300, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public void pokreniGlavniProzor(String sifrovanaPoruka, String kljuc)
	{
		SendMessage.main(sifrovanaPoruka, kljuc);
	}
}
