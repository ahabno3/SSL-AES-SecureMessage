package com.SSL;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SSL 
{
	private static final String SERVER = "localhost";
	private static final int PORT = 1104;
	
	public void posaljiPoruku(String poruka) throws Exception
	{
		try
		{	
			System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "yourKeyStorePassword");
			
			SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket ss = (SSLSocket) ssf.createSocket(SERVER, PORT);
			ss.setEnabledCipherSuites(ss.getEnabledCipherSuites());
			
			OutputStream out = ss.getOutputStream();
			OutputStreamWriter outw = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(outw);
			
			bw.write(poruka + "\n");
			bw.flush();
			bw.close();
			
			ss.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "greska kod ssl klijenta "+e);
		}
	}
}
