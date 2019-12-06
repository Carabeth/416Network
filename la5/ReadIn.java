//written by Xavier
import java.io.*;
import java.util.*;


public class ReadIn {
	private class Topology{
		public int[] link;
		public Topology(int a, int b, int c){
			link = new int [] {a,b,c};
		}
	}
	public Vector <Topology> topology;
	public ReadIn()
	{
		topology = new Vector<Topology>();
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
				char two = line.charAt(3);
				char weight = line.charAt(5);
				int r1 = Character.getNumericValue(one);
				int r2 = Character.getNumericValue(two);
				int w = Character.getNumericValue(weight);
				//adds each connection twice to get edge on both routers
				Topology l = new Topology(r1,r2,w);
				//This may not be needed with the code
				Topology n = new Topology(r2,r1,w);
				topology.add(l);
				topology.add(n);
			}
			scnnr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
