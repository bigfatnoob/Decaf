package stage2;


public class DecafError {
	int numErrors;
	
	DecafError(){
		
	}
	
	public static String errorPos(Position p){
		return "(L: " + p.startLine + 
			    ", Col: " + p.startCol  +
			    ") -- (L: " + p.endLine + 
			    ", Col: " + p.endCol  +
			    ")"; 
	}
	
	public void error(String s, Position p) {
		System.out.println("Error found at location "+
				   errorPos(p) + ":\n"+s);
	}
	
	public boolean haveErrors() {
		return (numErrors>0);
    }
	
}
