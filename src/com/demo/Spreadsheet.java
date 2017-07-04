package com.demo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Spreadsheet {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter no of test cases");
		int noOfTestCases = scanner.nextInt();
		int count = 1;
		Map<Integer, Map<String, String>> expressionMap = new LinkedHashMap<Integer, Map<String,String>>();
		while(noOfTestCases >= count) {
			int noOfExpressions = scanner.nextInt();
			scanner.nextLine();
			int expressionCount = 1;
			Map<String, String> celValueMap = new TreeMap<String, String>();
			while(noOfExpressions >= expressionCount) {
				String expression = scanner.nextLine();
				String[] expressionArray = expression.split("=");
				celValueMap.put(expressionArray[0].trim(), expressionArray[1].trim());
				expressionCount++;
			}
			expressionMap.put(noOfExpressions, celValueMap);
			count++;
		}
		scanner.close();
		for (Entry<Integer, Map<String, String>> entry : expressionMap.entrySet()) {
			for (Entry<String, String> expressionEntry : entry.getValue().entrySet()) {
				if(!expressionEntry.getValue().matches("\\d+")) {
					expressionEntry.setValue(String.valueOf(evaluate(expressionEntry.getValue(), entry.getValue())));
				}
			}
		}
		
		printValues(expressionMap);
	}

	private static int evaluate(String expression, Map<String, String> expressionMap) {
		String[] tokens = expression.split(" ");
		Stack<Integer> values = new Stack<Integer>();
		Stack<String> ops = new  Stack<String>();
		for (int i = tokens.length-1 ; i >= 0 ; i--) {
			String token = tokens[i];
			if(token.matches(".*[A-Z]+.*") && token.matches(".*\\d+.*")) {
				
				String tokenExpression = expressionMap.get(token);
				if(tokenExpression.matches("\\d+")) {
					token = tokenExpression;
				} else {
					String tokenKey = token;
					token = String.valueOf(evaluate(tokenExpression, expressionMap));
					expressionMap.put(tokenKey, token);
				}
			}
			if(token.matches("\\d+")) {
				values.push(Integer.parseInt(token));
			} else if(token.matches("[*/]")) {
				String previousToken = tokens[--i];
				if (previousToken.matches(".*[A-Z]+.*") && previousToken.matches(".*\\d+.*")) {
					String tokenExpression = expressionMap.get(previousToken);
					if (tokenExpression.matches("\\d+")) {
						previousToken = tokenExpression;
					} else {
						String tokenKey = previousToken;
						previousToken = String.valueOf(evaluate(tokenExpression, expressionMap));
						expressionMap.put(tokenKey, previousToken);
					} 
				}
				values.push(Integer.parseInt(previousToken));
				values.push(applyOp(token, values.pop(), values.pop()));
				
			} else if(token.matches("[-+]")){
				ops.push(token);
			}
		}
		while (!ops.empty()) {
			values.push(applyOp(ops.pop(), values.pop(), values.pop()));
		}
		return values.pop();
	}

	private static boolean hasPrecedence(String op1, String op2) {
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-")))
            return false;
        else
            return true;
	}

	private static int applyOp(String op, int b, int a) {
		switch (op) {
	    	case "+":
	    		return b + a;
	        case "-":
	            return b - a;
	        case "*":
	            return b * a;
	        case "/":
	            return b / a;
	        }
			return 0;
	    }

	 private static void printValues(Map<Integer, Map<String, String>> expressionMap) {
		 for (Entry<Integer, Map<String, String>> entry : expressionMap.entrySet()) {
				for (Entry<String, String> expressionEntry : entry.getValue().entrySet()) {
				System.out.println(expressionEntry.getKey() + " = " + expressionEntry.getValue());
				}
				System.out.println();
			}
		}
}
