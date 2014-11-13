/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class VariableUnit extends Unit {
	private SimpleNode type;
	private Modifier modifier;
	private boolean isArray;
	
	public SimpleNode getType() {
		return type;
	}
	public void setType(SimpleNode type) {
		this.type = type;
	}
	public Modifier getModifier() {
		return modifier;
	}
	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}
	public boolean isArray() {
		return isArray;
	}
	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	
}
