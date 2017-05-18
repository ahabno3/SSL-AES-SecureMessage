package MessageBuffer;

import DataManage.ManageUsers;
import SQL.ReadClientsAwaitingMessage;

public class Push 
{
	public String[] pushKlijente()
	{
		String[] klijenti;
		String[][] neobradeniPodaci;
		ReadClientsAwaitingMessage rcam = new ReadClientsAwaitingMessage();
		ManageUsers mu = new ManageUsers();
		
		neobradeniPodaci = rcam.ucitajKlijenteKojiCekajuPoruku();
		klijenti = mu.decrytUsernames(neobradeniPodaci);
		
		return klijenti;
	}
}
