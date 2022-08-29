package homework;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ChatScreen extends JFrame
{
	//10 1 grid
	public JTextField txtChat;
	public String[] chats;
	public JLabel[] lblArray = new JLabel[8];
	public String name;
	public JButton btnSend;
	public JButton btnPing;
	
	public boolean animBool = true;
	
	public int x,y;
	public int xVal = 1;
	public int yVal = 1;
	
	public Client curClient;


	//the constructor for the chat screen
	public ChatScreen(String name)
	{
		this.name = name;
		
		setVisible(true);
		init();
		setSize(400,800);
		setLayout(new GridLayout(10,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	
	}
	
	
	public void init()
	{
		
		initLabel();
		
		txtChat = new JTextField("write here");
		btnSend = new JButton("Send");
		btnPing = new JButton("Toggle Animation");
		
		btnSend.addActionListener(e->Send());
		//btnPing.addActionListener(e->ToggleAnim());
		
		Panel pnl = new Panel();
		
		pnl.setLayout(new GridLayout(1,2));
		pnl.add(btnPing);
		pnl.add(btnSend);
		
		add(txtChat);
		add(pnl);
		
	}
	

	
	// this method is used for adding a new line to the bottom of the array
	// the chat history is also moved upwards
	public void AddLine(String val)
	{
		for(int i = lblArray.length-1; i>= 1;i--)
		{
			lblArray[i].setText(lblArray[i-1].getText());
		}
		
		String toAdd = name + ": " + val;
		lblArray[0].setText(toAdd);
		
	}
	//this method creates and adds the label array to the frame
	public void initLabel()
	{
		for(int i = lblArray.length-1; i>=0; i--)
		{
			lblArray[i] = new JLabel(" asd " + i);
			add(lblArray[i]);
		}
	}
	
	//adds the written line to the chat history
	//can also be used to send info to the server
	public void Send()
	{
		//AddLine(txtChat.getText());
		try
		{
			curClient.SendData(txtChat.getText());
			System.out.println("written");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}









