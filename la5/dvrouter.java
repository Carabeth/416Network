//
//  lsrouter.java
//  
//
//  Created by Carrie E. Adkins and Tyler J. Barrett on 11/30/19.
//


import java.util.*;
import java.io.*;

public class dvrouter {
	private int distance [];
	private Set<Integer> settled;
	private PriorityQueue<Node> pq;
	private int Vert;
	List<List<Node> > adj;

	public lsrouter(int Vert) {
		this.Vert = Vert;
		distance = new int[Vert];
		settled = new HashSet<Integer>();
		pq = new PriorityQueue<Node>(Vert, new Node());
	}

	//Dijkastra's Algorithm

	public void dijkstra(List<List<Node > adj, int src) {

		this.adj = adj;

		for(int i = 0; i < Vert; i++){
			distance[i] = Integer.MAX_VALUE;
		}

		//source node

		pq.add(new Node (src, 0));

		//Distance to the source 0

		distance[src] = 0;
		while (settled.size() != Vert){

			//min distance node reomved

			int u = pq.remove().node;

			////adding final distance node

			settled.add(u);

			e_Neighbours(u);
		}
	}

	//Process the neighbours

	private void e_Neighbours(int u) {
		
		int edgeDistance = -1;
		int newDistance = -1;

		//Neighbours of Vert

		for (int i = 0; i < adj.get(u).size(); i++){
			Node v = adj.get(u).get(i);

			//Current node has not been processed

			if(!settled.contains(v.node)) {

				edgeDistance = v.cost;
				newDistance = distance[u] + edgeDistance;

				// New Distance better than cost
				if (newDistance < distance[v.node]){
					distance[v.node] = newDistance;
				}

				// Add current node to queue

				pq.add(new Node(v.node, distance[v.node]));
			}

		}
	}
	public static void main(String arg[]) {

	}
}
	

	// Node in graph

	class Node implements Comparator<Node> {
		public in node;
		public int cost;

		public Node(){

		}

		public Node(int node, int cost){
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compare(Node node1, Node node2){

			if(node1.cost < node2.cost)
				return -1;
			if(node1.cost > node2.cost)
				return 1;
			return 0;
		}
	}
