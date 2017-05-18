package MessageToClient;


import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class RegistrationMessage 
{
	private static final int PORT = 1110;
	
	public static void main(String poruka, String adresa) throws Exception
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
			JOptionPane.showMessageDialog(null, "greska kod slanja povratne poruke "+e);
		}
	}
}
