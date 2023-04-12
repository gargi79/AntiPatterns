package com.antipattern.detector.AntipatternDetector;

//import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public abstract class TypeCheckingDetector extends SpaghettiCodeDetector {

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE};
    }

    @Override
    public void visitToken(DetailAST ast) {
	super.visitToken(ast);
        DetailAST expression = ast.findFirstToken(TokenTypes.EXPR);
        if (expression != null) {
            DetailAST dot = expression.findFirstToken(TokenTypes.DOT);
            if (dot != null) {
                DetailAST methodCall = dot.findFirstToken(TokenTypes.METHOD_CALL);
                if (methodCall != null) {
                    DetailAST ident = methodCall.findFirstToken(TokenTypes.IDENT);
                    if (ident != null && "getClass".equals(ident.getText()))
                        log(ast, "Type checking anti-pattern detected: using 'getClass()' for type checking");
                }
            }
        }
    }
}
