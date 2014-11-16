/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class Unit {
	/***
	 * Name of the Unit
	 */
	private String name;
	
	/***
	 * Type of the unit -- Could be Class, Method or Variable 
	 */
	private UnitType unitType;
	
	/***
	 * Entry in symbol table for the unit 
	 */
	private TableEntry tableEntry;
	
	/***
	 * denotes how many units below the declaration of
	 * main the unit lies
	 */
	private int scopeLevel;
	
	/***
	 * Unit under which the current element is declared
	 */
	private Unit parentScopeUnit;
	
	/***
	 * Last Unit with the same name above the scope
	 * of this unit.
	 */
	private Unit lastNameUnit;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public TableEntry getTableEntry() {
		return tableEntry;
	}

	public void setTableEntry(TableEntry tableEntry) {
		this.tableEntry = tableEntry;
	}

	public int getScopeLevel() {
		return scopeLevel;
	}

	public void setScopeLevel(int scopeLevel) {
		this.scopeLevel = scopeLevel;
	}

	public Unit getParentScopeUnit() {
		return parentScopeUnit;
	}

	public void setParentScopeUnit(Unit parentScopeUnit) {
		this.parentScopeUnit = parentScopeUnit;
	}

	public Unit getLastNameUnit() {
		return lastNameUnit;
	}

	public void setLastNameUnit(Unit lastNameUnit) {
		this.lastNameUnit = lastNameUnit;
	}
}
