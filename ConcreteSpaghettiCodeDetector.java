package com.antipattern.detector.AntipatternDetector;

import java.util.ArrayList;

import java.util.List;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.antipattern.detector.AntipatternDetector.*;

public class ConcreteSpaghettiCodeDetector extends SpaghettiCodeDetector {

	// No additional functionality needed for testing purposes.
//java -classpath MyCheckstyle.jar;checkstyle-10.4-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml TypeCheckingDetector.java
	@Override
	public int[] getAcceptableTokens() {
		// Provide your implementation here
		List<Integer> acceptableTokens = new ArrayList<>();
		// Add the acceptable tokens to the list
		acceptableTokens.add(TokenTypes.CLASS_DEF);
		acceptableTokens.add(TokenTypes.INTERFACE_DEF);
		acceptableTokens.add(TokenTypes.ENUM_DEF);
		acceptableTokens.add(TokenTypes.ANNOTATION_DEF);

		int[] result = new int[acceptableTokens.size()];
		for (int i = 0; i < acceptableTokens.size(); i++) {
			result[i] = acceptableTokens.get(i);
		}
		return result;
	}

	@Override
	public void visitToken(DetailAST ast) {
		super.visitToken(ast);
		if (ast.getType() == TokenTypes.CLASS_DEF) {
			// Check for long class definitions
			int classLength = ast.getLastChild().getLineNo() - ast.getLineNo() + 1;
			if (classLength > 100) {
				log(ast, "Class definition too long (" + classLength + " lines)");
			}
		} else if (ast.getType() == TokenTypes.METHOD_DEF) {
			// Check for long method definitions
			int methodLength = ast.getLastChild().getLineNo() - ast.getLineNo() + 1;
			if (methodLength > 50) {
				log(ast, "Method definition too long (" + methodLength + " lines)");
			}
		} else if (ast.getType() == TokenTypes.LITERAL_IF) {
			// Check for nested if statements
			DetailAST parent = ast.getParent();
			while (parent != null && parent.getType() == TokenTypes.LITERAL_IF) {
				log(ast, "Nested if statement found");
				parent = parent.getParent();
			}
		}
		// Add more code to check for other types of spaghetti code
	}

}
