/**
 * 
 */
package stage2;

/**
 * @author George
 * Class to keep track of the
 * current scope
 */
public class ScopeFactory {
	private static ScopeFactory scopeFactory;
	
	private ScopeElement rootScope;
	
	private ScopeElement currentScope;
	
	private int blockScopeLevel;
	
	private ScopeFactory(){
		rootScope = new ScopeElement();
		currentScope = rootScope;
	}
	
	public static ScopeElement getStartScopeElement(){
		if (scopeFactory.rootScope.isProgram()){
			return scopeFactory.rootScope;
		}
		for(ScopeElement kidScope:scopeFactory.rootScope.getKids()) {
			if (kidScope.isProgram()) {
				return kidScope;
			}
		}
		return null;
	}
	
	public static ScopeFactory getScopeFactory(){
		if (scopeFactory == null) {
			scopeFactory = new ScopeFactory();
		}
		return scopeFactory;
	}
	
	public ScopeElement getCurrentScope(){
		return currentScope;
	}
	
	public ScopeUnit getCurrentScopeElement() {
		return currentScope.getScopeUnit();
	}
	
	public int getCurrentScopeElementLevel() {
		return currentScope.getLevel();
	}
	
	public int getBlockScopeLevel() {
		return blockScopeLevel;
	}
	
	public ScopeUnit enterNewScope(boolean isProgramStart, int MODE) {
		ScopeUnit newScopeUnit = null;
		if (MODE == 0) {
			ScopeElement newScope = new ScopeElement();
			newScopeUnit = new ScopeUnit();
			if (isProgramStart) {
				newScope.setProgram(true);
			} else {
				newScope.setBlock(true);
			}
			currentScope.addKid(newScope);
			newScope.setScopeUnit(newScopeUnit);
			newScope.setLevel(blockScopeLevel);
			currentScope = newScope;
		} else {
			ScopeElement tempScope = null;
			tempScope = currentScope.getKids().get(currentScope.getKidLevel().getValue());
			currentScope.setKidLevel(currentScope.getKidLevel().getValue() + 1);
			currentScope= tempScope;
			newScopeUnit = currentScope.getScopeUnit();
		}
		
		blockScopeLevel++;
		return newScopeUnit;
	}
	
	
	public ScopeUnit exitLastScope(int MODE) {
		blockScopeLevel--;
		ScopeUnit lastScopeUnit = currentScope.getScopeUnit();
		if (MODE == 1) {
			currentScope.setKidLevel(0);
		}
		currentScope = currentScope.getParent();
		return lastScopeUnit;
	}
	
	public ScopeUnit enterNewScopeForUnit(Unit unit, int MODE) {
		ScopeUnit newScopeUnit = null;
		if (MODE == 0) {
			ScopeElement newScope = new ScopeElement();
			newScopeUnit = new ScopeUnit();
			newScopeUnit.setUnit(unit);
			currentScope.addKid(newScope);
			newScope.setScopeUnit(newScopeUnit);
			newScope.setLevel(blockScopeLevel);
			currentScope = newScope;
		} else {
			ScopeElement tempScope = null;
			tempScope = currentScope.getKids().get(currentScope.getKidLevel().getValue());
			currentScope.setKidLevel(currentScope.getKidLevel().getValue() + 1);
			currentScope= tempScope;
			newScopeUnit = currentScope.getScopeUnit();
		}
		
		blockScopeLevel++;
		return newScopeUnit; 
	}
	
	
}
