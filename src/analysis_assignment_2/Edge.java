package analysis_assignment_2;

public class Edge{
	protected StringBuffer _strUniqueID, 	// a unique id identifying edge
							_strData; 		// data associated with this edge. Data could be name of edge or any meaningful property for an edge.
	protected int _nEdgeCost; 				// cost of traversing this edge
	 
	public Edge(String _strUniqueID, String _strData, int _nEdgeCost) {
		this._strUniqueID = new StringBuffer(_strUniqueID);
		this._strData = new StringBuffer(_strData);
		this._nEdgeCost = _nEdgeCost;
	}
	
	public StringBuffer getUniqueID( ){
		return _strUniqueID;
	}

	public StringBuffer getData( ){
		return _strData;
	}
	
	public int getCost( ){
		return _nEdgeCost;
	}
} 
