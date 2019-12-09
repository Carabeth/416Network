//
//  dvrouter.java
//  
//
//  Created by Carrie E. Adkins an Tyler J. Barrett on 11/30/19.
//


import java.util.*;
import java.lang.*;
import java.io.*;

class lsrouter {
	
	//Main method
	public static void main(String[] args) {
		String result = "";
		Input networks = new Input();
		Input revisions = new Input();
		Messages msgs = new Messages();
		networks.input("net1.txt");
		revisions.input("net1_changes.txt");
		msgs.input("net1_messages.txt");
		int V = networks.size;
		int E = network.topology.size();
		int[][] mappings = new int [V][V];
		String[][] jumps = new String [V][V];
		Graph routingNetwork = createNetwork(networks,E,V,mappings,jumps);
		result += routerTable(mappings, V, jumps);
		result += sendingMsg(jumps, messages);
		
		
		for (int i = 0; i < revisions.topology.size(); i = i + 2) {
			
			boolean notLocated = true;
			
			for (int j = 0; j < E; ++j) {
				
				if (revisions.topology.get(i).link[0] == networks.topology.get(j).link[0] && revisions.topology.get(i).link[1] == networks.topology.get(j).link[0]) {
					
					networks.topology.get(j).link[2] = revisions.topology.get(i).link[2];
					
					for (int k = 0; k < E; ++k) {
						
						if (revisions.topology.get(i).link[0] == networks.topology.get(k).link[1] && revisions.topology.get(i).link[1] == networks.topology.get(k).link[0] ) {
							
							networks.topology.get(k).link[2] = changes.topology.get(i).link[2];
							notLocated = false;
						}
					}
				}
			}
			
			if(notLocated == true) {
				networks.topology.add(changes.topology.get(i));
				networks.topology.add(changes.topology.get(i));
				
				E = networks.topology.size()
				routingNetwork = createNetwork(networks,E,V,mappings,jumps);
			}
			
			result += routingTable(mappings, V, jumps);
			
			result += sendingMsg(jumps, msgs);
		}
		
		Output(result);
		
	}


	// Shortest Distance from source to other nodes

	public static int[] BellmanFord(Graph graph, String [][] hops, int source){
		
		int V = graph.V;
		int E = graph.E;
		int distance[] = new int[V];

		// Initialize distance from source to all other node as infinity

		for(int i = 0; i < V; ++i)
			distance[i] = Integer.MAX_VALUE;
		distance[source] = 0;

		// shortest path no more than V -1 edges
		for( int i =1; i <V; ++i){
		for (int j = 1; j < E; ++j) {
			int u = graph.edge[j].source;
			int v = graph.edge[j].destination;
			int weight = graph.edge[j].weight;

			if (destination[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v] && weight >= 0){
				distance[v] = distance[u] + weight;
				hops[src][v]=hops[source][u] + (v+1) +" ";
			}
		}
	}
	return distance;
}

public static void hopsClear(String [][]hops, int V) {
	for(int i = 0; i<V; ++i) {
		for( int j = 0; j <V; ++j){
			hops[i][j] ="";
		}
	}
}

public static String routerTable(int [][] table, int V, String [][]hops){
	String ret = "";
	for(int i = 0; i < V; ++i){
		ret += "Forwarding table for router " + (i+1) + "\n";
		for(int j = 0; j <V; ++j){
			if (i == j){
				ret += (i+1) +" "+(j+1)+" "+table[i][j]+"\n";
			}
			else {
				ret += (i+1) +" "+ hops[i][j].substring(0,1)+" "table[i][j]+"\n";
			}
		} 
	}
	return ret;
}

public static void printTable(int [][]table, int V){
	
	System.out.println("Forwardding table");

	for(int i = 0; i < V; ++i){
		for(int j = 0; j < V; ++j){
			System.out.print(table[i][j] + " ");
		}

		System.out.println();
	}

}

public static String sendingMsg(String [][]hops, Message m){

	String ret = "";
	for(int i = 0; i < m.messages.size(); ++i){
		ret += "From " + (m.messages.get(i).out+1) + " to "+(m.messages.get(i).in+1)+" Hops: "+ hops[m.messages.get(i).out][m.messages.get(i).in]+" Message: "+ m.messages.get(i).message + "\n";
	}
	return ret;
}

public static void Output(String output) {

	try {
		FileWriter name = new FileWriter("output.txt");
		PrintWriter print = new PrintWriter(name);
		print.print(output);
		print.close();
	}
	catch (Exception e){
		System.out.println(e);
	}
}
}

