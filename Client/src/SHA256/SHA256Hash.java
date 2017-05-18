package SHA256;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.swing.JOptionPane;

public class SHA256Hash 
{
	public String hash(String poruka)
	{
		String rezultat = "";
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(poruka.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for (byte b : hash) rezultat = sb.append(String.format("%02x", b)).toString();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod SHA256 "+e);
		}
		
		return rezultat;
	}
}
