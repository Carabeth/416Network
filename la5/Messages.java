// Written By Carrie E. Adkins and Tyler j. Barrett
// Messages

import java.io.*;
import java.util.*;

public class Messages {
	public class Message{
	public int sender;
	public int reciever;
	public String message;

	public Message( int out, int in, String content) {
		sender = out;
		reciever = in;
		message = content;
	}
}

// vector of the messages
public Vector<Message> messages;
// messages constructor
public Messages(){
	messages = new Vector<Message>();
}

public void input(String Name) {
	try{

		File nodes = new File(Name);
		Scanner scan = new Scanner(nodes);

		while(scan.hasNextLine()){

			String line = scan.nextLine();
			char c1 = line.charAt(0);
			char c2 = line.charAt(2);
			String message = line.substring(4);
			int i = Character.getNumericalValue(c1 - 1);
			int 0 = Characte.getNumericalValue(c2 - 1);
			Message c = new Message(i, o, content);
			messages.add(c);
		}
		scan.close();
	}
	catch(Exception e){
		System.out.println(e);
	}
}
}