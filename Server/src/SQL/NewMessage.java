package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class NewMessage 
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
	
	public void spremiNovuPoruku(String username, String poruka, int TTL)
	{
		otvoriKonekciju();
		try
		{
			String trenutniSat = String.valueOf(Calendar.HOUR_OF_DAY);
			String trenutniMinut = String.valueOf(Calendar.MINUTE);
			if (trenutniMinut.length() == 1) trenutniMinut = "0"+trenutniMinut;
			
			int trenutnoVrijeme = Integer.valueOf(String.valueOf(trenutniSat+trenutniMinut));
			stmt = conn.createStatement();
			String query = "INSERT INTO poruke (Username, Poruka, SatKreiranja, TTL) "
					+ "VALUES ('"+username+"','"+poruka+"','"+trenutnoVrijeme+"','"+TTL+"')";
			
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod spremanja nove poruke "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
}
