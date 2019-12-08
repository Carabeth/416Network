//written by Xavier
import java.io.*;
import java.util.*;


public class ReadIn {
	public static final char DELIM = ' ';
	public class Topology{
		public int[] link;
		public Topology(int a, int b, int c){
			link = new int [] {a,b,c};
		}
	}
	public int size;
	public Vector <Topology> topology;
	public ReadIn()
	{
		int size = 0;
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
				int index = line.indexOf(DELIM);
				String one = line.substring(0,index);
				line = line.substring(index+1);
				index = line.indexOf(DELIM);
				String two = line.substring(0,index);
				String weight = line.substring(index+1);
				//index = line.indexOf(DELIM);
				//String weight = line.substring(index);
				int r1 = Integer.parseInt(one);
				int r2 = Integer.parseInt(two);
				int w = Integer.parseInt(weight);
				//adds each connection twice to get edge on both routers
				Topology l = new Topology(r1-1,r2-1,w);
				//This may not be needed with the code
				Topology n = new Topology(r2-1,r1-1,w);
				topology.add(l);
				topology.add(n);
			}
			this.size = this.countUnique();
			scnnr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	private int countUnique()
	{
		LinkedList<Integer> count = new LinkedList<Integer>();
		for(Topology t : topology)
		{
			if(!count.contains(t.link[0]))
			{
				count.add(t.link[0]);
			}
		}
		return count.size();
	}
	public void print()
	{
		for(Topology t:topology)
		{
			System.out.println(t.link[0] +" "+t.link[1]+" "+t.link[2]);
		}
	}
}
