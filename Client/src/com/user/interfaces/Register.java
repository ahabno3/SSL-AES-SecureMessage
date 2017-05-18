package com.user.interfaces;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.SSL.AnswerFromServer;
import com.SSL.SSL;

import SHA256.SHA256Hash;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JLabel lblEmail;
	private JTextField tfEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(24, 13, 87, 16);
		frame.getContentPane().add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(123, 10, 116, 22);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(24, 51, 77, 16);
		frame.getContentPane().add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(123, 45, 116, 22);
		frame.getContentPane().add(tfPassword);
		tfPassword.setColumns(10);
		
		lblEmail = new JLabel("email");
		lblEmail.setBounds(24, 81, 56, 16);
		frame.getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(123, 80, 116, 22);
		frame.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JButton bRegister = new JButton("Register");
		bRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (tfUsername.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Nema username");
				if (tfPassword.getPassword().length == 0) JOptionPane.showMessageDialog(null, "Nema password");
				if (tfEmail.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Nema email");
				
				if (!tfUsername.getText().isEmpty() && tfPassword.getPassword().length != 0 && !tfEmail.getText().isEmpty())
				{
					SSL ssl = new SSL();
					try 
					{
						String password = "";
						StringBuilder sb = new StringBuilder();
						for (char c : tfPassword.getPassword()) password = sb.append(c).toString();
						SHA256Hash sha = new SHA256Hash();
						password = sha.hash(password);
						ssl.posaljiPoruku("reg#"+tfUsername.getText()+"#"+password+"#"+tfEmail.getText());
						
						AnswerFromServer.main(null);
					} 
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(null, "Greska kod slanja"+e);
					}
				}
			}
		});
		bRegister.setBounds(89, 166, 79, 25);
		frame.getContentPane().add(bRegister);
	}
}
