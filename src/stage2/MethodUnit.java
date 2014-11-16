/**
 * 
 */
package stage2;

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
	
	private boolean isFormalArgument;
	
	private boolean isLocalVariable;
	
	private boolean isConstructor;
	
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
	
	
}
