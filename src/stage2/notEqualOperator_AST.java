/* Generated By:JJTree: Do not edit this line. notEqualOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class notEqualOperator_AST extends SimpleNode {
  public notEqualOperator_AST(int id) {
    super(id);
  }

  public notEqualOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=2f40e06bb8249e3cb6f1dc38ecb27e80 (do not edit this line) */
