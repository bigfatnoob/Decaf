/* Generated By:JJTree: Do not edit this line. methodPrime_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

import java.util.List;

public
class methodPrime_AST extends SimpleNode {
  String name;
  methodPrime_AST mPrime;
  binaryExpression_AST arrayIndex;
  ScopeElement callerScope;
  List<binaryExpression_AST> actualArgs;
  boolean isMethod = true;
  public methodPrime_AST(int id) {
    super(id);
  }

  public methodPrime_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitMethodPrime(this, data);
  }
}
/* JavaCC - OriginalChecksum=ffe102dcc548a2eb217a804c5a80e803 (do not edit this line) */
