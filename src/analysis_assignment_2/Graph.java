package analysis_assignment_2;

import java.util.*;

public class Graph {
	public Vector<Vertex> vertices;
	public Vector<PathSegment> paths;
	public Vector<Edge> edges;
	
	public Graph() {
		vertices = new Vector<Vertex>();
		paths = new Vector<PathSegment>();
		edges = new Vector<Edge>();
	}
	
	//  returns the name you have given to this graph library [1 pt]
	//	choose whatever name you like!
	public String getLibraryName() {
		return "UndirectedGraph1";
	}
	
	// returns the current version number [1 pt]
	public String getLibraryversion() {
		return "v1.1";
	}
	
	// the following method adds a vertex to the graph [2 pts]
	 public void insertVertex(String strUniqueID, String strData, int nX, int nY) throws GraphException {
		 if(getVertexByUniqueID(strUniqueID) == null)
		 	vertices.add(new Vertex(strUniqueID, strData, nX, nY));
		 else
		 	throw new GraphException("Vertex exists!");
	 }
	
	// inserts an edge between 2 specified vertices [2 pts]
	public void insertEdge(String strVertex1UniqueID, String strVertex2UniqueID, 
			String strEdgeUniqueID, String strEdgeData,int nEdgeCost) throws GraphException{
		Edge edge = new Edge(strEdgeUniqueID, strEdgeData, nEdgeCost);
		Vertex vertex1 = getVertexByUniqueID(strVertex1UniqueID);
		Vertex vertex2 = getVertexByUniqueID(strVertex2UniqueID);
		if (vertex1 == null || vertex2 == null)
			throw new GraphException("One of the vertices does not exist");
		paths.add(new PathSegment(vertex1, edge));
		paths.add(new PathSegment(vertex2, edge));
		edges.add(edge);
	}

	// removes vertex and its incident edges [1 pt]
	public void removeVertex(String strVertexUniqueID) throws GraphException {
		StringBuffer uniqueID = new StringBuffer(strVertexUniqueID);
		
		// Remove the vertex from the list of vertices
		for(Vertex vertex : vertices) {
			if(vertex.getUniqueID().equals(uniqueID)) {
				vertices.remove(vertex);
				break;
			}
		}
		
		// Get all the paths containing this vertex and remove their respective edges
		for(int i=0; i < paths.size(); i++) {
			if(paths.get(i).getVertex().getUniqueID().equals(uniqueID))
				removeEdge(paths.get(i).getEdge().getUniqueID().toString());
		}
	}
	
	// removes an edge from the graph [1 pt]
	public void removeEdge(String strEdgeUniqueID) throws GraphException {
		for(int i=0; i < paths.size(); i++) {
			if(paths.get(i).getEdge().getUniqueID().toString().equals(strEdgeUniqueID))
				paths.remove(i);
		}
	}

	// returns a vector of edges incident to vertex whose
	// id is strVertexUniqueID [1 pt]
	public Vector<Edge> incidentEdges(String strVertexUniqueID) throws GraphException{
		Vector<Edge> edges = new Vector<Edge>();
		for(PathSegment path : paths)
			if(path.getVertex().getUniqueID().toString().equals(strVertexUniqueID))
				edges.add(path.getEdge());
		return edges;
	}

	 

	 // returns all vertices in the graph [1 pt]
	public Vector<Vertex> vertices()throws GraphException {
		return vertices;
	}

	// returns all edges in the graph [1 pt]
		public Vector<Edge> edges() throws GraphException {
			return edges;
		}

	// returns an array of the two end vertices of the passed edge [1 pt]
		public Vertex[] endVertices(String strEdgeUniqueID) throws GraphException {
			ArrayList<Vertex> v = new ArrayList<Vertex>();
			for (PathSegment path : paths) {
				if(path.getEdge().getUniqueID().toString().equals(strEdgeUniqueID))
					v.add(path.getVertex());
			}
			return  new Vertex[]{v.get(0), v.get(1)};
		}
	
	// returns the vertex opposite of another vertex [1 pt]
		public Vertex opposite(String strVertexUniqueID, String strEdgeUniqueID) throws GraphException {
			Vertex[] v = endVertices(strEdgeUniqueID);
			if (v[0].getUniqueID().toString().equals(strVertexUniqueID))
				return v[1];
			else
				return v[0];
		}

	// performs depth first search starting from passed vertex visitor is called on each vertex and edge visited. [12 pts]
		public void dfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException {
			Stack<String> toBeVisited = new Stack<String>();
			toBeVisited.add(strStartVertexUniqueID);
			HashSet<String> visited = new HashSet<>();

			while (!toBeVisited.isEmpty()) {
				String nextVertex = toBeVisited.pop();
				if (visited.contains(nextVertex))
					continue;

				visitor.visit(getVertexByUniqueID(nextVertex));
				visited.add(nextVertex);

				Vector<Edge> incidentEdges = incidentEdges(nextVertex);

				for (Edge e : incidentEdges) {
					visitor.visit(e);
					Vertex v = opposite(nextVertex,e._strUniqueID.toString());
					if (!visited.contains(v._strUniqueID.toString()))
						toBeVisited.push(v._strUniqueID.toString());
				}
			}
		}


	 // performs breadth first search starting from passed vertex visitor is called on each vertex and edge visited. [17 pts]
		public void bfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException {
			HashSet<String> visited = new HashSet<>();
			LinkedList<String> queue = new LinkedList<>();
			queue.addLast(strStartVertexUniqueID);

			while (!queue.isEmpty()) {
				String nextVertex = queue.pollFirst();

				if (visited.contains(nextVertex))
					continue;

				visitor.visit(getVertexByUniqueID(nextVertex));
				visited.add(nextVertex);

				Vector<Edge> incidentEdges = incidentEdges(nextVertex);

				for (Edge e : incidentEdges) {
					visitor.visit(e);
					Vertex v = opposite(nextVertex,e._strUniqueID.toString());
					if (!visited.contains(v._strUniqueID.toString()))
						queue.addLast(v._strUniqueID.toString());
				}
			}
		}
	
	// returns a path between start vertex and end vertex if exists using dfs. [18 pts]
	//	public Vector<PathSegment> pathDFS( String strStartVertexUniqueID, String strEndVertexUniqueID) throws GraphException
	
	// finds the closest pair of vertices using divide and conquer algorithm. Use X and Y attributes in each vertex. [30 pts]
		public Vertex[] closestPair() throws GraphException {

		}
	
	/*			HELPER FUNCTIONS 			*/
	public Vertex getVertexByUniqueID(String uniqueId) {
		for(Vertex vertex : vertices)
			if(vertex.getUniqueID().toString().equals(uniqueId))
				return vertex;
		return null;
	}
	
	public Edge getEdgeByVertex(Vertex vertex) {
		for(PathSegment path : paths) {
			if(path.getVertex().getUniqueID().equals(vertex.getUniqueID()))
				return path.getEdge();
		}
		return null;
	}

	public static void main(String [] args) {

	}

}
