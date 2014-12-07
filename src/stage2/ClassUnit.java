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
	
	private SimpleNode type;
	
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
	public SimpleNode getType() {
		return type;
	}
	public void setType(SimpleNode type) {
		this.type = type;
	}
	
	public VariableUnit getVariable(String variableName) {
		for(Unit unit:membersAndMethods) {
			if ((unit instanceof VariableUnit) && (unit.getName().equals(variableName))) {
				return (VariableUnit)unit;
			}
		}
		return null;
	}
	
	public MethodUnit getMethod(String methodName, List<binaryExpression_AST> actualArgs) {
		int argSize = 0;
		if (actualArgs != null){
			argSize = actualArgs.size();
		}
		MethodUnit method = null;
		for(Unit unit:membersAndMethods) {
			if (unit instanceof MethodUnit) {
				method = (MethodUnit) unit;
				if (! unit.getName().equals(methodName)) {
					continue;
				}
				
				List<Unit> formalArgs = method.getFormalArgs();
				int formalArgSize = 0;
				if (formalArgs != null){
					formalArgSize = formalArgs.size();
				}
				if (formalArgSize != argSize) {
					continue;
				} else {
					return method;
				}
				/*boolean validMethod = true;
				for (int i=0; i<formalArgSize; i++){
					VariableUnit formalArg = (VariableUnit)formalArgs.get(i);
					if (formalArg.getType() == null) {
						return method;
					}
					if (! formalArg.getType().jjtGetValue().toString().equals(actualArgs.get(i).typeObj.name)) {
						validMethod = false;
						break;
					}
				}
				if (validMethod) {
					return method;
				}*/
			}
		}
		
		return null;
	}
}
