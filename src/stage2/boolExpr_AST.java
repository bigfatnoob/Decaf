/* Generated By:JJTree: Do not edit this line. boolExpr_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class boolExpr_AST extends literal_AST {
  public String type = "boolean";
  public Boolean value;
  public boolExpr_AST(int id) {
    super(id);
  }

  public boolExpr_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitBoolExpr(this, data);
  }
  public void cAccept(CodeGenerator codeGenerator, ScopeElement data) {
	  codeGenerator.visitNullExpr(this, data);
  }
}
/* JavaCC - OriginalChecksum=8798bcfa6173387f0b48bef19f8b6871 (do not edit this line) */
