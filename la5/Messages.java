

//written by Xavier

import java.io.*;
import java.util.*;
public class Messages {
	public class Message{
		public int sender;
		public int reciever;
		public String message;
		public Message (int aSend, int aRecieve, String aMessage)
		{
		sender = aSend;
		reciever = aRecieve;
		message = aMessage;
		}
	}
	public Vector<Message> messages;
	public Messages()
	{
		messages = new Vector<Message>();
	}
	public void readIn(String Filename)
	{
		try
		{
			//io
			File connections = new File(Filename);
			Scanner scnnr = new Scanner (connections);
			while(scnnr.hasNextLine())
			{
				//breaks string into 3 chars and parses based on index
				String line = scnnr.nextLine();
				char one = line.charAt(0);
				char two = line.charAt(2);
				String message = line.substring(4);
				int s = Character.getNumericValue(one-1);
				int r = Character.getNumericValue(two-1);
				Message m = new Message(s,r,message);
				messages.add(m);
			}
			scnnr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
