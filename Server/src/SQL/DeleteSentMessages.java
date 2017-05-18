package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DeleteSentMessages 
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
	
	public void izbrisiPoruke(String ID)
	{
		otvoriKonekciju();		
		try
		{
			stmt = conn.createStatement();
			String query = "DELETE FROM poruke WHERE IDPoruka = '"+ID+"'";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod brisanja poruka "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
}
