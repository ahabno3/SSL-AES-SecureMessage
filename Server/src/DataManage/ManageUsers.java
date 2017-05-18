package DataManage;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import AES.AESDecrypt;

public class ManageUsers 
{
	public String[] decrytUsernames(String[][] neobradeniPodaci)
	{
		String[] rezultat = new String[999999];
		AESDecrypt ad = new AESDecrypt();
		
		for(int brojac = 0; neobradeniPodaci[brojac][0] != null; brojac++)
		{
			byte[] kljucByte = Base64.getDecoder().decode(izvadiKljuc(neobradeniPodaci[brojac][1]));
			SecretKey kljuc = new SecretKeySpec(kljucByte, 0, kljucByte.length, "AES");
			
			String dekriptovanUsername = ad.decrypt(neobradeniPodaci[brojac][0], kljuc);
			if (!postojiUBufferu(rezultat, dekriptovanUsername)) rezultat[nadiZadnjiNull(rezultat)] = dekriptovanUsername;
			System.out.println("Dekriptovan username: "+dekriptovanUsername);
		}
		
		return rezultat;
	}
	
	public String izvadiKljuc(String poruka)
	{
		String rezultat;
		
		rezultat = poruka.substring(poruka.length() - 24);
		System.out.println("Isjecen kljuc: "+rezultat);
		
		return rezultat;
	}
	
	public boolean postojiUBufferu(String[] buffer, String username)
	{
		boolean rezultat = false;
		
		for (int brojac = 0; buffer[brojac] != null; brojac++)
		{
			if (buffer[brojac].equals(username))
			{
				rezultat = true;
				break;
			}
		}
		
		return rezultat;
	}
	
	public int nadiZadnjiNull (String[] buffer)
	{
		int brojac = 0;
		
		if (buffer[0] == null) brojac = 0;
		else
		{
			for (brojac = 0; buffer[brojac] != null; brojac++);
			
			return brojac + 1;
		}
		
		System.out.println("Brojac: "+brojac);
		
		return brojac;
	}
}
