/* Generated By:JJTree: Do not edit this line. primaryMethodParent_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class primaryMethodParent_AST extends SimpleNode {
  public primaryMethodParent_AST(int id) {
    super(id);
  }

  public primaryMethodParent_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=24516c09a4b92baf8b531a483fc3624d (do not edit this line) */
