/* Generated By:JJTree: Do not edit this line. greaterEqualOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class greaterEqualOperator_AST extends SimpleNode {
  public greaterEqualOperator_AST(int id) {
    super(id);
  }

  public greaterEqualOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=551961a59b20e74ad60690785ffa6859 (do not edit this line) */
