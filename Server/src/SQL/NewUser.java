package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class NewUser 
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
	
	public boolean provjeriKorisnika(String username, String email)
	{
		boolean rezultat = false;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Username, Email FROM korisnici WHERE Username = '"+username+"' OR Email = '"+email+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) rezultat = true;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod provjere korisnika "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	
	public void registrujNovogKorisnika(String[] podaci)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO korisnici (Username, Password, Email) "
					+ "VALUES ('"+podaci[1]+"','"+podaci[2]+"','"+podaci[3]+"')";
			
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod registracije novog korisnika "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
}
