package MessageToClient;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class LoginMessage 
{
	private static final int PORT = 1110;
	
	public static void main(String poruka, String adresa)
	{
		try
		{
			Socket s = new Socket(adresa, PORT);
			
			OutputStream out = s.getOutputStream();
			OutputStreamWriter outw = new OutputStreamWriter(out);
			BufferedWriter bw = new BufferedWriter(outw);
			
			bw.write(poruka + "\n");
			bw.flush();
			bw.close();
			
			s.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greska kod slanja login poruke "+e);
		}
	}
}
