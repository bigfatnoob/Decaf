/* Generated By:JJTree: Do not edit this line. newArray_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

import java.util.List;

public
class newArray_AST extends SimpleNode {
  List<binaryExpression_AST> arrayIndices;
  int size = 0;
  public newArray_AST(int id) {
    super(id);
  }

  public newArray_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitNewArray(this, data);
  }
}
/* JavaCC - OriginalChecksum=413daaf6dd66a8b29ef89caef7d51711 (do not edit this line) */
