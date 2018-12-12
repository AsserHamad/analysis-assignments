package analysis_assignment_2;

public class Grading {

    public int runTestCase1() throws GraphException {
        int nMark=0;
        Graph g = new Graph( );
        GradingVisitor gVisitor = new GradingVisitor( );
        g.insertVertex("1", "1" ,0,0);
        g.insertVertex("2", "2" ,0,1);
        g.insertVertex("3", "3" ,1,0);
        g.insertVertex("4", "4" ,1,1);
        g.insertVertex("5", "5", 1,2);

        g.insertEdge("1","4","88","88",5);
        g.insertEdge("1","2","2","2", 2);
        g.insertEdge("2", "3","14","14",14);
        g.insertEdge("2", "4","99","99",5);
        g.insertEdge("2", "5","4","4",4);
        g.insertEdge("4", "5", "58", "58", 58);
        g.insertEdge("3", "5", "34", "34", 34);


        System.out.println(g.pathDFS("1", "5"));

        System.out.println(gVisitor.getResult());
        if(gVisitor.getResult().equalsIgnoreCase("blah"))
            nMark+= 12;

        return nMark;
    }

    public static void main( String[] args ) throws GraphException {
        int nTotalMark=0;
        Grading grading = new Grading();
        nTotalMark += grading.runTestCase1();
    }
}
