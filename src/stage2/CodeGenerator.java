package stage2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class CodeGenerator {
	BufferedWriter output;
    DecafError err;
    TypeEquality check = new TypeEquality();
    private final static int MODE = 1;
	private static ScopeFactory sf = ScopeFactory.getScopeFactory();
	public static SymbolTable SYMBOL_TABLE = SymbolTable.getSymbolTable();
	private final static List<String> basicTypes = new ArrayList<String>(Arrays.asList("int","boolean","char"));
	private final static List<String> builtinTypes = new ArrayList<String>(Arrays.asList("Object","String","IO"));
	
    

    public CodeGenerator() {
		err = new DecafError();
    }
   


    /* ----------  Label generation ------------- */

    int LabCounter=0;
    private String mkLabel(String s) {
		String result = s + LabCounter;
		LabCounter++;
		return result;
    }


    /* ----------------------------------------- */


    private void exitSys() {
    	throw new java.lang.RuntimeException("Compiler Error in codeGen");
    }	
    
    /*****************************************/
    
    void emit(String s)  {
    	try {
    		if((s==null)|| (s.trim().length() == 0)){
        		output.newLine();
        		return;
        	}
    		if (s.charAt(0) == '.'){
    		    output.write(s);
    		}else if (s.charAt(s.length()-1) == ':') { /* a label */
    		    output.write(s);
    		} else{
    			output.write("\t"+s); /* use a tab for printing it in nicely */
    		}
    		output.newLine();
    	}catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    private String getType(String javaType){
    	if (javaType.equals("void"))
    		return "V";
    	if (javaType.equals("int"))
    		return "I";
    	if (javaType.equals("char"))
    		return "C";
    	if (javaType.equals("boolean"))
    		return "Z";
    	if (javaType.equals("Object"))
    		return "java/lang/Object";
    	if (javaType.equals("String"))
    		return "java/lang/String";
    	return javaType;
    }
    
    
    private String getRetType(Type vType){
    	String argStr = "";
    	if (vType.isArray){
			for (int i=0; i<vType.arraySize;i++){
				argStr += "[";
			}
		}
		if (vType.isClass){
			argStr += "L";
		}
		argStr += getType(vType.name);
		return argStr;
    }
    
    private String getInst(String type) {
    	if (type.equals("char")) {
    		return "c";
    	} else if(type.equals("int")){
    		return "i";
    	} else if(type.equals("boolean")){
    		return "z";
    	}
    	return "a";
    }
    
    private ScopeElement getClassScope(String className){
		ScopeElement rootScopeElement = ScopeFactory.getStartScopeElement();
		for(ScopeElement kidScope: rootScopeElement.getKids()) {
			if(kidScope.getScopeUnit().getUnit().getName().equals(className)){
				return kidScope;
			}
		}
		return null;
	}
    
    
    public void visitStart(start_AST node, ScopeElement data) throws IOException{
    	Iterator<Unit> classes = node.getClasses().listIterator();
		ClassUnit classUnit = null;
		data.setKidLevel(3);
		FileWriter fstream = null; 
		while (classes.hasNext()) {
			classUnit = (ClassUnit)classes.next();
			sf.enterNewScopeForUnit(classUnit, MODE);
			
			try  
			{
				 File file = new File("X:/NCSU/CSC-512 Compilers/Project/Repos/Decaf/src/stage2/Jasmin",classUnit.getName()+".j");
			     fstream = new FileWriter(file, false); //true tells to append data.
			     output = new BufferedWriter(fstream);
			     visitClass(classUnit, sf.getCurrentScope());
			}
			catch (IOException e)
			{
			    System.err.println("Error: " + e.getMessage());
			}
			finally
			{	
				sf.exitLastScope(1);
			    if(output != null) {
			        output.close();
			    }
			}
			
		}
    }
    
    public void visitClass(ClassUnit classUnit, ScopeElement data){
    	emit(".class "+classUnit.getName());
    	if (classUnit.getSuperClass() != null) {
    		emit(".super "+classUnit.getSuperClass().getName());
    	} else {
    		emit(".super "+getType("Object"));
    	}
    	Iterator<Unit> memberUnits = classUnit.getMembersAndMethods().listIterator();
    	Unit memberUnit = null;
		MethodUnit methodUnit = null;
		VariableUnit variableUnit = null;
		// Variables
		emit("");
		while (memberUnits.hasNext()) {
			memberUnit = memberUnits.next();
			if (memberUnit instanceof VariableUnit) {			
				variableUnit = (VariableUnit) memberUnit;
				visitField(variableUnit, sf.getCurrentScope());
			}
		}
		emit("");
		if (!hasDefaultConstructor(classUnit.getMembersAndMethods())) {
			createDefaultConstructor(classUnit);
			emit("");
		}
		memberUnits = classUnit.getMembersAndMethods().listIterator();
		while (memberUnits.hasNext()) {
			memberUnit = memberUnits.next();
			if (memberUnit instanceof MethodUnit) {	
				methodUnit = (MethodUnit) memberUnit;
				sf.enterNewScopeForUnit(methodUnit, MODE);
				if (methodUnit.isConstructor()) {
					visitConstructor(methodUnit, sf.getCurrentScope());
				} else {
					visitMethod(methodUnit, sf.getCurrentScope());
				}
				sf.exitLastScope(1);
				emit("");
			}
		}
    }
    
    private void createDefaultConstructor(ClassUnit classUnit){
    	emit(".method public <init>()V");
    	emit(".limit locals 1");
    	emit(".limit stack 100");
    	emit("aload_0");
    	if (classUnit.getSuperClass() != null){
    		emit("invokespecial "+getType(classUnit.getSuperClass().getName())+"/<init>()V");
    	} else {
    		emit("invokespecial "+getType("Object")+"/<init>()V");
    	}
    	emit("return");
    	emit(".end method");
    }
    
    private boolean hasDefaultConstructor(List<Unit> membersAndMethod) {
    	Iterator<Unit> memberUnits = membersAndMethod.listIterator();
    	Unit memberUnit = null;
    	MethodUnit methodUnit = null; 
    	while (memberUnits.hasNext()) {
    		memberUnit = memberUnits.next();
    		if (memberUnit instanceof MethodUnit) {
    			methodUnit = (MethodUnit) memberUnit;
    			if (methodUnit.isConstructor() && (methodUnit.getFormalArgs() == null)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private void visitField(VariableUnit variableUnit, ScopeElement data) {
    	String code = ".field " + getModifiers(variableUnit)+variableUnit.getName()+" ";
    	if(variableUnit.isArray()) {
    		for(int i=0;i<variableUnit.getArraySize();i++) {
    			code +="[";
    		}
    	}
    	code += getType(variableUnit.getType().typeObj.name);
    	emit(code);
    }
    
    private void visitConstructor(MethodUnit methodUnit, ScopeElement data) {
    	emit(".method "+getModifiers(methodUnit) + "<init>"+getFormalArguments(methodUnit)+"V");
    	emit(".limit locals " + methodUnit.localVariables.size());
    	emit(".limit stack 100");
    	block_AST block = methodUnit.getMethodBlock();
    	sf.enterNewScope(false, MODE);
    	block.cAccept(this, data,"","","");
    	sf.exitLastScope(MODE);
    	emit("return");
    	emit(".end method");
    }
    
    private void visitMethod(MethodUnit methodUnit, ScopeElement data){
    	Type retType = methodUnit.getReturnType().typeObj;
    	emit(".method "
    			+getModifiers(methodUnit)+methodUnit.getName()
    			+getFormalArguments(methodUnit)+getRetType(retType));
    	emit(".limit locals " + methodUnit.localVariables.size());
    	emit(".limit stack 100");
    	block_AST block = methodUnit.getMethodBlock();
    	sf.enterNewScope(false, MODE);
    	block.cAccept(this, data,"","","");
    	sf.exitLastScope(MODE);
    	if (methodUnit.getReturnType() instanceof voidType_AST) {
    		emit("return");
    	}
    	emit(".end method");
    }
    
    private String getModifiers(Unit unit) {
    	Modifier modifier = null;
    	String modifierStr="";
    	if (unit instanceof VariableUnit) {
    		modifier = ((VariableUnit) unit).getModifier();
    	} else if (unit instanceof MethodUnit) {
    		modifier = ((MethodUnit) unit).getModifier();
    	}
    	if (modifier.isScopePublic())
    		modifierStr += " public";
    	if (modifier.isScopeProtected())
    		modifierStr += " protected";
    	if (modifier.isScopePrivate())
    		modifierStr += " private";
    	if (modifier.isScopeStatic())
    		modifierStr += " static";
    	return modifierStr.trim() + " ";
    }
    
    private String getFormalArguments(MethodUnit mUnit){
    	List<Unit> formalArgs = mUnit.getFormalArgs();
    	if (formalArgs == null){
    		return "()";
    	}
    	String argStr = "";
    	for(Unit unit: formalArgs) {
    		VariableUnit vUnit = (VariableUnit) unit;
    		if (vUnit.getType() != null) {
    			argStr += getRetType(vUnit.getType().typeObj)+";";
    		}
    		
    	}
    	return "("+argStr+")";
    }


	public void visitBlock(block_AST node, ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		Iterator<SimpleNode> stmts = node.getStatements().listIterator();
		SimpleNode statement = null;
		while (stmts.hasNext()) {
			statement = stmts.next();
			visitStatement(statement, data, brkLbl, contLbl, nextLbl);
		}
	}
	
	
	public void visitStatement(SimpleNode statement, ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		if (statement instanceof ifThenElseStat_AST) {
			ifThenElseStat_AST stat = (ifThenElseStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof whileStat_AST) {
			whileStat_AST stat = (whileStat_AST)statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof returnStat_AST) {
			returnStat_AST stat = (returnStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof expressionStat_AST) {
			expressionStat_AST stat = (expressionStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof varDeclStat_AST) {
			varDeclStat_AST stat = (varDeclStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof block_AST) {
			block_AST stat = (block_AST) statement;
			sf.enterNewScope(false, 1);
			if (stat.getStatements() == null) {
				stat = (block_AST) stat.children[0];
			}
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
			sf.exitLastScope(1);
		} else if (statement instanceof breakStat_AST) {
			breakStat_AST stat = (breakStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof continueStat_AST) {
			continueStat_AST stat = (continueStat_AST) statement;
			stat.cAccept(this, data, brkLbl, contLbl, nextLbl);
		} else if (statement instanceof emptyStat_AST) {
			// Do Nothing;
		}
	}
	
	public void visitIfThenElseStat(ifThenElseStat_AST node, ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		SimpleNode ifStmt = node.ifStat;
		SimpleNode elseStmt = node.elseStat;
		binaryExpression_AST condition = node.condition;
		String outLab;
		boolean noNext = (nextLbl.length() ==0);
		if (elseStmt != null) {
			String elsePart = mkLabel("Else");
			if (noNext) outLab = mkLabel("FI");
			else outLab = nextLbl;
			condition.cAccept(this, data, elsePart);
			emit("goto\t"+outLab);
			emit(elsePart+":");
			visitStatement(elseStmt, data, brkLbl, contLbl, nextLbl);
			if (noNext)
			    emit(outLab+":");
			return;
		} else {
			if (noNext)   outLab = mkLabel("FI");
			else outLab = nextLbl;
			condition.cAccept(this, data, outLab);
			visitStatement(ifStmt, data, brkLbl, contLbl, outLab);
			if (noNext)
			    emit(outLab+":");
			return;
		}
		
	}
	
	public void visitWhileStat(whileStat_AST node, ScopeElement data,
			String brkLbl, String contLbl, String nextLbl) {
		String topLabel = mkLabel("SWhile");
		String botLabel;

		if (nextLbl.length() == 0)  botLabel = mkLabel("EWhile");
		else botLabel = nextLbl;

		emit(topLabel+":");
		node.condition.cAccept(this, data, botLabel); /* where to go on False */
		visitStatement(node.body, data, botLabel, topLabel, botLabel);
		/*------------------------brk--------cont------next---*/
		if (nextLbl.length() == 0)
		    emit(botLabel+":");
	}

	public void visitReturnStat(returnStat_AST node,
			ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		node.expr.cAccept(this, data, nextLbl);
		emit(getInst(node.expr.typeObj.name)+"return");
		return;
	}
	
	public void visitBreakStat(breakStat_AST node, ScopeElement data,
			String brkLbl, String contLbl, String nextLbl) {
		if (brkLbl.length() == 0) {
		    err.error("Break Statement not with in a while loop",
			      new Position(0, 0, 0, 0));
		    return;
		}
		emit("goto\t"+brkLbl);
		return;
	}
	
	public void visitCont(continueStat_AST node, ScopeElement data, String brkLab,
		      String contLab, String nextLab) {
		if (contLab.length() == 0) {
		    err.error("Continue Statement not with in a while loop",
		    		new Position(0, 0, 0, 0));
		    return;
		}
		emit("goto\t"+contLab);
		return;
	}
	
	public void visitBinaryExpr(binaryExpression_AST node,
			ScopeElement data, String falsLbl) {
		andOperator_AST lhs = node.lhs;
		binaryExpression_AST rhs = node.rhs;
		if (rhs != null) {
			String disjLab = mkLabel("ORLab");
			String trueLab = mkLabel("trueLab");
			lhs.cAccept(this, data, falsLbl);
			emit("goto "+trueLab);
	        emit(disjLab+":");
	        rhs.cAccept(this, data, falsLbl);
	        emit(trueLab+":");
	        return;
		} else if (lhs != null){
			lhs.cAccept(this, data, falsLbl);
		}
	}


	public void visitAndOpertor(andOperator_AST node,
			ScopeElement data, String falsLab) {
		equality_AST lhs = node.lhs;
		andOperator_AST rhs = node.rhs;
		if (rhs != null) {
			lhs.cAccept(this, data, falsLab);
			rhs.cAccept(this, data, falsLab);
		} else {
			lhs.cAccept(this, data, falsLab);
		}
	}


	public void visitEquality(equality_AST node, ScopeElement data,
			String falsLab) {
		relational_AST lhs = node.lhs;
		equality_AST rhs = node.rhs;
		Token sign = node.sign;
		if (rhs != null) {
			lhs.cAccept(this, data, "");
			rhs.cAccept(this, data, "");
			if (sign.image.equals("==")) {
				emit("if_icmpne \t"+falsLab);
			} else if (sign.image.equals("!=")) {
				emit("if_icmpeq \t"+falsLab);
			}
		} else {
			lhs.cAccept(this, data, falsLab);
		}
	}


	public void visitRelational(relational_AST node,
			ScopeElement data, String falsLab) {
		sum_AST lhs = node.lhs;
		relational_AST rhs = node.rhs;
		Token sign = node.sign;
		if (rhs != null) {
			lhs.cAccept(this, data, "");
			rhs.cAccept(this, data, "");
			if (sign.image.equals("<")){
				emit("if_icmpge \t"+falsLab);
			} else if (sign.image.equals("<=")) {
				emit("if_icmpgt \t"+falsLab);
			} else if (sign.image.equals(">")) {
				emit("if_icmple \t"+falsLab);
			} else if (sign.image.equals(">=")) {
				emit("if_icmplt \t"+falsLab);
			}
		} else {
			lhs.cAccept(this, data, falsLab);
		}
	}


	public void visitSum(sum_AST node, ScopeElement data, String falsLab) {
		factor_AST lhs = node.lhs;
		sum_AST rhs = node.rhs;
		Token sign = node.sign;
		if (rhs != null) {
			lhs.cAccept(this, data, "");
			rhs.cAccept(this, data, "");
			if (sign.image.equals("+")) {
				emit(getInst(lhs.typeObj.name)+"add");
			} else if (sign.image.equals("-")) {
				emit(getInst(lhs.typeObj.name)+"sub");
			}
		} else {
			lhs.cAccept(this, data, falsLab);
		}
	}


	public void visitFactor(factor_AST node, ScopeElement data,
			String falsLbl) {
		unary_AST lhs = node.lhs;
		factor_AST rhs = node.rhs;
		Token sign = node.sign;
		if (rhs != null) {
			lhs.cAccept(this, data, "");
			rhs.cAccept(this, data, "");
			if (sign.image.equals("*")) {
				emit(getInst(lhs.typeObj.name)+"mul");
			} else if (sign.image.equals("/")) {
				emit(getInst(lhs.typeObj.name)+"div");
			} else if (sign.image.equals("%")) {
				emit(getInst(lhs.typeObj.name)+"rem");
			}
		} else {
			lhs.cAccept(this, data, falsLbl);
		}
	}


	public void visitUnary(unary_AST node, ScopeElement data,
			String falsLbl) {
		Token sign = node.sign;
		SimpleNode primary = node.primary;
		binaryExpression_AST expr = node.expr;
		if (sign != null) {
			if (sign.image.equals("+")) {
				expr.cAccept(this, data, falsLbl);
				emit("iadd 1 1");
			} else if (sign.image.equals("-")){
				expr.cAccept(this, data, falsLbl);
				emit("iadd 1 -1");
			} else if (sign.image.equals("!")){
				String trueLab = mkLabel("trueLab");
				expr.cAccept(this, data, trueLab);
				emit("goto "+falsLbl);
				emit(trueLab+":");
			}
		} else {
			if (primary instanceof primaryExisting_AST) {
				primaryExisting_AST primaryExisting = (primaryExisting_AST) primary;
				primaryExisting.cAccept(this, data);
			} else if (primary instanceof newArray_AST) {
				newArray_AST newArray = (newArray_AST) primary;
				newArray.cAccept(this, data);
			}
		}
	}


	public void visitNewArray(newArray_AST node, ScopeElement data) {
		for (binaryExpression_AST index:node.arrayIndices) {
			index.cAccept(this, data, "");
		}
		if (node.arrayIndices.size() > 1) {
			emit("multinewarray "+getRetType(node.typeObj));
		} else {
			if (node.typeObj.isClass) {
				emit("anewarray "+getRetType(node.typeObj));
			} else {
				emit("newarray "+getRetType(node.typeObj));
			}
		}
	}

	public void visitPrimary(primaryExisting_AST node,
			ScopeElement data) {
		SimpleNode p1 = node.p1;
		primaryExisiting2_AST p2 = node.p2;
		if (p2 != null) {
			ClassUnit cUnit = null;
			boolean isStatic = false;
			if (p1 instanceof thisExpr_AST) {
				cUnit = ((MethodUnit)sf.getMethodUnitForBlock(data)).classUnit;
				emit("aload_0");
			} else if(p1 instanceof superExpr_AST) {
				cUnit = ((MethodUnit)sf.getMethodUnitForBlock(data)).classUnit.getSuperClass();
				emit("aload_0");
			} else if (p1 instanceof idExpr_AST) {
				idExpr_AST idExpr = (idExpr_AST) p1;
				VariableUnit varUnit = idExpr.variableUnit;
				MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
				if (mUnit == null) {
					System.out.println("DANGER ... Method null");
					exitSys();
				}
				int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
				String className = idExpr.name;
				if (localVariableIndex == -1){
					if (varUnit.isLocalVariable()) {
						className = idExpr.typeObj.name;
						emit("aload_0");
						emit("getfield "+mUnit.classUnit.getName()+"/"+varUnit.getName() + getRetType(varUnit.getType().typeObj));
					} else {
						isStatic = true;
					}
				} else {
					className =idExpr.typeObj.name;
					if (localVariableIndex > 2) {
						emit(getInst(varUnit.getType().typeObj.name)+"load "+(localVariableIndex+1)); // +1 for this
					} else {
						emit(getInst(varUnit.getType().typeObj.name)+"load_"+(localVariableIndex+1)); // +1 for this
					}
				}
				cUnit = (ClassUnit) (getClassScope(className).getScopeUnit().getUnit());
				
			}
			if (p2.isMethodCall) {
				if (!isStatic) {
					emit("aload 0");
				}
				List<binaryExpression_AST> args = p2.arguments;
				if (args != null) {
					for(binaryExpression_AST arg:args) {
						arg.cAccept(this, data, "");
					}
				}
				MethodUnit mUnit = cUnit.getMethod(p2.attribute, args);
				if (mUnit == null) {
					mUnit = cUnit.getSuperClass().getMethod(p2.attribute, args);
				}
				if (isStatic) {
					emit("invokestatic "+cUnit.getName()+"/"+p2.attribute+getFormalArguments(mUnit)+getRetType(mUnit.getReturnType().typeObj));
				} else {
					emit("invokevirtual "+cUnit.getName()+"/"+p2.attribute+getFormalArguments(mUnit)+getRetType(mUnit.getReturnType().typeObj));
				}
			} else {
				// if and else same ... not changing if there is any future need.
				if (isStatic) {
					emit("getField "+cUnit.getName()+"/"+p2.attribute+" "+getRetType(cUnit.getVariable(p2.attribute).getType().typeObj));
				} else {
					emit("getField "+cUnit.getName()+"/"+p2.attribute+" "+getRetType(cUnit.getVariable(p2.attribute).getType().typeObj));
				}
			}
			
			
		} else {
			if (p1 instanceof literal_AST) {
				literal_AST literal = (literal_AST) p1;
				literal.cAccept(this, data);
			} else if (p1 instanceof binaryExpression_AST) {
				binaryExpression_AST expr = (binaryExpression_AST) p1;
				expr.cAccept(this, data, "");
			} else if (p1 instanceof methodExpr_AST) {
				methodExpr_AST methodExpr = (methodExpr_AST) p1;
				MethodUnit munit = methodExpr.methodUnit;
				ClassUnit classUnit = munit.classUnit;
				List<binaryExpression_AST> args = methodExpr.arguments;
				String invoker = "invokevirtual ";
				if (munit.getModifier().isScopeStatic()) {
					invoker = "invokestatic ";
				} else {
					emit("aload 0");
				}
				if (args != null) {
					for(binaryExpression_AST arg:args) {
						arg.cAccept(this, data, "");
					}
				}
				emit(invoker+getType(classUnit.getName())+"/"+munit.getName()+getFormalArguments(munit)+getRetType(munit.getReturnType().typeObj));
			} else if (p1 instanceof newClassExpr_AST) {
				newClassExpr_AST newClassExpr = (newClassExpr_AST) p1;
				ClassUnit classUnit = newClassExpr.classUnit;
				List<binaryExpression_AST> args = newClassExpr.arguments;
				emit("new "+classUnit.getName());
				emit("dup");
				MethodUnit cTor = classUnit.getMethod(classUnit.getName(), args);
				if (cTor == null) {
					emit("invokespecial "+classUnit.getName()+"/<init>()V");
				} else {
					emit("invokespecial "+classUnit.getName()+"/<init>"+getFormalArguments(cTor)+"V");
				}
			} else if (p1 instanceof arrayExpr_AST) {
				arrayExpr_AST idExpr = (arrayExpr_AST) p1;
				VariableUnit varUnit = idExpr.variableUnit;
				MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
				if (mUnit == null) {
					System.out.println("DANGER ... Method null in array");
					exitSys();
				}
				int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
				if (localVariableIndex != -1){
					if (localVariableIndex > 2) {
						emit(getInst(varUnit.getType().typeObj.name)+"load "+(localVariableIndex+1)); // +1 for this
					} else {
						emit(getInst(varUnit.getType().typeObj.name)+"load_"+(localVariableIndex+1)); // +1 for this
					}
				} else {
					emit("aload_0");
					emit("getfield "+mUnit.classUnit.getName()+"/"+varUnit.getName() +" "+getRetType(varUnit.getType().typeObj));
				}
				visitArrayIndices(data, idExpr.arrayIndices);
			} else if (p1 instanceof idExpr_AST) {
				idExpr_AST idExpr = (idExpr_AST) p1;
				VariableUnit varUnit = idExpr.variableUnit;
				MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
				if (mUnit == null) {
					System.out.println("DANGER ... Method null");
					exitSys();
				}
				int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
				if (localVariableIndex != -1){
					if (localVariableIndex > 2) {
						emit(getInst(varUnit.getType().typeObj.name)+"load "+(localVariableIndex+1)); // +1 for this
					} else {
						emit(getInst(varUnit.getType().typeObj.name)+"load_"+(localVariableIndex+1)); // +1 for this
					}
				} else {
					emit("aload_0");
					emit("getfield "+mUnit.classUnit.getName()+"/"+varUnit.getName() +" "+getRetType(varUnit.getType().typeObj));
				}
			}
		}
		
	}

	public void visitArrayIndices(ScopeElement data, List<binaryExpression_AST> arrayIndices) {
		if (arrayIndices == null) {
			return;
		}
		int count = 0;
		for(binaryExpression_AST index:arrayIndices) {
			index.cAccept(this, data, "");
			count += 1;
			if (count != arrayIndices.size()) {
				emit("aaload");
			}
		}
	}

	public void visitLiteral(literal_AST node, ScopeElement data) {
		if (node instanceof nullExpr_AST) {
			nullExpr_AST nullExpr = (nullExpr_AST) node;
			nullExpr.cAccept(this, data);
		} else if (node instanceof boolExpr_AST) {
			boolExpr_AST boolExpr = (boolExpr_AST) node;
			boolExpr.cAccept(this, data);
		} else if (node instanceof intExpr_AST) {
			intExpr_AST intExpr = (intExpr_AST) node;
			intExpr.cAccept(this, data);
		} else if (node instanceof charExpr_AST) {
			charExpr_AST charExpr = (charExpr_AST) node;
			charExpr.cAccept(this, data);
		} else if (node instanceof stringExpr_AST) {
			stringExpr_AST stringExpr = (stringExpr_AST) node;
			stringExpr.cAccept(this, data);
		}
	}


	public void visitNullExpr(nullExpr_AST nullExpr_AST, ScopeElement data) {
		emit("aconst_null");
	}


	public void visitNullExpr(boolExpr_AST node, ScopeElement data) {
		if (node.value == true)
			emit("bipush 1");
		else
			emit("bipush 0");
	}


	public void visitIntExpr(intExpr_AST node, ScopeElement data) {
		if (node.value > 3) {
			emit("iconst "+node.value);
		} else {
			emit("iconst_"+node.value);
		}
	}
	
	public void visitCharExpr(charExpr_AST node, ScopeElement data) {
		emit("bipush "+((int)node.value));
	}


	public void visitStringExpr(stringExpr_AST node, ScopeElement data) {
		emit("ldc "+node.value);
	}


	public void visitVarDecl(varDeclStat_AST node,
			ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		List<Unit> units = node.variables;
		for (Unit unit:units) {
			VariableUnit varUnit = (VariableUnit) unit;
			if (varUnit.rhs != null) {
				varUnit.rhs.cAccept(this, data, nextLbl);
			}
			MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
			int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
			if (varUnit.getType() == null) {
				return;
			}
			if (localVariableIndex > 2) {
				emit(getInst(varUnit.getType().typeObj.name)+"store "+(localVariableIndex+1)); // +1 for this
			} else {
				emit(getInst(varUnit.getType().typeObj.name)+"store"+(localVariableIndex+1)); // +1 for this
			}
		}
	}


	public void visitExpressionStat(expressionStat_AST node,
			ScopeElement data, String brkLbl, String contLbl, String nextLbl) {
		if (node.expressionStat instanceof methodCall_AST) {
			methodCall_AST mCall = (methodCall_AST) node.expressionStat;
			mCall.cAccept(this, data);
		} else if(node.expressionStat instanceof assignOperator_AST) {
			assignOperator_AST assignment = (assignOperator_AST) node.expressionStat;
			assignment.cAccept(this, data, nextLbl);
		}else if (node.expressionStat instanceof binaryExpression_AST) {
			binaryExpression_AST expr = ((binaryExpression_AST)node.expressionStat);
			expr.cAccept(this, data, nextLbl);
		}
		node.typeObj = node.expressionStat.typeObj;
	}


	public void visitAssignOperator(assignOperator_AST node,
			ScopeElement data, String nextLbl) {
		fieldAccess_AST fAccess = node.lhs;
		fieldAccess1_AST lhs = fAccess.fAccess1;
		MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
		String varName = lhs.name;
		VariableUnit varUnit = (VariableUnit)SYMBOL_TABLE.lookUp(varName, UnitType.VARIABLE, new IntegerMuted(-1));
		int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
		if (lhs.arrayIndices != null) {
			if (localVariableIndex != -1){
				if (localVariableIndex > 2) {
					emit(getInst(varUnit.getType().typeObj.name)+"load "+(localVariableIndex+1)); // +1 for this
				} else {
					emit(getInst(varUnit.getType().typeObj.name)+"load_"+(localVariableIndex+1)); // +1 for this
				}
			} else {
				emit("aload_0");
				emit("getfield "+mUnit.classUnit.getName()+"/"+varUnit.getName() +" "+getRetType(varUnit.getType().typeObj));
			}
			visitArrayIndices(data, lhs.arrayIndices);
			node.rhs.cAccept(this, data, nextLbl);
			emit("dup_x2");
			emit("iastore");
		} else {
			node.rhs.cAccept(this, data, nextLbl);
			if (localVariableIndex != -1){
				if (localVariableIndex > 2) {
					emit(getInst(varUnit.getType().typeObj.name)+"store "+(localVariableIndex+1)); // +1 for this
				} else {
					emit(getInst(varUnit.getType().typeObj.name)+"store_"+(localVariableIndex+1)); // +1 for this
				}
			} else {
				emit("aload_0");
				emit("putfield "+mUnit.classUnit.getName()+"/"+varUnit.getName() +" "+getRetType(varUnit.getType().typeObj));
			}
		}
	}


	public void visitMethodCall(methodCall_AST node, ScopeElement data) {
		methodCall1_AST m1 = node.m1;
		List<binaryExpression_AST> args = m1.actualArgs;
		methodPrime_AST mPrime = m1.mPrime;
		methodParent_AST mParent = m1.mParent;
		if (mPrime != null) {
			MethodUnit mUnit = (MethodUnit) sf.getMethodUnitForBlock(data);
			String methodName = mPrime.name;
			String varName = mParent.name;
			VariableUnit varUnit = (VariableUnit)SYMBOL_TABLE.lookUp(varName, UnitType.VARIABLE, new IntegerMuted(-1));
			int localVariableIndex = mUnit.localVariables.indexOf(varUnit);
			if (localVariableIndex != -1){
				ClassUnit cUnit = (ClassUnit)getClassScope(varUnit.getType().typeObj.name).getScopeUnit().getUnit();
				MethodUnit primeMethod = cUnit.getMethod(methodName, args);
				if (localVariableIndex > 2) {
					emit("aload "+(localVariableIndex+1)); // +1 for this
				} else {
					emit("aload_"+(localVariableIndex+1)); // +1 for this
				}
				if (args != null) {
					for(binaryExpression_AST arg:args) {
						arg.cAccept(this, data, "");
					}
				}
				if (primeMethod.getModifier().isScopeStatic()) {
					emit("invokestatic "+cUnit.getName()+"/"+primeMethod.getName()+getFormalArguments(primeMethod)+getRetType(primeMethod.getReturnType().typeObj));
				} else {
					emit("invokevirtual "+cUnit.getName()+"/"+primeMethod.getName()+getFormalArguments(primeMethod)+getRetType(primeMethod.getReturnType().typeObj));
				}
			} else{
				ClassUnit cUnit = (ClassUnit)getClassScope(varName).getScopeUnit().getUnit();
				MethodUnit primeMethod = cUnit.getMethod(methodName, args);
				if (args != null) {
					for(binaryExpression_AST arg:args) {
						arg.cAccept(this, data, "");
					}
				}
				if (mUnit.getModifier().isScopeStatic()) {
					emit("invokestatic "+cUnit.getName()+"/"+primeMethod.getName()+getFormalArguments(primeMethod)+getRetType(primeMethod.getReturnType().typeObj));
				} else {
					emit("invokestatic "+cUnit.getName()+"/"+primeMethod.getName()+getFormalArguments(primeMethod)+getRetType(primeMethod.getReturnType().typeObj));
				}
			}
		} else {
			ClassUnit cUnit = ((MethodUnit) sf.getMethodUnitForBlock(data)).classUnit;
			String methodName = mParent.name;
			MethodUnit mUnit = cUnit.getMethod(methodName, args);
			if (mUnit == null) {
				mUnit = cUnit.getSuperClass().getMethod(methodName, args);
				if (mUnit == null) {
					System.out.println(methodName +" is not defined");
					return;
					//exitSys();
				} else {
					
				}
			}
			if(!mUnit.getModifier().isScopeStatic()) {
				emit("aload 0");
			}
			if (args != null) {
				for(binaryExpression_AST arg:args) {
					arg.cAccept(this, data, "");
				}
			}
			if (mUnit.getModifier().isScopeStatic()) {
				emit("invokestatic "+cUnit.getName()+"/"+mUnit.getName()+getFormalArguments(mUnit)+getRetType(mUnit.getReturnType().typeObj));
			} else {
				emit("invokevirtual "+cUnit.getName()+"/"+mUnit.getName()+getFormalArguments(mUnit)+getRetType(mUnit.getReturnType().typeObj));
			}
		}
	}

}
