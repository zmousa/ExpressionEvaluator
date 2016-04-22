package com.zenus.evaluator;

import com.fathzer.soft.javaluator.Operator;

public class ValueComparator {
	public static boolean evaluateExpression(Operator operator, String refId, String value){
        String questionValue = ValueMapper.getValue(refId).trim();
        value = value.replace("\"", "").trim();
        if (operator == LogicalExpressionEvaluator.EQUAL){
            return questionValue.equals(value);
        } else if (operator == LogicalExpressionEvaluator.NEQUAL){
            return !questionValue.equals(value);
        } else if (operator == LogicalExpressionEvaluator.GT || operator == LogicalExpressionEvaluator.GTE
            || operator == LogicalExpressionEvaluator.LT || operator == LogicalExpressionEvaluator.LTE){
            return compareStringNumbers(questionValue, value, operator);
        }
        return false;
    }
	
	private static boolean compareStringNumbers(String num1, String num2, Operator operator){
        try {
            int number1 = Integer.parseInt(num1);
            int number2 = Integer.parseInt(num2);
            if (operator == LogicalExpressionEvaluator.GT) {
                return number1 > number2;
            } else if (operator == LogicalExpressionEvaluator.GTE) {
                return number1 >= number2;
            } else if (operator == LogicalExpressionEvaluator.LT) {
                return number1 < number2;
            } else if (operator == LogicalExpressionEvaluator.LTE) {
                return number1 <= number2;
            }
        } catch (NumberFormatException e){
            return false;
        }
        return false;
    }
}
