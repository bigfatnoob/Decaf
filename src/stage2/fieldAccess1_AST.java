/* Generated By:JJTree: Do not edit this line. fieldAccess1_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

import java.util.List;

public
class fieldAccess1_AST extends SimpleNode {
  SimpleNode primaryFieldAccess;
  String name;
  List<binaryExpression_AST> arrayIndices;
  public fieldAccess1_AST(int id) {
    super(id);
  }

  public fieldAccess1_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitFieldAccess1(this, data);
  }
}
/* JavaCC - OriginalChecksum=1b4a5cee9eba94f21f44b52625974189 (do not edit this line) */
