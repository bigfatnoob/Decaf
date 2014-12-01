/**
 * 
 */
package stage2;

public class TypeEquality {
	public TypeEquality() {
		super();
	}
	
	public static boolean eq(SimpleNode t1, SimpleNode t2) {
		Unit idT1, idT2;
		if(((t1 instanceof boolean_AST) && (t2 instanceof boolean_AST)) ||
			((t1 instanceof int_AST) && (t2 instanceof int_AST)) ||
			((t1 instanceof char_AST) && (t2 instanceof char_AST))||
			((t1 instanceof voidType_AST) && (t2 instanceof voidType_AST))){
			return true;
		}
		if ((t1 instanceof arrayExpr_AST) && (t2 instanceof arrayExpr_AST)) {
			return (((arrayExpr_AST)t1).size == ((arrayExpr_AST)t2).size);
			// TODO typechecking for objects
		} else if ((t1 instanceof idExpr_AST) && (t2 instanceof idExpr_AST)) {
			idT1 = ((id_AST)t1).getUnit();
			idT2 = ((id_AST)t2).getUnit();
			if (idT1.getName().equals(idT2.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean eq(Type t1, Type t2, Type commonType) {
		if (commonType == null) {
			return t1.equals(t2);
		}
		if (t1.equals(t2)) {
			return t1.equals(commonType);
		}
		return false;
	}
}
