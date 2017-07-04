package com.demo2;

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
				String[] expressionArray =expression.split("=");
				celValueMap.put(expressionArray[0].trim(), expressionArray[1].trim());
				expressionCount++;
			}
			expressionMap.put(count, celValueMap);
			count++;
		}
		scanner.close();
		for (Entry<Integer, Map<String, String>> entry : expressionMap.entrySet()) {
			for (Entry<String, String> expressionEntry : entry.getValue().entrySet()) {
				if(!expressionEntry.getValue().trim().matches("\\d")) {
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
		for (String token : tokens) {
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
			} else if(token.matches("[*/+-]")) {
				while (!ops.empty() && hasPrecedence(token, ops.peek()))
	                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
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

	 public static int applyOp(String op, int b, int a)
	    {
	        switch (op)
	        {
	        case "+":
	            return a + b;
	        case "-":
	            return a - b;
	        case "*":
	            return a * b;
	        case "/":
	            return a / b;
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