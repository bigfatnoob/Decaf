/* Generated By:JJTree: Do not edit this line. nullExpr_AST.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package stage2;

public
class nullExpr_AST extends literal_AST {
  public String value = null;
  public String type = "null";
  public nullExpr_AST(int id) {
    super(id);
  }

  public nullExpr_AST(Decaf p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(DecafVisitor visitor, ScopeElement data) {
    return visitor.visitNullExpr(this, data);
  }
  
  public void cAccept(CodeGenerator codeGenerator, ScopeElement data) {
	  codeGenerator.visitNullExpr(this, data);
  }

}
/* JavaCC - OriginalChecksum=83605f2da692ae386936504f4b20751f (do not edit this line) */
