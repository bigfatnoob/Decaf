/* Generated By:JJTree: Do not edit this line. assignOperator_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class assignOperator_AST extends SimpleNode {
  fieldAccess_AST lhs;
  binaryExpression_AST rhs;
  public assignOperator_AST(int id) {
    super(id);
  }

  public assignOperator_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitAssignOperator(this, data);
  }

	public void cAccept(CodeGenerator codeGenerator, ScopeElement data,
			String nextLbl) {
		codeGenerator.visitAssignOperator(this, data, nextLbl);
	}
}
/* JavaCC - OriginalChecksum=115f247d0553c562c34142e3a5fd2416 (do not edit this line) */
