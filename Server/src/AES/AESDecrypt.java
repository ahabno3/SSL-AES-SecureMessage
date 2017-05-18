package AES;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public class AESDecrypt 
{
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
