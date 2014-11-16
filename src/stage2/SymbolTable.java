/**
 * 
 */
package stage2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George
 *
 */
public class SymbolTable {
	private Map<String, List<TableEntry>> table;
	
	public SymbolTable(){
		table = new HashMap<String, List<TableEntry>>();
	}
	
	/***
	 * Return the most deep Unit with a
	 * given name.
	 * @param name
	 * @return
	 */
	private Unit getLastUnit(String name) {
		List<TableEntry> tableEntries = table.get(name);
		if (tableEntries == null)
			return null;
		
		TableEntry lastEntry = null;
		for (TableEntry tableEntry: tableEntries) {
			if (tableEntry.getName().equals(name)) {
				lastEntry = tableEntry;
				break;
			}
		}
		
		if (lastEntry == null)
			return null;
		
		Unit lastUnit = lastEntry.getUnit();
		while(lastUnit != null) {
			if (lastUnit.getLastNameUnit() == null) {
				return lastUnit;
			}
			lastUnit = lastUnit.getLastNameUnit();
		}
		return lastUnit;
	}	
	
	/**
	 * Method to update a record in the symbol table
	 * @param tableEntry
	 */
	private void updateTable(TableEntry tableEntry) {
		String name = tableEntry.getName();
		List<TableEntry> tableEntries = table.get(name);
		
		if (tableEntries == null) {
			tableEntries = new ArrayList<TableEntry>();
			tableEntries.add(tableEntry);
			table.put(name, tableEntries);
		}
		
		TableEntry existingTableEntry = null;
		for (TableEntry tblEntry: tableEntries) {
			if (!tblEntry.getName().equals(name)) {
				existingTableEntry = tblEntry;
				break;
			}
		}
		
		if (existingTableEntry != null) {
			Unit unit = tableEntry.getUnit();
			existingTableEntry.setUnit(unit);
			unit.setTableEntry(existingTableEntry);
		} else {
			tableEntries.add(tableEntry);
		}
	}
	
	/***
	 * Method to clear symbol table entry for
	 * a name
	 * @param unit
	 */
	private void resetTableEntries(Unit unit) {
		UnitType unitType = unit.getUnitType();
		Unit lastUnit = unit.getLastNameUnit();
		
		if (lastUnit == null) {
			TableEntry tableEntry = unit.getTableEntry();
			List<TableEntry> tableEntries = table.get(tableEntry.getName());
			tableEntries.remove(tableEntry);
		}
		
		while (lastUnit != null) {
			if (lastUnit.getUnitType().equals(unitType)) {
				TableEntry tblEntry = lastUnit.getTableEntry();
				tblEntry.setUnit(lastUnit);
				lastUnit.setTableEntry(tblEntry);
				break;
			}
		}
	}
	
	public Unit find(String name, UnitType type, IntegerMuted scopeLevel) {
		List<TableEntry> tableEntries = table.get(name);
		if ((tableEntries ==  null) || (tableEntries.size() == 0))
			return null;
		
		TableEntry tableEntry = null;
		for (TableEntry tblEntry: tableEntries) {
			if (tblEntry.getName().equals(name)) {
				tableEntry = tblEntry;
				break;
			}
		}
		
		Unit currentUnit = tableEntry.getUnit();
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		while (currentUnit != null) {
			if (currentUnit.getUnitType().equals(type)) {
				scopeLevel.setValue(0);
				if (scopeFactory.getCurrentScopeElementLevel() == currentUnit.getScopeLevel())
					scopeLevel.setValue(1);
				return currentUnit;
			}
			currentUnit = currentUnit.getLastNameUnit();
		}
		return null;
	}
	
	public Unit add(String name, UnitType type) {
		IntegerMuted currentScope = new IntegerMuted(-1);
		
		Unit unit = find(name, type, currentScope);
		if (unit != null && currentScope.getValue().equals(1))
			throw new RuntimeException(type.getName() + " " + name + " already declared");
		
		TableEntry tableEntry = new TableEntry();
		unit = UnitFactory.generateUnit(type);
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		ScopeUnit currentScopeUnit = scopeFactory.getCurrentScopeElement();
		Unit currentUnit = currentScopeUnit.getUnit();
		
		currentScopeUnit.setUnit(unit);
		Unit lastNameUnit = getLastUnit(name);
		
		unit.setLastNameUnit(lastNameUnit);
		unit.setParentScopeUnit(currentUnit);
		unit.setUnitType(type);
		unit.setScopeLevel(scopeFactory.getCurrentScopeElementLevel());
		unit.setTableEntry(tableEntry);
		
		tableEntry.setName(name);
		tableEntry.setUnit(unit);
		
		updateTable(tableEntry);
		
		return unit;
	}
	
	public void enterScope() {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		scopeFactory.enterNewScope();
	}
	
	public void exitScope() {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		ScopeUnit scopeUnit = scopeFactory.exitLastScope();
		Unit lastUnit = scopeUnit.getUnit();
		
		while (lastUnit != null) {
			resetTableEntries(lastUnit);
			lastUnit = lastUnit.getParentScopeUnit();
		}
	}
}
