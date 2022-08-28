package homework;

import java.io.*;
import java.net.*;

public class Server 
{
	
	
	public static void main(String[] args) 
	{
		try
		{
			ServerSocket sv = new ServerSocket(5000);
			Socket socket = sv.accept();
			OutputStream out = socket.getOutputStream();
			DataOutputStream dout = new  DataOutputStream(out);
			
			InputStream in = socket.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			
			String inputMsg = din.readUTF();
			
			while(!inputMsg.equals("sv_Exit"))
			{
				inputMsg = din.readUTF();
				
				dout.writeUTF(inputMsg);
				
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
