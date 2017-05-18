package DataManage;

public class ShuffleKeyIntoMessage 
{
	public String ubaciKljuc(String poruka, String kljuc)
	{
		String rezultat = "";
		
		//ovdje treba malo bolji nacin nego append
		
		rezultat = poruka + kljuc;
		
		return rezultat;
	}
}
