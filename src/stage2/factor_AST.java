/* Generated By:JJTree: Do not edit this line. factor_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class factor_AST extends SimpleNode {
  unary_AST lhs;
  Token sign;
  factor_AST rhs;
  String type;
  public factor_AST(int id) {
    super(id);
  }

  public factor_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitFactor(this, data);
  }
}
/* JavaCC - OriginalChecksum=85bcef8420ee3dc5c9773513079bfc85 (do not edit this line) */