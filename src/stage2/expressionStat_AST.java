/* Generated By:JJTree: Do not edit this line. expressionStat_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class expressionStat_AST extends SimpleNode {
  SimpleNode expressionStat;
  public expressionStat_AST(int id) {
    super(id);
  }

  public expressionStat_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitExpressionStat(this, data);
  }
  
  public void cAccept(CodeGenerator codeGenerator, ScopeElement data,
			String brkLbl, String contLbl, String nextLbl) {
		codeGenerator.visitExpressionStat(this, data, brkLbl, contLbl, nextLbl);
	}
}
/* JavaCC - OriginalChecksum=8bb16d28119f00207c7fb6dd9d278765 (do not edit this line) */
