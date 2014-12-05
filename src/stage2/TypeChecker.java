package stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class TypeChecker implements DecafVisitor{
	TypeEquality equality;
	public DecafError decafError;
	private final static int MODE = 1;
	private static ScopeFactory sf = ScopeFactory.getScopeFactory();
	public static SymbolTable SYMBOL_TABLE = SymbolTable.getSymbolTable();
	private final static List<String> basicTypes = new ArrayList<String>(Arrays.asList("int","boolean","char"));
	private final static List<String> builtinTypes = new ArrayList<String>(Arrays.asList("Object","String","IO"));
	
	
	public TypeChecker() {
		decafError = new DecafError();
	}
	
	@Override
	public Object visit(SimpleNode node, ScopeElement data) {
		// Should not come here
		System.out.println("What is the program doing here?");
		return null;
	}

	@Override
	public Object visitStart_AST(start_AST node, ScopeElement data) {
		Iterator<Unit> classes = node.getClasses().listIterator();
		ClassUnit classUnit = null;
		data.setKidLevel(3);
		while (classes.hasNext()) {
			classUnit = (ClassUnit)classes.next();
			sf.enterNewScopeForUnit(classUnit, MODE);
			visitClass(classUnit, sf.getCurrentScope());
			sf.exitLastScope(1);
		}
		return null;
	}

	@Override
	public Object visit(int_AST node, ScopeElement data) {
		// Handled in jjt
		return null;
	}

	@Override
	public Object visit(char_AST node, ScopeElement data) {
		// Handled in jjt
		return null;
	}

	@Override
	public Object visit(boolean_AST node, ScopeElement data) {
		// Handled in jjt
		return null;
	}

	@Override
	public Object visit(id_AST node, ScopeElement data) {
		// Handled in jjt
		return null;
	}

	@Override
	public Object visitAssignOperator(assignOperator_AST node, ScopeElement data) {
		fieldAccess_AST lhs = node.lhs;
		binaryExpression_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		rhs.jjtAccept(this, data);
		if (!TypeEquality.eq(lhs.typeObj, rhs.typeObj, null)) {
			System.out.println("LHS : " +lhs.typeObj.toString());
			System.out.println("RHS : " +rhs.typeObj.toString());
			decafError.numErrors++;
			decafError.error("Left hand side and right hand side have to be of the same type", new Position(0, 0, 0, 0));
		}
		return null;
	}

	@Override
	public Object visit(voidType_AST node, ScopeElement data) {
		// Handled in jjt
		return null;
	}

	@Override
	public Object visitBlock_AST(block_AST node, ScopeElement data) {
		Iterator<SimpleNode> stmts = node.getStatements().listIterator();
		SimpleNode statement = null;
		while (stmts.hasNext()) {
			statement = stmts.next();
			visitStatement(statement, data);
		}
		return null;
	}
	
	public void visitStatement(SimpleNode statement, ScopeElement data){
		if (statement instanceof ifThenElseStat_AST) {
			ifThenElseStat_AST stat = (ifThenElseStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof whileStat_AST) {
			whileStat_AST stat = (whileStat_AST)statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof returnStat_AST) {
			returnStat_AST stat = (returnStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof expressionStat_AST) {
			expressionStat_AST stat = (expressionStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof varDeclStat_AST) {
			varDeclStat_AST stat = (varDeclStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof block_AST) {
			block_AST stat = (block_AST) statement;
			sf.enterNewScope(false, 1);
			stat.jjtAccept(this, data);
			sf.exitLastScope(1);
		} else if (statement instanceof breakStat_AST) {
			breakStat_AST stat = (breakStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof continueStat_AST) {
			continueStat_AST stat = (continueStat_AST) statement;
			stat.jjtAccept(this, data);
		} else if (statement instanceof emptyStat_AST) {
			emptyStat_AST stat = (emptyStat_AST) statement;
			stat.jjtAccept(this, data);
		}
	}
	
	@Override
	public Object visitIfThenElseStat(ifThenElseStat_AST node, ScopeElement data) {
		SimpleNode ifStmt = node.ifStat;
		SimpleNode elseStmt = node.elseStat;
		binaryExpression_AST condition = node.condition;
		condition.jjtAccept(this, data);
		if (!TypeEquality.eq(condition.typeObj, new Type("boolean", false, 0, false), null)) {
			decafError.numErrors++;
			decafError.error("If condition has to be of type boolean", new Position(0, 0, 0, 0));
		}
		visitStatement(ifStmt, data);
		if (elseStmt != null) {
			visitStatement(elseStmt, data);
		}
		return null;
	}

	@Override
	public Object visitWhileStat(whileStat_AST node, ScopeElement data) {
		binaryExpression_AST condition = node.condition;
		SimpleNode bodyStmt = node.body;
		condition.jjtAccept(this, data);
		visitStatement(bodyStmt, data);
		if (!TypeEquality.eq(condition.typeObj, new Type("boolean", false, 0, false), null)) {
			decafError.numErrors++;
			decafError.error("While condition has to be of type boolean", new Position(0, 0, 0, 0));
		}
		return null;
	}
	
	

	
	@Override
	public Object visitReturnStat(returnStat_AST node, ScopeElement data) {
		binaryExpression_AST returnStmt = node.expr;
		MethodUnit methodUnit = (MethodUnit) data.getScopeUnit().getUnit();
		if (methodUnit == null)
			return null;
		Type methodReturnType = methodUnit.getReturnType().typeObj;
		if (returnStmt == null && methodReturnType.name.equals("null")){
			return null;
		}else if (returnStmt == null) {
			decafError.numErrors++;
			decafError.error("Expecting " +methodReturnType.name+ " return type", new Position(0, 0, 0, 0));
		}
		returnStmt.jjtAccept(this, data);
		if (data.getScopeUnit().getUnit() instanceof MethodUnit){
			if (!TypeEquality.eq(returnStmt.typeObj, methodReturnType, null)) {
				decafError.numErrors++;
				decafError.error("Invalid return type in method " + methodUnit.getName(), new Position(0, 0, 0, 0));
			}
		} else {
			System.out.println("Scope Not in method. CHECK IT OUT !!!");
			decafError.numErrors++;
			decafError.error("Scope is not in method", new Position(0, 0, 0, 0));
		}
		return null;
	}

	@Override
	public Object visitExpressionStat(expressionStat_AST node, ScopeElement data) {
		if (node.expressionStat instanceof methodCall_AST) {
			methodCall_AST mCall = (methodCall_AST) node.expressionStat;
			mCall.jjtAccept(this, data);
		} else if(node.expressionStat instanceof assignOperator_AST) {
			assignOperator_AST assignment = (assignOperator_AST) node.expressionStat;
			assignment.jjtAccept(this, data);
		}else if (node.expressionStat instanceof binaryExpression_AST) {
			binaryExpression_AST expr = ((binaryExpression_AST)node.expressionStat);
			expr.jjtAccept(this, data);
		}
		node.typeObj = node.expressionStat.typeObj;
		return null;
	}

	@Override
	public Object visitVarDeclStat(varDeclStat_AST node, ScopeElement data) {
		// DO Nothing
		return null;
	}

	@Override
	public Object visitBreakStat(breakStat_AST node, ScopeElement data) {
		//Do Nothing
		return null;
	}

	@Override
	public Object visitContinueStat(continueStat_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitEmptyStat(emptyStat_AST node, ScopeElement data) {
		// Do Nothin
		return null;
	}

	@Override
	public Object visit(fieldExpr_AST node, ScopeElement data) {
		// Need not be handled
		return null;
	}

	@Override
	public Object visitArrayExpr(arrayExpr_AST node, ScopeElement data) {
		List<binaryExpression_AST> arrayIndices = node.arrayIndices;
		for (binaryExpression_AST arrayIndex: arrayIndices) {
			arrayIndex.jjtAccept(this, data);
			if (! arrayIndex.typeObj.name.equals("int")) {
				decafError.numErrors++;
				decafError.error("Array Index for " +node.name+ " is not an integer", new Position(0, 0, 0, 0));
			}
		}
		node.typeObj.name = node.variableUnit.getType().jjtGetValue().toString();
		node.typeObj.isArray = true;
		node.typeObj.arraySize = arrayIndices.size();
		node.typeObj.isClass = true;
		if (arrayIndices.size() == node.variableUnit.getArraySize()) {
			node.typeObj.isArray = false;
		}
		
		return null;
	}

	@Override
	public Object visitIdExpr(idExpr_AST node, ScopeElement data) {
		node.typeObj = node.variableUnit.getType().typeObj;
		return null;
	}

	@Override
	public Object visitThisExpr(thisExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitSuperExpr(superExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitNewClassExpr(newClassExpr_AST node, ScopeElement data) {
		List<binaryExpression_AST> actualArgs = node.arguments;
		ClassUnit classUnit = node.classUnit;
		if (actualArgs != null) {
			for (binaryExpression_AST actualArg: actualArgs) {
				actualArg.jjtAccept(this, data);
			}
		}
		MethodUnit constructor = classUnit.getMethod(node.name, actualArgs);
		if (constructor == null) {
			decafError.numErrors++;
			decafError.error("No Class name ", new Position(0, 0, 0, 0));
		}
		node.typeObj.name = node.name;
		node.typeObj.isClass =true;
		return null;
	}

	@Override
	public Object visitMethodExpr(methodExpr_AST node, ScopeElement data) {
		List<binaryExpression_AST> actualArgs = node.arguments;
		MethodUnit methodUnit = node.methodUnit;
		if (actualArgs != null) {
			for (binaryExpression_AST actualArg: actualArgs) {
				actualArg.jjtAccept(this, data);
			}
		}
		if (methodUnit.validateMethodCall(actualArgs)) {
			decafError.numErrors++;
			decafError.error("Invalid method call " + methodUnit.getName(), new Position(0, 0, 0, 0));
		}
		node.typeObj = methodUnit.getReturnType().typeObj;
		if (!basicTypes.contains(node.typeObj.name)) {
			node.typeObj.isClass = true;
		} 
			
		return null;
	}


	@Override
	public Object visitAndOperator(andOperator_AST node, ScopeElement data) {
		equality_AST lhs = node.lhs;
		andOperator_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null) {
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, new Type("boolean", false, 0, false))) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of type boolean" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj = lhs.typeObj;
		return null;
	}


	@Override
	public void visitClass(ClassUnit classUnit, ScopeElement data) {
		Iterator<Unit> memberUnits = classUnit.getMembersAndMethods().listIterator();
		Unit memberUnit = null;
		MethodUnit methodUnit = null;
		@SuppressWarnings("unused")
		VariableUnit variableUnit = null;
		while (memberUnits.hasNext()) {
			memberUnit = memberUnits.next();
			if (memberUnit instanceof VariableUnit) {			
				variableUnit = (VariableUnit) memberUnit;
				// Handled in jjt
			} else {
				methodUnit = (MethodUnit) memberUnit;
				sf.enterNewScopeForUnit(methodUnit, MODE);
				visitMethod(methodUnit, sf.getCurrentScope());
				sf.exitLastScope(1);
			}
		}
		
	}

	@Override
	public void visitMethod(MethodUnit methodUnit, ScopeElement data) {
		block_AST block =  methodUnit.getMethodBlock();
		sf.enterNewScope(false, 1);
		block.jjtAccept(this, data);
		sf.exitLastScope(1);
	}

	@Override
	public Object visitEquality(equality_AST node, ScopeElement data) {
		relational_AST lhs = node.lhs;
		equality_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null) {
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, null)) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of same type" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj = lhs.typeObj;
		return null;
	}

	@Override
	public Object visitRelational(relational_AST node, ScopeElement data) {
		sum_AST lhs = node.lhs;
		relational_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null) {
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, new Type("int", false, 0, false))) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of type integer" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj = lhs.typeObj;
		return null;
	}

	@Override
	public Object visitSum(sum_AST node, ScopeElement data) {
		factor_AST lhs = node.lhs;
		sum_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null) {
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, new Type("int", false, 0, false))) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of type integer" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj = lhs.typeObj;
		return null;
	}

	@Override
	public Object visitFactor(factor_AST node, ScopeElement data) {
		unary_AST lhs = node.lhs;
		factor_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null) {
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, new Type("int", false, 0, false))) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of type integer" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj = lhs.typeObj;
			
		return null;
	}

	@Override
	public Object visitUnary(unary_AST node, ScopeElement data) {
		if (node.primary != null) {
			if (node.primary instanceof primaryExisting_AST) {
				primaryExisting_AST p = (primaryExisting_AST) node.primary;
				p.jjtAccept(this, data);
				node.typeObj = p.typeObj;
			} else if (node.primary instanceof newArray_AST){
				newArray_AST n = (newArray_AST) node.primary;
				n.jjtAccept(this, data);
				node.typeObj = n.typeObj;
			}
		} else {
			Token sign = node.sign;
			binaryExpression_AST exp = node.expr;
			exp.jjtAccept(this, data);
			if (sign.image.equals("!") && (!exp.typeObj.name.equals("boolean"))) {
				decafError.numErrors++;
				decafError.error("Expression does not evaluate to a boolean" , new Position(0, 0, 0, 0));
			} else if ((sign.image.equals("+")||sign.image.equals("-")) && (!exp.typeObj.name.equals("int"))) {
				decafError.numErrors++;
				decafError.error("Expression does not evaluate to a int" , new Position(0, 0, 0, 0));
			} 
			node.typeObj = exp.typeObj;
		}
		return null;
	}

	@Override
	public Object visitExpression(binaryExpression_AST node, ScopeElement data) {
		andOperator_AST lhs = node.lhs;
		binaryExpression_AST rhs = node.rhs;
		lhs.jjtAccept(this, data);
		if (rhs != null){
			rhs.jjtAccept(this, data);
			if (! TypeEquality.eq(lhs.typeObj, rhs.typeObj, new Type("boolean", false, 0, false))) {
				decafError.numErrors++;
				decafError.error("LHS and RHS needs to be of type boolean" , new Position(0, 0, 0, 0));
			}
		}
		node.typeObj =lhs.typeObj;
		return null;
	}

	@Override
	public Object visitNullExpr(nullExpr_AST node, ScopeElement data) {
		// Do Nothing;
		return null;
	}

	@Override
	public Object visitBoolExpr(boolExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitIntExpr(intExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitCharExpr(charExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}

	@Override
	public Object visitStringExpr(stringExpr_AST node, ScopeElement data) {
		// Do Nothing
		return null;
	}
	
	@Override
	public Object visitLiteral(literal_AST node, ScopeElement data) {
		if (node instanceof nullExpr_AST ) {
			nullExpr_AST n = (nullExpr_AST) node;
			n.jjtAccept(this, data);
		} else if (node instanceof boolExpr_AST) {
			boolExpr_AST b = (boolExpr_AST) node;
			b.jjtAccept(this, data);
		} else if (node instanceof intExpr_AST) {
			intExpr_AST i = (intExpr_AST) node;
			i.jjtAccept(this, data);
		} else if (node instanceof charExpr_AST) {
			charExpr_AST c = (charExpr_AST) node;
			c.jjtAccept(this, data);
		} else if (node instanceof stringExpr_AST) {
			stringExpr_AST s = (stringExpr_AST) node;
			s.jjtAccept(this, data);
		}
		return null;
	}

	@Override
	public Object visitPrimaryExisting(primaryExisting_AST node, ScopeElement data) {
		SimpleNode p1 = node.p1;
		primaryExisiting2_AST p2 = node.p2;
		ScopeElement callerScope = null;
		if (p1 instanceof literal_AST) {
			literal_AST literal = (literal_AST) p1;
			literal.jjtAccept(this, data);
			if (literal.typeObj.isClass) {
				callerScope = getClassScope(literal.typeObj.name);
			}
		} else if (p1 instanceof thisExpr_AST) {
			thisExpr_AST thisExpr = (thisExpr_AST) p1;
			thisExpr.jjtAccept(this, data);
			data = getClassScope(data);
			thisExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
			thisExpr.typeObj.isClass = true;
		} else if (p1 instanceof superExpr_AST) {
			superExpr_AST superExpr = (superExpr_AST) p1;
			superExpr.jjtAccept(this, data);
			data = getSuperClassScope(data);
			superExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
			superExpr.typeObj.isClass = true;
		} else if (p1 instanceof binaryExpression_AST) {
			binaryExpression_AST expression = (binaryExpression_AST) p1;
			expression.jjtAccept(this, data);
			if (expression.typeObj.isClass) {
				callerScope = getClassScope(expression.typeObj.name);
			}
		} else if (p1 instanceof newClassExpr_AST) {
			newClassExpr_AST newClass = (newClassExpr_AST) p1;
			newClass.jjtAccept(this, data);
			if (newClass.typeObj.isClass) {
				callerScope = getClassScope(newClass.typeObj.name);
			}
		} else if (p1 instanceof methodExpr_AST) {
			methodExpr_AST methodExpr = (methodExpr_AST) p1;
			methodExpr.jjtAccept(this, data);
			if (methodExpr.typeObj.isClass) {
				callerScope = getClassScope(methodExpr.typeObj.name);
			}
		} else if (p1 instanceof arrayExpr_AST) {
			arrayExpr_AST arrayExpr = (arrayExpr_AST) p1;
			arrayExpr.jjtAccept(this, data);
			if (arrayExpr.typeObj.isClass && !(arrayExpr.typeObj.isArray)) {
				callerScope = getClassScope(arrayExpr.typeObj.name);
			}
		} else if (p1 instanceof idExpr_AST) {
			idExpr_AST idExpr = (idExpr_AST) p1;
			idExpr.jjtAccept(this, data);
			if (idExpr.typeObj.isClass) {
				callerScope = getClassScope(idExpr.typeObj.name);
			}
		}
		
		if (p2 != null) {
			if (callerScope == null) {
				System.out.println(p1.jjtGetValue()+ " is not a class.");
			}
			p2.callerScope = callerScope;
			p2.jjtAccept(this, data);
			node.typeObj = p2.typeObj;
		} else {
			node.typeObj = p1.typeObj;
		}
		return null;
	}

	@Override
	public Object visitPrimaryExisting2(primaryExisiting2_AST node, ScopeElement data) {
		ClassUnit classUnit =(ClassUnit)node.callerScope.getScopeUnit().getUnit();
		Unit unit;
		if (node.isMethodCall) {
			if (node.arguments != null) {
				for(binaryExpression_AST arg:node.arguments) {
					arg.jjtAccept(this, data);
				}
			}
			unit = classUnit.getMethod(node.attribute, node.arguments);
			node.typeObj = ((MethodUnit)unit).getReturnType().typeObj;
		} else if (node.arrayExpression != null) {
			node.arrayExpression.jjtAccept(this, data);
			unit = classUnit.getVariable(node.attribute);
			node.typeObj = ((VariableUnit)unit).getType().typeObj;
			node.typeObj.isArray = false;
		} else {
			unit = classUnit.getVariable(node.attribute);
			node.typeObj = ((VariableUnit)unit).getType().typeObj;
		}
		return null;
	}

	@Override
	public Object visitNewArray(newArray_AST node, ScopeElement data) {
		for(binaryExpression_AST arrayIndex:node.arrayIndices) {
			arrayIndex.jjtAccept(this, data);
		}
		return null;
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
	
	private ScopeElement getClassScope(ScopeElement currentScope){
		ScopeElement tempScope = currentScope;
		while (tempScope != null) {
			if (tempScope.getScopeUnit().getUnit() instanceof ClassUnit) {
				return tempScope;
			}
			else 
				tempScope = tempScope.getParent();
		}
		return null;
	}
	
	private ScopeElement getSuperClassScope(ScopeElement currentScope) {
		ScopeElement tempScope = currentScope;
		while (tempScope != null) {
			if (tempScope.getScopeUnit().getUnit() instanceof ClassUnit) {
				break;
			}else 
				tempScope = tempScope.getParent();
		}
		if (tempScope == null) {
			return null;
		}
		ScopeElement startScope = tempScope.getParent();
		String superClassName = ((ClassUnit)tempScope.getScopeUnit().getUnit()).getSuperClass().getName();
		for(ScopeElement kidElement: startScope.getKids()) {
			if (kidElement.getScopeUnit().getUnit().getName().equals(superClassName)) {
				return kidElement;
			}
		}
		return null;
	}

	@Override
	public Object visitMethodCall(methodCall_AST node, ScopeElement data) {
		methodCall1_AST m1 = node.m1;
		methodCall2_AST m2 = node.m2;
		m1.jjtAccept(this, data);
		if (m2 != null) {
			if (!basicTypes.contains(m1.typeObj.name)) {
				m2.callerScope = getClassScope(m1.typeObj.name);
				m2.jjtAccept(this, data);
			} else {
				decafError.numErrors++;
				decafError.error(m1.typeObj.name+" cannot call a method" , new Position(0, 0, 0, 0));
			}
			m2.jjtAccept(this, data);
			node.typeObj = m2.typeObj;
		} else {
			node.typeObj = m1.typeObj;
		}
		return null;
	}
	
	@Override
	public Object visitMethodCall1(methodCall1_AST node, ScopeElement data) {
		methodParent_AST mParent = node.mParent;
		methodPrime_AST mPrime = node.mPrime;
		List<binaryExpression_AST> actualArgs = node.actualArgs;
		//mParent.jjtAccept(this, data);
		if (actualArgs != null) {
			for(binaryExpression_AST arg: actualArgs) {
				arg.jjtAccept(this, data);
			}
		}
		if (mPrime != null && !basicTypes.contains(mParent.typeObj.name)) {
			mParent.isMethod = false;
			mParent.jjtAccept(this, data);
			mPrime.actualArgs = actualArgs;
			mPrime.callerScope = getClassScope(mParent.typeObj.name);
			mPrime.isMethod = true;
			mPrime.jjtAccept(this, data);
			node.typeObj = mPrime.typeObj;
		} else if (mPrime != null && basicTypes.contains(mParent.typeObj.name)) {
			// TODO Cannot call . for basic types
		} else {
			mParent.isMethod = true;
			mParent.actualArgs = actualArgs;
			mParent.jjtAccept(this, data);
			node.typeObj = mParent.typeObj;
		} 
		return null;
	}

	@Override
	public Object visitMethodCall2(methodCall2_AST node, ScopeElement data) {
	  String name = node.name;
	  List<binaryExpression_AST> actualArgs = node.actualArgs;
	  methodCall2_AST m2 = node.m2;
	  binaryExpression_AST arrayIndex = node.arrayIndex;
	  arrayIndex.jjtAccept(this, data);
	  methodPrime_AST mPrime = node.mPrime;
	  ScopeElement callerScope = node.callerScope;
	  Type typeObj = null;
	  if (mPrime != null) {
		  mPrime.callerScope = callerScope;
		  mPrime.actualArgs = actualArgs;
		  mPrime.isMethod = true;
		  mPrime.jjtAccept(this, data);
		  typeObj = mPrime.typeObj;
	  } else {
		  ClassUnit cUnit = (ClassUnit)callerScope.getScopeUnit().getUnit();
		  MethodUnit mUnit = cUnit.getMethod(name, node.actualArgs);
		  if (mUnit == null) {
			decafError.numErrors++;
			decafError.error("No Method name " +name, new Position(0, 0, 0, 0));
		  }
		  typeObj = mUnit.getReturnType().typeObj;
	  }
	  if (m2 != null) {
		  m2.callerScope = getClassScope(typeObj.name);
		  m2.jjtAccept(this, data);
		  node.typeObj = m2.typeObj;
	  } else {
		  node.typeObj = typeObj;
	  }
	  return null;
	}

	@Override
	public Object visitMethodParent(methodParent_AST node, ScopeElement data) {
		SimpleNode p1 = node.primaryMethodParent;
		String name = node.name;
		VariableUnit vUnit = null;
		MethodUnit mUnit = null;
		binaryExpression_AST arrayIndex = node.arrayIndex;
		if (p1 != null) {
			ScopeElement callerScope = null;
			if (p1 instanceof literal_AST) {
				literal_AST literal = (literal_AST) p1;
				literal.jjtAccept(this, data);
				if (literal.typeObj.isClass) {
					callerScope = getClassScope(literal.typeObj.name);
				}
			} else if (p1 instanceof thisExpr_AST) {
				thisExpr_AST thisExpr = (thisExpr_AST) p1;
				thisExpr.jjtAccept(this, data);
				data = getClassScope(data);
				thisExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
				thisExpr.typeObj.isClass = true;
			} else if (p1 instanceof superExpr_AST) {
				superExpr_AST superExpr = (superExpr_AST) p1;
				superExpr.jjtAccept(this, data);
				data = getSuperClassScope(data);
				superExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
				superExpr.typeObj.isClass = true;
			} else if (p1 instanceof binaryExpression_AST) {
				binaryExpression_AST expression = (binaryExpression_AST) p1;
				expression.jjtAccept(this, data);
				if (expression.typeObj.isClass) {
					callerScope = getClassScope(expression.typeObj.name);
				}
			} else if (p1 instanceof newClassExpr_AST) {
				newClassExpr_AST newClass = (newClassExpr_AST) p1;
				newClass.jjtAccept(this, data);
				if (newClass.typeObj.isClass) {
					callerScope = getClassScope(newClass.typeObj.name);
				}
			}
			if (callerScope!= null) {
				ClassUnit cUnit = (ClassUnit)callerScope.getScopeUnit().getUnit();
				if (node.isMethod) {
					mUnit = cUnit.getMethod(name, node.actualArgs);
					node.typeObj = mUnit.getReturnType().typeObj;
				} else {
					vUnit = cUnit.getVariable(name);
					node.typeObj = vUnit.getType().typeObj;	
				}
			} else{
				// TODO handle error
			}
		} else {
			if (node.isMethod) {
				IntegerMuted intBaseMuted = new IntegerMuted(-1);
				mUnit = (MethodUnit) SYMBOL_TABLE.lookUp(name, UnitType.METHOD, intBaseMuted);
				if (mUnit != null){
					node.typeObj = mUnit.getReturnType().typeObj;
				} else {
					// TODO handle error
				}
			} else {
				IntegerMuted intBaseMuted = new IntegerMuted(-1);
				vUnit = (VariableUnit) SYMBOL_TABLE.lookUp(name, UnitType.VARIABLE, intBaseMuted);
				if (vUnit != null){
					node.typeObj = vUnit.getType().typeObj;
				} else {
					// Static variables come here
					ClassUnit cUnit = (ClassUnit)getClassScope(name).getScopeUnit().getUnit();
					if (cUnit!= null){
						node.typeObj = new Type(name, false, 0, true);
					}
					// TODO handle error
				}
			}
		}
		if (arrayIndex!=null) {
			arrayIndex.jjtAccept(this, data);
		}
		return null;
	}

	@Override
	public Object visitMethodPrime(methodPrime_AST node, ScopeElement data) {
		String name = node.name;
		methodPrime_AST mPrime = node.mPrime;
		ScopeElement currentScope = node.callerScope;
		ClassUnit cUnit = (ClassUnit)currentScope.getScopeUnit().getUnit();
		
		if (mPrime!= null) {
			VariableUnit vUnit = cUnit.getVariable(name);
			mPrime.callerScope = getClassScope(vUnit.getType().typeObj.name);
			mPrime.isMethod = node.isMethod;
			mPrime.jjtAccept(this, data);
			node.typeObj = mPrime.typeObj;
		} else {
			if (node.isMethod) {
				MethodUnit mUnit = cUnit.getMethod(name, node.actualArgs);
				if (mUnit == null) {
					decafError.numErrors++;
					decafError.error("No Method name " +name, new Position(0, 0, 0, 0));
				}
				node.typeObj = mUnit.getReturnType().typeObj;
			} else {
				VariableUnit vUnit = cUnit.getVariable(name);
				node.typeObj = vUnit.getType().typeObj;
			}
			
		}
		
		return null;
	}

	@Override
	public Object visitFieldAccess(fieldAccess_AST node, ScopeElement data) {
		fieldAccess1_AST fAccess1 = node.fAccess1;
		methodPrime_AST mPrime = node.mPrime;
		fAccess1.jjtAccept(this, data);
		if (mPrime!= null) {
			if (fAccess1.typeObj.isClass && !fAccess1.typeObj.isClass) {
				mPrime.isMethod = false;
				mPrime.callerScope = getClassScope(fAccess1.typeObj.name);
				mPrime.jjtAccept(this, data);
				node.typeObj = mPrime.typeObj;
			} else {
				System.out.println("Error line : 845  for field access " + node.toString() );
			}
		} else {
			node.typeObj = fAccess1.typeObj;
		}
		return null;
	}

	@Override
	public Object visitFieldAccess1(fieldAccess1_AST node, ScopeElement data) {
		SimpleNode p1 = node.primaryFieldAccess;
		String name = node.name;
		List<binaryExpression_AST> arrayIndices = node.arrayIndices;
		VariableUnit vUnit = null;
		if (p1 != null) {
			ScopeElement callerScope = null;
			if (p1 instanceof literal_AST) {
				literal_AST literal = (literal_AST) p1;
				literal.jjtAccept(this, data);
				if (literal.typeObj.isClass) {
					callerScope = getClassScope(literal.typeObj.name);
				}
			} else if (p1 instanceof thisExpr_AST) {
				thisExpr_AST thisExpr = (thisExpr_AST) p1;
				thisExpr.jjtAccept(this, data);
				data = getClassScope(data);
				thisExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
				thisExpr.typeObj.isClass = true;
			} else if (p1 instanceof superExpr_AST) {
				superExpr_AST superExpr = (superExpr_AST) p1;
				superExpr.jjtAccept(this, data);
				data = getSuperClassScope(data);
				superExpr.typeObj.name = data.getScopeUnit().getUnit().getName();
				superExpr.typeObj.isClass = true;
			} else if (p1 instanceof newClassExpr_AST) {
				newClassExpr_AST newClass = (newClassExpr_AST) p1;
				newClass.jjtAccept(this, data);
				if (newClass.typeObj.isClass) {
					callerScope = getClassScope(newClass.typeObj.name);
				}
			}
			if (callerScope!= null) {
				ClassUnit cUnit = (ClassUnit)callerScope.getScopeUnit().getUnit();
				vUnit = cUnit.getVariable(name);
				node.typeObj = vUnit.getType().typeObj;
				if ((arrayIndices == null) && (!node.typeObj.isArray)) {
					// Variable
				} else if (arrayIndices!=null && (arrayIndices.size() == node.typeObj.arraySize)) {
					// Variable in array
					node.typeObj.isArray = false;
				} else if (arrayIndices!=null && (arrayIndices.size() < node.typeObj.arraySize)) {
					// array
					node.typeObj.arraySize = node.typeObj.arraySize - arrayIndices.size();
					node.typeObj.isArray = true;
				} else {
					// TODO error handle
				}
			} else{
				IntegerMuted intBaseMuted = new IntegerMuted(-1);
				vUnit = (VariableUnit) SYMBOL_TABLE.lookUp(name, UnitType.VARIABLE, intBaseMuted);
				node.typeObj = vUnit.getType().typeObj;
				if ((arrayIndices == null) && (!node.typeObj.isArray)) {
					// Variable
				} else if (arrayIndices!=null && (arrayIndices.size() == node.typeObj.arraySize)) {
					// Variable in array
					node.typeObj.isArray = false;
				} else if (arrayIndices!=null && (arrayIndices.size() < node.typeObj.arraySize)) {
					// array
					node.typeObj.arraySize = node.typeObj.arraySize - arrayIndices.size();
					node.typeObj.isArray = true;
				} else {
					// TODO error handle
				}
			}
		} else {
			IntegerMuted intBaseMuted = new IntegerMuted(-1);
			vUnit = (VariableUnit) SYMBOL_TABLE.lookUp(name, UnitType.VARIABLE, intBaseMuted);
			node.typeObj = vUnit.getType().typeObj;
			if ((arrayIndices == null) && (!node.typeObj.isArray)) {
				// Variable
			} else if (arrayIndices!=null && (arrayIndices.size() == node.typeObj.arraySize)) {
				// Variable in array
				node.typeObj.isArray = false;
			} else if (arrayIndices!=null && (arrayIndices.size() < node.typeObj.arraySize)) {
				// array
				node.typeObj.arraySize = node.typeObj.arraySize - arrayIndices.size();
				node.typeObj.isArray = true;
			} else {
				// TODO error handle
			}
		}
		return null;
	}
}
