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
	private Modifier modifier;
	private SimpleNode returnType;
	private List<Unit> formalArgs;
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
	
	
}
