/**
 * 
 */
package stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George
 *
 */
public class MethodUnit extends Unit {
	/**
	 * public / private / protected / static
	 */
	private Modifier modifier;
	/**
	 * Think kinda obvious 
	 */
	private SimpleNode returnType;
	/***
	 * Think kinda obvious
	 */
	private List<Unit> formalArgs;
	/***
	 * All Statements in the function
	 */
	private List<SimpleNode> statements;
	
	ClassUnit classUnit;
	
	List<Unit> localVariables = new ArrayList<Unit>();
	
	private boolean isFormalArgument;
	
	private boolean isLocalVariable;
	
	private boolean isConstructor;
	
	private block_AST methodBlock;
	
	public Modifier getModifier() {
		return modifier;
	}
	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}
	public SimpleNode getReturnType() {
		return returnType;
	}
	public void setReturnType(SimpleNode returnType) {
		this.returnType = returnType;
	}
	public List<Unit> getFormalArgs() {
		return formalArgs;
	}
	public void setFormalArgs(List<Unit> formalArgs) {
		localVariables.addAll(formalArgs);
		this.formalArgs = formalArgs;
	}
	public List<SimpleNode> getStatements() {
		return statements;
	}
	public void setStatements(List<SimpleNode> statements) {
		this.statements = statements;
	}
	public boolean isConstructor() {
		return isConstructor;
	}
	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}
	public boolean isFormalArgument() {
		return isFormalArgument;
	}
	public void setFormalArgument(boolean isFormalArgument) {
		this.isFormalArgument = isFormalArgument;
	}
	public boolean isLocalVariable() {
		return isLocalVariable;
	}
	public void setLocalVariable(boolean isLocalVariable) {
		this.isLocalVariable = isLocalVariable;
	}
	public block_AST getMethodBlock() {
		return methodBlock;
	}
	public void setMethodBlock(block_AST methodBlock) {
		this.methodBlock = methodBlock;
	}
	
	public boolean validateMethodCall(List<binaryExpression_AST> actualArgs) {
		int argSize = 0;
		if (actualArgs != null){
			argSize = actualArgs.size();
		}
		int formalArgSize = 0;
		if (formalArgs != null){
			formalArgSize = formalArgs.size();
		}
		if (formalArgSize != argSize) {
			return false;
		}
		if (formalArgSize == 0) {
			return true;
		}
		boolean validMethod = true;
		for (int i=0; i<formalArgSize; i++){
			VariableUnit formalArg = (VariableUnit)formalArgs.get(i);
			try {
				if (! formalArg.getType().typeObj.name.equals(actualArgs.get(i).typeObj.name)) {
					validMethod = false;
					break;
				}
			} catch (Exception e) {
				return true;
			}
			
		}
		return validMethod;
	}

}
