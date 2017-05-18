package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ReadClientsAwaitingMessage 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/zisprojekat?useUnicode=true&characterEncoding=UTF-8";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	
	Connection conn = null;
	Statement stmt = null;
	
	
	public void otvoriKonekciju()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod otvaranja konekcije "+e);
		}
	}
	
	public void zatvoriKonekciju()
	{
		try
		{
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod zatvaranja konekcije: "+e);
		}
	}
	
	public String[][] ucitajKlijenteKojiCekajuPoruku()
	{
		String[][] neobradeniPodaci = new String[999999][2];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Username, Poruka FROM poruke";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				neobradeniPodaci[brojac][0] = rs.getString("Username");
				neobradeniPodaci[brojac][1] = rs.getString("Poruka");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod citanja klijenata koji cekaju poruku "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return neobradeniPodaci;
	}
}
