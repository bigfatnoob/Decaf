/**
 * 
 */
package stage2;

/**
 * @author George
 *
 */
public class UnitFactory {
	public static Unit generateUnit(UnitType unitType){
		if (unitType.equals(UnitType.CLASS)) {
			return new ClassUnit();
		} else if (unitType.equals(UnitType.METHOD)) {
			return new MethodUnit();
		} else if (unitType.equals(UnitType.VARIABLE)) {
			return new VariableUnit();
		}
		return null;
	}
}
