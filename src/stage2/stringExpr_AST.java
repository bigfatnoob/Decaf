/* Generated By:JJTree: Do not edit this line. stringExpr_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class stringExpr_AST extends literal_AST {
  public String type = "String";
  public String value;
  public stringExpr_AST(int id) {
    super(id);
  }

  public stringExpr_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitStringExpr(this, data);
  }
}
/* JavaCC - OriginalChecksum=2e316aebf74bcbc6e3e1c60091a64d2f (do not edit this line) */