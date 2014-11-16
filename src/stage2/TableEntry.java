/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class TableEntry {
	
	private String name;
	
	private Unit unit;

	public TableEntry(){}
	
	public TableEntry(String name, Unit unit) {
		this.name = name;
		this.unit = unit;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
