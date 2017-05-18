package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import AES.AESDecrypt;
import DataManage.ManageUsers;

public class FetchMessageForClient 
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
	
	public String[][] decryptUsernameUzmiPoruku(String username)
	{
		ManageUsers mu = new ManageUsers();
		AESDecrypt ad = new AESDecrypt();
		
		String[][] rezultat = new String[999999][2];
		String[][] kriptovaniPodaci = new String[999999][4];
		
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT IDPoruka, Username, Poruka FROM poruke";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				kriptovaniPodaci[brojac][0] = String.valueOf(rs.getInt("IDPoruka"));
				kriptovaniPodaci[brojac][1] = rs.getString("Username");
				kriptovaniPodaci[brojac][2] = rs.getString("Poruka");
			}
			
			for (int brojac = 0; kriptovaniPodaci[brojac][0] != null; brojac++)
			{
				kriptovaniPodaci[brojac][3] = mu.izvadiKljuc(kriptovaniPodaci[brojac][2]);
				byte[] kljucByte = Base64.getDecoder().decode(kriptovaniPodaci[brojac][3]);
				SecretKey kljuc = new SecretKeySpec(kljucByte, 0, kljucByte.length, "AES");
				kriptovaniPodaci[brojac][1] = ad.decrypt(kriptovaniPodaci[brojac][1], kljuc);
			}
			
			for (int brojac = 0; kriptovaniPodaci[brojac][0] != null; brojac++)
			{
				if (kriptovaniPodaci[brojac][1].equals(username))
				{
					rezultat[brojac][0] = kriptovaniPodaci[brojac][0];
					rezultat[brojac][1] = kriptovaniPodaci[brojac][2];
				}
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod decryptUsernameUzmiID "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
}
