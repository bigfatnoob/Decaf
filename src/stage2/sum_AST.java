/* Generated By:JJTree: Do not edit this line. sum_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class sum_AST extends SimpleNode {
  factor_AST lhs;
  Token sign;
  sum_AST rhs;
  String type;
  public sum_AST(int id) {
    super(id);
  }

  public sum_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitSum(this, data);
  }

public void cAccept(CodeGenerator codeGenerator, ScopeElement data,
		String falsLab) {
	codeGenerator.visitSum(this, data, falsLab);
	
}
}
/* JavaCC - OriginalChecksum=406ce59e43eee5d88c23ddaa96776c01 (do not edit this line) */
