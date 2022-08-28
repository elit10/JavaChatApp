package homework;

import java.awt.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;
import java.util.HashMap;

public class HW extends JFrame
{
	//these are the components that we will use
	private JLabel lblName,lblPass;
	private TextField txtName,txtPass;
	private JRadioButton sv1,sv2,sv3;
	private JButton btnLogin,btnRegister;
	private JPanel p1,p2,p3;
	
	//These are for logging the users
	private FileOutputStream fos;
	private FileInputStream fis;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//These are the operational variables
	private int svNum;
	private HashMap<String, User> map = new HashMap<>();
	
	
	public static void main(String[] args) 
	{
		HW hw = new HW();
	}
	
	public HW()
	{
		//we will have three panels on top of each other
		setLayout(new GridLayout(3,1));
		
		//we define and add the components in this method
		init();
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	public void init()
	{
	
		//the default value for the server is -1
		svNum = -1;
		
		//we define the input-output streams
		try
		{
			fos = new FileOutputStream("user.ser");
			oos = new ObjectOutputStream(fos);
		
			
			fis = new FileInputStream("user.ser");
			ois = new ObjectInputStream(fis);
		} 
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		
		//the first panel is for label and text fields at the top
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2,2));
		
		// the second panel is for radio buttons
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1,3));
		
		//the third panel is for the buttons
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,2));
		
		
		
		//definition of the labels 
		lblName = new JLabel("Name :");
		lblPass = new JLabel("Password :");
		
		//the textfields 
		txtName = new TextField("user name");
		txtPass = new TextField("00000000");
		
		//the buttons 
		btnLogin = new JButton("Login");
		btnRegister = new JButton("Register");
		
		btnLogin.addActionListener(e -> Login());
		btnRegister.addActionListener(e -> Register());
		
		//the radio buttons
		ButtonGroup btnGroup = new ButtonGroup();
		sv1 = new JRadioButton("server 1");
		sv2 = new JRadioButton("server 2");
		sv3 = new JRadioButton("server 3");
		
		sv1.addActionListener(e -> SetServer(0));
		sv2.addActionListener(e -> SetServer(1));
		sv3.addActionListener(e -> SetServer(2));
		
		btnGroup.add(sv1);
		btnGroup.add(sv2);
		btnGroup.add(sv3);
		
		
		
		//Here we add components to the panels
		p1.add(lblName);
		p1.add(txtName);
		p1.add(lblPass);
		p1.add(txtPass);
		
		p2.add(sv1);
		p2.add(sv2);
		p2.add(sv3);
		
		p3.add(btnLogin);
		p3.add(btnRegister);
		
		//Here we add the panels to the frame
		add(p1);
		add(p2);
		add(p3);
		
		//we add a camouflage color to the frame
		p1.setBackground(new Color(120,134,107));
		p2.setBackground(new Color(120,134,107));
		p3.setBackground(new Color(120,134,107));
	}
	
	
	
	//is called when a radio button is selected
	public void SetServer(int val)
	{
		svNum = val;
	}
	
	//is called when login button is pressed
	public void Login()
	{
		String name = txtName.getText();
		
		if(svNum == -1)
		{
			JOptionPane.showMessageDialog(this,
				    "You need to select a server to continue",
				    "server warning",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//if the user name is correct
		if(map.containsKey(name))
		{
			//if the password is correct
			if(map.get(name).Password == Integer.parseInt(txtPass.getText()))
			{
				
				
				//we accept the login
				
				JOptionPane.showMessageDialog(this,
					    "Login successful ");
				
				//welcome mesage tailored for the user name
				JOptionPane.showMessageDialog(this,
					    "Welcome aboard " + name);
				
				ChatScreen cs = new ChatScreen(name);
				
				return;
			}
			
			
			//the password is wrong
			JOptionPane.showMessageDialog(this,
				    "The password is wrong",
				    "warning",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//the username is not available in the hashmap
		JOptionPane.showMessageDialog(this,
			    "The user name is wrong",
			    "warning",
			    JOptionPane.WARNING_MESSAGE);
		return;

	}
	
	//is called when register button is pressed
	public void Register()
	{
		
		User u;
		int ID = GenerateID();
		
		
		// we try to create a user with current name and password fields
		try
		{
			u = new User(txtName.getText(),Integer.parseInt(txtPass.getText()),ID);
		}
		
		//if the inputs are invalid an error is shown
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(this,
				    "couldn't create the user",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		

		//if the user is not in the hashmap we add it
		String txt = txtName.getText();
		if(!map.containsKey(txt))
		{
			map.put(txt, u);
			JOptionPane.showMessageDialog(this,
				    "Registeration is successful");
			
			System.out.println("put in the hashmap");
		}
	}
	
	
	//this will be used to save the user list in the server
	public void SaveMap()
	{
		
	}
	
	
	//random id creator ---> (10000 - 19999)
	public int GenerateID()
	{
		Random rd = new Random();
		
		return rd.nextInt(9999)+10000;
		
		
	}
	
	
}
