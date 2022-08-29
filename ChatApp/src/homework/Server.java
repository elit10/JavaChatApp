package homework;

import java.io.*;
import java.net.*;

import javax.swing.Timer;

public class Server 
{
	
	public static int ip = 6000;
	
	private ServerSocket sv;
	private Socket socket;
	private OutputStream out;
	private InputStream in;
	private DataOutputStream dout;
	private DataInputStream din;
	
	private String inputMsg = "";
	public static void main(String[] args) 
	{
		Server sv = new Server();
	}

	public Server()
	{
		try
		{
			
			sv = new ServerSocket(ip);

			socket = sv.accept();

			System.out.println("Connected ");
			
			out = socket.getOutputStream();
			dout = new  DataOutputStream(out);
			
			in = socket.getInputStream();
			din = new DataInputStream(in);
			
			while(true)
			{
				inputMsg = din.readUTF();
				System.out.println(inputMsg);
				dout.writeUTF(inputMsg);
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
}
