/* Generated By:JJTree: Do not edit this line. multiplyOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class multiplyOperator_AST extends SimpleNode {
  public multiplyOperator_AST(int id) {
    super(id);
  }

  public multiplyOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=16b127d7994ffc1248090a83d7e7c9bf (do not edit this line) */
