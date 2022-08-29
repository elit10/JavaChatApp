package homework;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client 
{
	private OutputStream out;
	private DataOutputStream dout;
	private InputStream in;
	private DataInputStream din;
	private Socket socket;
	

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		
		String name = sc.nextLine();
		
		ChatScreen cs = new ChatScreen(name);
		
		Client c = new Client(cs);
	}
	
	public void SendData(String arg)
	{
		try 
		{
			dout.writeUTF(arg);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Client(ChatScreen cs)
	{
		
		
		
		cs.curClient = this;
		try
		{
			socket = new Socket("localhost",Server.ip);
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			in = socket.getInputStream();
			din = new DataInputStream(in);
			while(true)
			{
				String input = din.readUTF();
				cs.AddLine(input);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
