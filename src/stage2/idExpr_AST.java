/* Generated By:JJTree: Do not edit this line. idExpr_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class idExpr_AST extends SimpleNode {
  public String name;
  public VariableUnit variableUnit;
  public idExpr_AST(int id) {
    super(id);
  }

  public idExpr_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitIdExpr(this, data);
  }
}
/* JavaCC - OriginalChecksum=7d69da98edf3c4ace9fcec2bb02e3649 (do not edit this line) */
