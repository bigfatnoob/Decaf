/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class FieldDetails {
	private SimpleNode type;
	private Modifier modifier;
	boolean isArray;
	int arraySize = 0;
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
	
	
}
