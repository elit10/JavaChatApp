package homework;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String Name;
	public int Password;
	public int ID;
	
	public User(String Name,int Password,int ID)
	{
		this.Name = Name;
		this.Password = Password;
		this.ID = ID;
	}
	
	public String toString()
	{
		return "the user Name is :" + Name + " the Password is : " + Password + " The ıd is : " + ID;
	}
	
	public boolean equals(User u)
	{
		return this.Name.equals(u.Name) && Password == u.Password && ID == u.ID;
	}
	
}
