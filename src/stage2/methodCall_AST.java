/* Generated By:JJTree: Do not edit this line. methodCall_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class methodCall_AST extends SimpleNode {
  methodCall1_AST m1;
  methodCall2_AST m2;
  public methodCall_AST(int id) {
    super(id);
  }

  public methodCall_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitMethodCall(this, data);
  }
}
/* JavaCC - OriginalChecksum=19faec5d748559445af56c0159a273ee (do not edit this line) */