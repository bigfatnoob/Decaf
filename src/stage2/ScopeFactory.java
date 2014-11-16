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
	
	public static ScopeFactory getScopeFactory(){
		if (scopeFactory == null) {
			scopeFactory = new ScopeFactory();
		}
		return scopeFactory;
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
	
	public ScopeUnit enterNewScope() {
		ScopeElement newScope = new ScopeElement();
		ScopeUnit newScopeUnit = new ScopeUnit();
		currentScope.addKid(newScope);
		newScope.setScopeUnit(newScopeUnit);
		newScope.setLevel(blockScopeLevel);
		currentScope = newScope;
		
		blockScopeLevel++;
		return newScopeUnit;
	}
	
	
	public ScopeUnit exitLastScope() {
		blockScopeLevel--;
		ScopeUnit lastScopeUnit = currentScope.getScopeUnit();
		currentScope = currentScope.getParent();
		return lastScopeUnit;
	}
}
