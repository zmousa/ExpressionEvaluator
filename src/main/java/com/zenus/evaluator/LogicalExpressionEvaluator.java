package com.zenus.evaluator;


import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;
import java.util.List;

public class LogicalExpressionEvaluator extends AbstractEvaluator<String> {
    public final static Operator AND = new Operator("and", 2, Operator.Associativity.LEFT, 3);
    public final static Operator OR = new Operator("or", 2, Operator.Associativity.LEFT, 3);
    public final static Operator AND_SIGN = new Operator("&&", 2, Operator.Associativity.LEFT, 3);
    public final static Operator OR_SIGN = new Operator("||", 2, Operator.Associativity.LEFT, 3);
    
    public final static Operator NOT = new Operator("!", 1, Operator.Associativity.LEFT, 1);

    public final static Operator EQUAL = new Operator("==", 2, Operator.Associativity.LEFT, 2);
    public final static Operator NEQUAL = new Operator("!=", 2, Operator.Associativity.LEFT, 2);
    
    public final static Operator GT = new Operator(">", 2, Operator.Associativity.LEFT, 2);
    public final static Operator GTE = new Operator(">=", 2, Operator.Associativity.LEFT, 2);
    public final static Operator LT = new Operator("<", 2, Operator.Associativity.LEFT, 2);
    public final static Operator LTE = new Operator("<=", 2, Operator.Associativity.LEFT, 2);

    private static final Parameters PARAMETERS;

    public static Function IS_EMPTY = new Function("is_empty", 1);
    public static Function REGEX_MATCH = new Function("regexMatch", 2);

    static {
        // Create the evaluator's parameters
        PARAMETERS = new Parameters();
        // Add the supported operators
        PARAMETERS.add(AND);
        PARAMETERS.add(OR);
        PARAMETERS.add(AND_SIGN);
        PARAMETERS.add(OR_SIGN);
        PARAMETERS.add(EQUAL);
        PARAMETERS.add(NEQUAL);
        PARAMETERS.add(NOT);
        PARAMETERS.add(GT);
        PARAMETERS.add(GTE);
        PARAMETERS.add(LT);
        PARAMETERS.add(LTE);
        // Add the supported functions
        PARAMETERS.add(IS_EMPTY);
        PARAMETERS.add(REGEX_MATCH);
        // Add the parentheses
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
        PARAMETERS.addFunctionBracket(BracketPair.PARENTHESES);
    }

    public LogicalExpressionEvaluator() {
        super(PARAMETERS);
    }

    @Override
    protected String toValue(String literal, Object evaluationContext) {
        return literal;
    }

    private boolean getValue(String literal) {
        if ("true".equals(literal) || literal.endsWith("=true"))
        	return true;
        else if ("false".equals(literal) || literal.endsWith("=false")) 
        	return false;
        throw new IllegalArgumentException("Unknown literal : " + literal);
    }

    private boolean evaluateExpression(Operator operator, String refId, String value){
    	return ValueComparator.evaluateExpression(operator, refId, value);
    }

    @Override
    protected String evaluate(Operator operator, Iterator<String> operands,
                              Object evaluationContext) {
        @SuppressWarnings("unchecked")
		List<String> tree = (List<String>) evaluationContext;
        String o1 = operands.next();
        String o2 = "";
        Boolean result;
        if (operator == NOT) {
            result = !Boolean.parseBoolean(o1);
        } else {
        	o2 = operands.next();
	        if (operator == OR || operator == OR_SIGN) {
	            result = getValue(o1) || getValue(o2);
	        } else if (operator == AND || operator == AND_SIGN) {
	            result = getValue(o1) && getValue(o2);
	        } else if (operator == EQUAL || operator == NEQUAL) {
	            result = evaluateExpression(operator, o1, o2);
	        } else if (operator == GT || operator == GTE || operator == LT || operator == LTE) {
	            result = evaluateExpression(operator, o1, o2);
	        } else {
	            throw new IllegalArgumentException();
	        }
        }
        String eval = "("+o1+" "+operator.getSymbol()+" "+o2+")="+result;
        //String eval = result.toString();
        tree.add(eval);
        return eval;
    }

    @Override
    protected String evaluate(Function function, Iterator<String> arguments, Object evaluationContext) {
        if (function == IS_EMPTY || function == REGEX_MATCH) {
            return FunctionEvaluator.evaluate(function, arguments);
        } else {
            return super.evaluate(function, arguments, evaluationContext);
        }
    }
}
