package DataManage;

import java.util.Base64;

import javax.crypto.SecretKey;

import AES.AESEncrypt;
import AES.AESKeyGen;
import MessageBuffer.Push;
import MessageToClient.LoginMessage;
import MessageToClient.RegistrationMessage;
import MessageToClient.SentMessageMessage;
import SQL.CheckLogin;
import SQL.DeleteSentMessages;
import SQL.FetchMessageForClient;
import SQL.NewMessage;
import SQL.NewUser;

public class SortData 
{
	public void sortirajPodatke(String ulaz, String adresa) throws Exception
	{
		NewUser nu = new NewUser();
		
		String[] podaci = new String[4];
		podaci = ulaz.split("#");
		
		if (podaci[0].equals("reg"))
		{
			if (!nu.provjeriKorisnika(podaci[1], podaci[3]))
			{
				nu.registrujNovogKorisnika(podaci);
				RegistrationMessage.main("Uspjesna registracija", adresa);
			}
			else
			{
				RegistrationMessage.main("Nije moguca registracija, email ili username postoji", adresa);
			}
		}
		else if (podaci[0].equals("log"))
		{
			CheckLogin cl = new CheckLogin();
			
			if (cl.provjeriPostojanjeKorisnika(podaci[1]))
			{
				if (cl.provjeriLogin(podaci[1], podaci[2]))
				{
					Push p = new Push();
					String[] klijenti = p.pushKlijente();
					boolean imaKlijent = false;
					String klijent = "";
					
					for (int brojac = 0; klijenti[brojac] != null; brojac++)
					{
						if (klijenti[brojac].equals(podaci[1]))
						{
							System.out.println("Klijent ceka poruku:"+klijenti[brojac]);
							klijent = klijenti[brojac];
							imaKlijent = true;
						}
					}
					
					if (imaKlijent)
					{
						FetchMessageForClient fmfc = new FetchMessageForClient();
						DeleteSentMessages dsm = new DeleteSentMessages();
						String[][] poruke = new String[999999][2];
						
						poruke = fmfc.decryptUsernameUzmiPoruku(klijent);
						String porukeZaPoslat = "";
						
						//za sad samo jedna poruka se moze poslat :/
						/*
						for (int brojac = 0; poruke[brojac][0] != null; brojac++)
						{
							if (porukeZaPoslat.isEmpty()) porukeZaPoslat = poruke[brojac][1];
							else porukeZaPoslat = porukeZaPoslat + "#" + poruke[brojac][1];
						}
						*/
						
						porukeZaPoslat = poruke[0][1];
						
						LoginMessage.main("suc1#"+porukeZaPoslat, adresa);
						dsm.izbrisiPoruke(poruke[0][0]);
					}
					else LoginMessage.main("suc0", adresa);
				}
				else
				{
					LoginMessage.main("Pogrešan password!", adresa);
				}
			}
			else LoginMessage.main("Nepostojeci login!", adresa); 
		}
		else if (podaci[0].equals("msg"))
		{
			AESEncrypt ae = new AESEncrypt();
			AESKeyGen akg = new AESKeyGen();
			//AESDecrypt ad = new AESDecrypt();
			ShuffleKeyIntoMessage skim = new ShuffleKeyIntoMessage();
			NewMessage nm = new NewMessage();
			
			SecretKey kljuc = akg.generisiKljuc(podaci[2].substring(0, 8));
			String kriptovanaPoruka = ae.encrypt(kljuc, podaci[2]);	
			String kljucString = Base64.getEncoder().encodeToString(kljuc.getEncoded());
			String kriptovanUsername = ae.encrypt(kljuc, podaci[1]);
			
			kriptovanaPoruka = skim.ubaciKljuc(kriptovanaPoruka, kljucString);
			nm.spremiNovuPoruku(kriptovanUsername, kriptovanaPoruka, Integer.valueOf(podaci[3]));
			
			SentMessageMessage.main("Uspjesno poslana poruka!", adresa);
			
			/*
			//samo za test
			byte[] kljucByte = Base64.getDecoder().decode(kljucString);
			SecretKey kljucOdStringa = new SecretKeySpec(kljucByte, 0, kljucByte.length, "AES");
			String dekriptovano = ad.decrypt(kriptovanaPoruka, kljucOdStringa);
			System.out.println("Kljuc u string formatu: "+kljucString);
			System.out.println("Originalna poruka: "+podaci[2]);
			System.out.println("Kriptovana poruka: "+kriptovano);
			System.out.println("Dekriptovana poruka: "+dekriptovano);
			*/
		}
	}
}
