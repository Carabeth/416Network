// Java program to implement Graph 
// with the help of Generics 
import java.util.*; 
import java.io.*; 
  
// A class to represent a connected, directed and weighted graph 
class Graph { 
    // A class to represent a weighted edge in graph 
    class Edge { 
        int src, dest, weight; 
        Edge() 
        { 
            src = dest = weight = 0; 
        } 
    }; 
    int V, E; 
    Edge edge[]; 
  
    // Creates a graph with V vertices and E edges 
    Graph(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[e]; 
        for (int i = 0; i < e; ++i) 
            edge[i] = new Edge(); 
    } 
  
}
  