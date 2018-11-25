package analysis_assignment_2;

//the following Exception should be your resort whenever an error occurs.
@SuppressWarnings("serial")
public class GraphException extends Exception{
	public GraphException( String strMessage ){
		super( strMessage );
	}
} 
