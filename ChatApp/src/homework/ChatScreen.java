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
	
	private InputStream in;
	private DataInputStream din;
	private OutputStream out;
	private DataOutputStream dout;
	
	private Socket socket;
	
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
	
	public void paint(Graphics g) {
		// we check if the user wants an animation or not
		if(animBool)
		{
			super.paint(g);
			g.setColor(Color.RED);
			
			g.fillOval(x, y, 150, 150);
		}
		
			
		
	}
	
	public void init()
	{
		
		initLabel();
		
		txtChat = new JTextField("write here");
		btnSend = new JButton("Send");
		btnPing = new JButton("Toggle Animation");
		
		btnSend.addActionListener(e->Send());
		btnPing.addActionListener(e->ToggleAnim());
		
		Panel pnl = new Panel();
		
		pnl.setLayout(new GridLayout(1,2));
		pnl.add(btnPing);
		pnl.add(btnSend);
		
		add(txtChat);
		add(pnl);
		
		try
		{
			socket = new Socket("localhost",5000);
			in = socket.getInputStream();
			din = new DataInputStream(in);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		Timer s = new Timer(30, e-> Client());
		s.start();
		//Here we create a timer for the animation
		Timer t = new Timer(30, e-> Animation());
		t.start();
	}
	
	
	public void Client()
	{
		
	}
	
	//this method checks if the ball has hit the boundaries or not
	public void Animation()
	{
		x+=xVal;
		y+=yVal;

		System.out.println(x + " , " + y);
		
		repaint();
		if(x>250)
		{
			xVal*=-1;
		}
		if(x<0)
		{
			xVal*=-1;
		}
		if(y>600)
		{
			yVal*=-1;
		}
		if(y<0)
		{
			yVal*=-1;
		}
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
		AddLine(txtChat.getText());
	}
	
	
	//The button action for toggling the animation
	public void ToggleAnim()
	{
		if(animBool)
		{
			AddLine("Closed Animation");
		}
		else 
		{
			AddLine("Started Animation");
		}
		
		animBool = !animBool;
	}
	
}















