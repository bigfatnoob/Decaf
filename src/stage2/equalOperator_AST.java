/* Generated By:JJTree: Do not edit this line. equalOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class equalOperator_AST extends SimpleNode {
  public equalOperator_AST(int id) {
    super(id);
  }

  public equalOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=fa8314b3722cf5e1f94ef3e217214a2b (do not edit this line) */
