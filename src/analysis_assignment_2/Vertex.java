package analysis_assignment_2;

public class Vertex {
	protected StringBuffer _strUniqueID, 	//a unique id identifying vertex
							_strData; 		//data associated with vertex
	protected int _nX,_nY; 					//Coordinates of vertex on some /map. Assume 0,0 is bottom left.
	
	public Vertex(String _strUniqueID, String _strData, int _nX, int _nY) {
		this._strUniqueID = new StringBuffer(_strUniqueID);
		this._strData = new StringBuffer(_strData);
		this._nX = _nX;
		this._nY = _nY;
	}
	
	public StringBuffer getUniqueID( ){
		return _strUniqueID;
	}

	public StringBuffer getData( ){
		return _strData;
	}
	
	public int getX( ){
		return _nX;
	}
	
	public int getY( ){
		return _nY;
	}
}
