import java.util.ArrayList;
import java.util.List;

import com.zenus.evaluator.LogicalExpressionEvaluator;

public class Main {
	public Main() {
		
	}
	public static void main(String[] args) {
		LogicalExpressionEvaluator evaluator = new LogicalExpressionEvaluator();
    	boolean res = doIt(evaluator, "((X == \"T\")) or (((is_empty(Y) && (!(Z <= 10)))))");
    	System.out.println ("Result: " + res);
	}
	
	private static boolean doIt(LogicalExpressionEvaluator evaluator, String expression) {
		expression = expression.replace("\"", "").trim();
        List<String> sequence = new ArrayList<String>();
        evaluator.evaluate(expression, sequence);
        System.out.println ("Evaluation sequence for :"+expression);
        for (String string : sequence) {
          System.out.println (string);
        }
        System.out.println ();
        if (sequence.size() != 0) {
            return Boolean.parseBoolean(sequence.get(sequence.size() - 1));
        }
        return false;
    }
}