/**
 * 
 */
package stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George
 *
 */
public class SymbolTable {
	private Map<String, List<TableEntry>> table;
	private static SymbolTable symbolTable;
	private final static List<String> builtinTypes = new ArrayList<String>(Arrays.asList("Object","String","IO"));
	
	
	private SymbolTable(){
		this.table = new HashMap<String, List<TableEntry>>();
	}
	
	public static SymbolTable getSymbolTable(){
		if (symbolTable == null) {
			symbolTable = new SymbolTable();
		}
		return symbolTable;
	}
	
	public Map<String, List<TableEntry>> getTable(){
		return table;
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
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		String name = tableEntry.getName();
		List<TableEntry> tableEntries = table.get(name);
		
		if (tableEntries == null) {
			tableEntries = new ArrayList<TableEntry>();
			tableEntries.add(tableEntry);
			table.put(name, tableEntries);
		} else {
			TableEntry existingTableEntry = null;
			
			for (TableEntry tblEntry: tableEntries) {
				if (tblEntry.getName().equals(name)) {
					if((tblEntry.getUnit().equals(scopeFactory.getCurrentScopeElement().getUnit())) && 
							(tblEntry.getUnit().getUnitType().equals(tableEntry.getUnit().getUnitType()))) {
						existingTableEntry = tblEntry;
						break;
					}
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
	
	public Unit lookUp(String name, UnitType type, IntegerMuted scopeLevel) {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		List<TableEntry> tableEntries = table.get(name);
		if ((tableEntries ==  null) || (tableEntries.size() == 0))
			return null;
		
		if (!type.equals(UnitType.METHOD) && builtinTypes.contains(name)) {
			if (type.equals(UnitType.CLASS)) {
				return tableEntries.get(0).getUnit();
			} else {
				ClassUnit cUnit = (ClassUnit)tableEntries.get(0).getUnit();
				VariableUnit vUnit = new VariableUnit();
				vUnit.setModifier(cUnit.getModifier());
				idExpr_AST idExpr = new idExpr_AST(0);
				idExpr.typeObj = new Type(cUnit.getName(), false, 0, true);
				vUnit.setType(idExpr);
				return vUnit;
			}
		}
		
		TableEntry tableEntry = null;
		for (TableEntry tblEntry: tableEntries) {
			if (tblEntry.getName().equals(name)) {
				if(!type.equals(UnitType.METHOD)) {
					if(tblEntry.getUnit().getScopeLevel() <= 1) {
						// FOr Class declarations
						tableEntry = tblEntry;
						break;
					}
				} else {
					if(tblEntry.getUnit().getScopeLevel() <= 1 && type.equals(tblEntry.getUnit().getUnitType())) {
						// FOr Class declarations
						tableEntry = tblEntry;
						break;
					}
				}
				
				try{
					if(scopeFactory.getCurrentScope().isBlock() && tblEntry.getUnit().getScopeLevel() < scopeFactory.getBlockScopeLevel()) {
						if (type.equals(tblEntry.getUnit().getUnitType())) {
							tableEntry = tblEntry;
							break;
						}
					}else if((tblEntry.getUnit().getScopeLevel() <= (scopeFactory.getCurrentScopeElement().getUnit().getScopeLevel()+1))||
							(tblEntry.getUnit().equals(scopeFactory.getCurrentScopeElement().getUnit()))) {
						if (type.equals(tblEntry.getUnit().getUnitType())) {
							tableEntry = tblEntry;
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (tableEntry == null){
			return null;
		}
		
		Unit currentUnit = tableEntry.getUnit();
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
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		Unit unit = lookUp(name, type, currentScope);
		if (unit != null && currentScope.getValue().equals(1) && unit.getParentScopeUnit().equals(scopeFactory.getCurrentScope().getScopeUnit().getUnit()))
			throw new RuntimeException(type.getName() + " " + name + " already declared");
		
		TableEntry tableEntry = new TableEntry();
		unit = UnitFactory.generateUnit(type);
		unit.setName(name);
		
		ScopeUnit currentScopeUnit = scopeFactory.getCurrentScopeElement();
		Unit currentUnit = currentScopeUnit.getUnit();
		
		//currentScopeUnit.setUnit(unit);	
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
	
	public void enterScope(boolean isProgramStart, int MODE) {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		scopeFactory.enterNewScope(isProgramStart, MODE);
	}
	
	public void exitScope(int MODE) {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		ScopeUnit scopeUnit = scopeFactory.exitLastScope(MODE);
		Unit lastUnit = scopeUnit.getUnit();
		
		/*while (lastUnit != null) {
			resetTableEntries(lastUnit);
			lastUnit = lastUnit.getParentScopeUnit();
		}*/
	}
	
	public void enterScopeForUnit(Unit unit, int MODE) {
		ScopeFactory scopeFactory = ScopeFactory.getScopeFactory();
		scopeFactory.enterNewScopeForUnit(unit, MODE);
	}
}
