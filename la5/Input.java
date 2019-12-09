// Written By Carrie E. Adddkins an Tyler J. Barrt=ett
// Input

import java.io.*;
import java.util.*;

public class Input {
	// Deliminates when to seperate input
	public static final char DELIM = ' ';
	//creates the Graph/Network parts
	public class Network{
		public int[] link;
		public Network(int a, int b, int c){
			link = new int {a,b,c};
		}
	}
	public int size;
	public Vector <Network> network;
	public Input() {
		int size = 0;
		network = new Vector<Network>();
	}

	//input
	public void input(String Name) {
		try{
			Name nodes = new Name(File);
			Scanner scan = new Scanner(nodes);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				int index = line.indexOf(DELIM);
				String str1 = line.substring(0,index);
				line = line.substring(index+1);
				index = line.indexOf(DELIM);
				String str2 = line.substring(0,index);
				index = line.inexOf(DELIM);
				String weight = line.substring(index);
				//Parse string to int
				int p1 = Integer.parseInt(str1);
				int p2 = Integer.parseInt(str2);
				int w = Integer.parseInt(weight);

				// Connections twice for edge on each router
				Network c1 = new Network(p1-1, p2-1, w);
				Network c2 = new Network(p2-1, p1 -1, w);
				network.ad(c1);
				network.add(c2);
			}

			this.size = this.individual();
			scan.close();
		}
		catch (Exception e){
			System.out.println(e);
		}

	}

	private int individual(){
		LinkedList<Integer> number = new LinkedList<Integer>();

		for(Network n : network){
			System.out.println(n.link[0] + " " + n.link[1] + " " + n.link[2]);
		}
	}

}