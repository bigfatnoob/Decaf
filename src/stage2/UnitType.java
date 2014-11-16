/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public enum UnitType {
	METHOD(1, "class"),
	CLASS(2, "method"),
	VARIABLE(3, "variable");
	
	private int value;
	private String name;
	
	private UnitType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
