import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.JOptionPane;

import DataManage.SortData;

public class MessagePolling 
{
	public static void main(String[] args) throws Exception
	{
		try
		{	
			while(true)
			{
				System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
				System.setProperty("javax.net.ssl.keyStorePassword", "yourKeyStorePassword");
			
				SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
				SSLServerSocket sss = (SSLServerSocket) ssf.createServerSocket(1104);
				SSLSocket ss = (SSLSocket) sss.accept();
				String adresa = InetAddress.getLocalHost().getHostAddress();
				sss.setEnabledCipherSuites(ss.getSupportedCipherSuites());	
				BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
					
				String poruka = in.readLine();
				in.close();
				
				System.out.println(poruka);
				System.out.println(adresa);
				SortData sd = new SortData();
				sd.sortirajPodatke(poruka, adresa);
				
				ss.close();
				sss.close();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greška server "+e);
		}
	}
}
