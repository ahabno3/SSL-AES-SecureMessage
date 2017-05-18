package AES;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;



public class AESEncrypt 
{
	public String encrypt(SecretKey kljuc, String poruka)
	{
		String rezultat = "";
		try
		{
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, kljuc);
			rezultat = Base64.getEncoder().encodeToString(c.doFinal(poruka.getBytes("UTF-8")));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod kriptovanja poruke "+e);
		}
		return rezultat;
	}
}
