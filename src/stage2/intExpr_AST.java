/* Generated By:JJTree: Do not edit this line. intExpr_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class intExpr_AST extends literal_AST {
  public Integer value;
  public String type = "int";
  public intExpr_AST(int id) {
    super(id);
  }

  public intExpr_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitIntExpr(this, data);
  }
}
/* JavaCC - OriginalChecksum=b668919a5949467e48ed688d7e8bae32 (do not edit this line) */