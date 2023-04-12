package com.antipattern.detector.AntipatternDetector;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import java.util.function.Supplier;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SpaghettiCodeDetector extends AbstractCheck {
    private static final int MAX_METHOD_LENGTH = 50;
    private static final int MAX_NESTING_DEPTH = 4;

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
        if (openingBrace != null) {
            final int methodLength = openingBrace.getLineNo() - ast.getLineNo();
            if (methodLength > MAX_METHOD_LENGTH) {
                log(ast, "Spaghetti code detected: method too long");
            }

            final int nestingDepth = getNestingDepth(openingBrace);
            if (nestingDepth > MAX_NESTING_DEPTH) {
                log(ast, "Spaghetti code detected: too much nesting");
            }
        }
    }

    int getNestingDepth(DetailAST ast) {
        int depth = 0;
        for (DetailAST child = ast.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getType() == TokenTypes.LITERAL_IF || child.getType() == TokenTypes.LITERAL_FOR
                    || child.getType() == TokenTypes.LITERAL_WHILE || child.getType() == TokenTypes.LITERAL_DO) {
                depth = Math.max(depth, 1 + getNestingDepth(child.findFirstToken(TokenTypes.SLIST)));
            }
        }
        return depth;
    }

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return null;
	}
}