/* Generated By:JJTree: Do not edit this line. notOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class notOperator_AST extends SimpleNode {
  public notOperator_AST(int id) {
    super(id);
  }

  public notOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c94f18021200c44dc6d7f967e361435c (do not edit this line) */
