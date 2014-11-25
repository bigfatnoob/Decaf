package stage2;

public class Position {
public int startLine, startCol, endLine, endCol;
    
    public Position(int sL, int sC, int eL, int eC) {
		startLine = sL;
		startCol = sC;
		endLine = eL;
		endCol = eC;
    }
    
    public Position merge(Position Q) {
		if (Q.startLine > startLine ||
		    (Q.startLine==startLine && Q.startCol > startCol)) 
		    return Q.merge(this);
		else
		    return new Position(startLine, startCol, Q.endLine,
					Q.endCol);
    }

    public void setPos(Position Q) {
		startLine = Q.startLine;
		startCol = Q.startCol;
		endLine = Q.endLine;
		endCol = Q.endCol;
    }
}
