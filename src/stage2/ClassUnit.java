/**
 * 
 */
package stage2;

import java.util.List;

/**
 * @author George
 *
 */
public class ClassUnit extends Unit {
	/**
	 * public / private / protected / static
	 */
	private Modifier modifier;
	/***
	 * Unit of the superclass
	 */
	private ClassUnit superClass;
	/**
	 * List of all members and methods under the 
	 */
	private List<Unit> membersAndMethods;
	
	public Modifier getModifier() {
		return modifier;
	}
	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}
	public ClassUnit getSuperClass() {
		return superClass;
	}
	public void setSuperClass(ClassUnit superClass) {
		this.superClass = superClass;
	}
	public List<Unit> getMembersAndMethods() {
		return membersAndMethods;
	}
	public void setMembersAndMethods(List<Unit> membersAndMethods) {
		this.membersAndMethods = membersAndMethods;
	}
}
