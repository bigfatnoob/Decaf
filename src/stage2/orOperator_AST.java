/* Generated By:JJTree: Do not edit this line. orOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class orOperator_AST extends SimpleNode {
  public orOperator_AST(int id) {
    super(id);
  }

  public orOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=ec57d2a91529d03fe15614adcfeb752b (do not edit this line) */