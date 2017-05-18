package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CheckLogin 
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
	
	public boolean provjeriLogin(String username, String password)
	{
		boolean rezultat = false;
		
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Username, Password FROM Korisnici WHERE Username = '"+username+"' AND Password = '"+password+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) rezultat = true;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod provjere logina");
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	
	public boolean provjeriPostojanjeKorisnika(String username)
	{
		boolean rezultat = false;
		otvoriKonekciju();
		
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Username FROM Korisnici WHERE Username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) rezultat = true;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod provjere postajanja korisnika "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
}
