//
//  dvrouter.java
//  
//
//  Created by Carrie E. Adkins an Tyler J. Barrett on 11/30/19.
//


import java.util.*;
import java.lang.*;
import java.io.*;

class dvrouter {

	//Weighted edge

	class Edge {
		int source;
		int destination;
		int weight;

		Edge(){
			source = 0;
			destination = 0;
			weight = 0;
		}
	};

	int V;
	int E;
	Edge edge[];

	//Creates graph
	Graph(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[e];

		for (int i =0; i < e; ++i)
			edge[i] = new Edge();
	}

	// Shortest Distance from source to other nodes

	void BellmanFord(Graph graph, int source){
		
		int V = graph.V;
		int E = graph.E;
		int distance[] = new int[V];

		// Initialize distance from source to all other node as infinity

		for(int i = 0; i < V; ++i)
			distance[i] = Integer.MAX_VALUE;
		distance[source] = 0;

		// shortest path no more than V -1 edges

		for (int j = 1; j < E; ++j) {
			int u = graph.edge[j].source;
			int v = graph.edge[j].destination;
			int weight = graph.edge[j].weight;

			if (destination[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
				distance[v] = distance[u] + weight;
		}

		// check for any cycles with negative weight.

		for ( int j = 0; j < E; ++j) {
			int u = graph.edge[j].source;
			int v = graph.edge[j].destination;

			if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
				System.out.println("Negative weight cycle");
				return;
			}
		}

		printSolution(distance, V);

	}

	// Print function
	void printSolution(int distance[], int V) {
		System.out.println("Destination  NextHop  PathCost");

		for( int i = 0; i < V; ++i) {
			System.out.println((i + 1) + " " + distance[i] + " " + weight);
		}
		
	}
}


