package com.SSL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JOptionPane;

import com.user.interfaces.Login;


public class AnswerFromServer 
{
	public static void main(String[] args) throws Exception
	{
		try
		{	
			while(true)
			{
				
				ServerSocket ss = new ServerSocket(1110);
				Socket s = ss.accept();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					
				String poruka = in.readLine();
				in.close();
				String[] porukaArray = new String[999999];
				
				if (poruka.contains("#")) porukaArray = poruka.split("#");
				else porukaArray[0] = poruka;
				
				if (!poruka.isEmpty())
				{
					if (porukaArray[0].equals("suc0"))
					{
						Login l = new Login();
						l.pokreniGlavniProzor(null, null);
					}
					else if (porukaArray[0].equals("suc1"))
					{
						JOptionPane.showMessageDialog(null, "Imate novih poruka!");
						String poruke = "";
						
						//za sad samo jedna poruka :/
						/*
						for (int brojac = 1; porukaArray[brojac] != null; brojac++)
						{
							if (brojac == 1) poruke = porukaArray[brojac];
							else poruke = poruke + "#" + porukaArray[brojac];
							
							System.out.println("Brojac:"+brojac+" Poruke:"+poruke);
						}
						*/
						
						poruke = porukaArray[1];
						Login l = new Login();
						
						
						String[] porukaZaPoslat = new String[2];
						porukaZaPoslat = rastaviSifratIKljuc(poruke);
						
						l.pokreniGlavniProzor(porukaZaPoslat[0], porukaZaPoslat[1]);
						
						System.out.println(poruke);
					}
					else
					{
						System.out.println(poruka);
						JOptionPane.showMessageDialog(null, poruka);
					}
					
					s.close();
					ss.close();
					break;
				}
				
				s.close();
				ss.close();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Greška klijent prihvatanje poruka "+e);
		}
	}
	
	public static String[] rastaviSifratIKljuc(String poruka)
	{
		String[] rezultat = new String[2];
		
		rezultat[1] = poruka.substring(poruka.length() - 24);
		rezultat[0] = poruka.substring(0, poruka.length() - 24);
		
		return rezultat;
	}
}
