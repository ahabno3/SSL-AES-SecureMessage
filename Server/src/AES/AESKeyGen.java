package AES;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class AESKeyGen 
{
	public SecretKey generisiKljuc(String poruka)
	{	
		SecretKey sk = null;
		byte[] kljuc;
		try
		{
			kljuc = poruka.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			kljuc = md.digest(kljuc);
			kljuc = Arrays.copyOf(kljuc, 16);
			sk = new SecretKeySpec(kljuc, "AES");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod generisanja kljua "+e);
		}
		
		return sk;
	}
}
