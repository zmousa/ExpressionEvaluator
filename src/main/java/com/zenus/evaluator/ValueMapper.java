package com.zenus.evaluator;

import java.util.HashMap;
import java.util.Map;

public class ValueMapper {
	public static Map<String, String> values = new HashMap<String, String>();
	static {
		values.put("X", "Y");
		values.put("Y", "v");
		values.put("Z", "15");
	}
	
	public static String getValue(String parameterName){
		if (values.containsKey(parameterName))
			return values.get(parameterName);
		return "";
	}
}
