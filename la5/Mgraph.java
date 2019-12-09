public class Mgraph {
	private class Vertex
	{
		private String name;
		private ArrayList<Edge> neighbors;
		public Vertex(String aName)
		{
			this.name = aName;
			this.neighbors = new ArrayList<Edge>();
		}
	}
	private class Edge
	{
		private Vertex toVertex;
		private Double weight;
		public Edge(Vertex aVert, double aW)
		{
			toVertex = aVert;
			weight = aW;
		}
	}
	private Vertex origin;
	private ArrayList<Vertex> verts;
	public Graph()
	{
		origin = null;
		verts = new ArrayList<Vertex>();
	}
	public void addVertex(String aName)
	{
		if(vertexContained(aName))
			return;
		Vertex v = new Vertex(aName);
		verts.add(v);
		if(origin == null)
			origin = v;
	}
	public void addEdge(String fromVert, String toVert, double weight)
	{
		Vertex v1 = getVert(fromVert);
		Vertex v2 = getVert(toVert);
		if(v1 == null || v2 == null)
			return;
		v1.neighbors.add(new Edge(v2,weight));
	}
	private boolean vertexContained(String aName)
	{
		for(Vertex vert : verts)
			if(vert.name.equals(aName))
				return true;
		return false;
	}
	private Vertex getVert(String aName)
	{
		for(Vertex vert:verts)
			if(vert.name.equals(aName))
				return vert;
		return null;
	}
