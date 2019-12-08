
import java.io.*;
//written by Xavier
public class lsrouter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String output = "";
		ReadIn network = new ReadIn();
		ReadIn changes = new ReadIn();
		Messages messages = new Messages();
		network.readIn("net1.txt");
		changes.readIn("net1_changes.txt");
		messages.readIn("net1_messages.txt");
		int V = network.size;
		int E = network.topology.size();
		int [][] table = new int[V][V];
		String [][]hops = new String [V][V];
		Graph rNetwork = createNetwork(network,E,V,table,hops);
		output+=rTable(table, V,hops);
		//printTable(table, V);
		output+=sendMessage(hops, messages);
		for(int i =0;i<changes.topology.size();i+=2)
		{
			boolean notFound = true;
			for(int j=0; j<E;j++)
			{
				if(changes.topology.get(i).link[0] == network.topology.get(j).link[0] && changes.topology.get(i).link[1] == network.topology.get(j).link[1])
				{
					network.topology.get(j).link[2] = changes.topology.get(i).link[2];
					for(int h=0; h<E;h++)
					{
						if(changes.topology.get(i).link[0] == network.topology.get(h).link[1] && changes.topology.get(i).link[1] == network.topology.get(h).link[0] )
						{
							network.topology.get(h).link[2] = changes.topology.get(i).link[2];
							notFound = false;
						}
					}
				}
			}
			
			if(notFound)//== true)
			{
				network.topology.add(changes.topology.get(i));
				network.topology.add(changes.topology.get(i));
				
				E = network.topology.size();
				rNetwork = createNetwork(network,E,V,table,hops);
			}
			//network.print();
			output+=rTable(table, V,hops);
			//printTable(table,V);
			output+=sendMessage(hops, messages);
		}
		Output(output);
		
	}
	
	
	public static Graph createNetwork(ReadIn network,int E, int V, int[][]table, String[][]hops)
	{
		Graph rNetwork= new Graph(V,E);
		hopsClear(hops, V);
		//network.print();
		for(int i=0; i < network.topology.size();i++)
		{
			rNetwork.edge[i].src = network.topology.get(i).link[0]; 
			rNetwork.edge[i].dest = network.topology.get(i).link[1];
			rNetwork.edge[i].weight = network.topology.get(i).link[2];
		}
		for(int i=0;i< V;i++)
		{
			table[i] = BellmanFord(rNetwork,hops,i);
		}
		return rNetwork;
	}
	public static int[] BellmanFord(Graph graph, String [][] hops, int src) 
    { 
        int V = graph.V, E = graph.E; 
        int dist[] = new int[V]; 
  
        // Step 1: Initialize distances from src to all other 
        // vertices as INFINITE 
        for (int i = 0; i < V; ++i) 
            dist[i] = Integer.MAX_VALUE; 
        dist[src] = 0; 
  
        // Step 2: Relax all edges |V| - 1 times. A simple 
        // shortest path from src to any other vertex can 
        // have at-most |V| - 1 edges 
        for (int i = 1; i < V; ++i) { 
            for (int j = 0; j < E; ++j) { 
                int u = graph.edge[j].src; 
                int v = graph.edge[j].dest; 
                int weight = graph.edge[j].weight; 
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v] && weight >= 0) 
                {
                    dist[v] = dist[u] + weight; 
                    hops[src][v]= hops[src][u] +(v+1) +" ";
                }
                	
            } 
        } 
        return dist;
    }
	public static void hopsClear(String [][]hops, int V)
	{
		for(int i = 0; i<V; i++)
		{
			for (int j = 0; j<V; j++)
			{
				hops[i][j] = "";
			}
		}
	}
	public static String rTable(int [][] table, int V, String [][]hops)
	{
		String ret = "";
		for(int i=0;i<V;i++)
		{
			ret += "Forwarding table for router "+ (i+1) +"\n";
			for(int j=0;j<V;j++)
			{
				if (i==j)
				{
					ret += (i+1) +" "+(j+1)+" "+ table[i][j]+"\n";
				}
				else
				{
					ret += (i+1) +" "+hops[i][j].substring(0,1)+" "+ table[i][j]+"\n";
				}
			}
		}
		return ret;
	}
	public static void printTable(int [][]table, int V)
	{
		System.out.println("Forwarding table");
		for(int i = 0; i<V; i++)
		{
			for (int j = 0; j<V; j++)
			{
				System.out.print(table[i][j] +" ");
			}
			System.out.println();
		}
	}
	
	public static String sendMessage(String [][]hops, Messages m)
	{
		String ret ="";
		for(int i=0; i<m.messages.size();i++)
		{
			ret+="From "+(m.messages.get(i).sender+1) +" to "+(m.messages.get(i).reciever+1)+ " Hops: "+ hops[m.messages.get(i).sender][m.messages.get(i).reciever]+" Message: "+m.messages.get(i).message+"\n";
		}
		return ret;
	}
	public static void Output(String output)
	{
		try
		{
			FileWriter file = new FileWriter("output.txt");
			PrintWriter print = new PrintWriter(file);
			print.print(output);
			print.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
