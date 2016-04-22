package com.zenus.evaluator;

import java.util.Iterator;

import com.fathzer.soft.javaluator.Function;

public class FunctionEvaluator {
	@SuppressWarnings("unused")
	public static String evaluate(Function function, Iterator<String> arguments){
		if (function == LogicalExpressionEvaluator.IS_EMPTY) {
            String refId = arguments.next();
            String value = ValueMapper.getValue(refId);
            return value.isEmpty() + "";
        } else if (function == LogicalExpressionEvaluator.REGEX_MATCH) {
            String regEx = arguments.next();
            String refId = arguments.next();
            String value = ValueMapper.getValue(refId);
            // Test regular expression
            return "true";
        }
		return "";
	}
}
