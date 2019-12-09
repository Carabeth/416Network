// Graph
// Written By Carrie E. Adkins and Tyler J. Barrett

import java.util.*;
import java.io.*;

// Weighted directed and connected graph

class Graph {
	// Weighted edge
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

	int V; //verticies
	int E; //edges
	Edge edge[];

	//Graph creation

	Graph(int v, int e){
		V = v;
		E = e;
		edge = new Edge[e];
		for(int i = 0; i < e; ++i){
			edge[i] = new Edge();
		}
	}
}