package com.ada.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Expression {

	private static final Logger logger = Logger.getLogger(Expression.class);
	Stack<String> stack = new Stack<String>();
	Stack<Integer> values = new Stack<Integer>();

	public int execute(String input) {
		String nospace = Utils.removeAllSpace(input);
		if (StringUtils.isEmpty(nospace)) {
			throw new IllegalArgumentException("input can't be null or empty");
		}
		Map<String, Integer> variables = new HashMap<String, Integer>();
		return execute(nospace, variables);
	}

	int execute(String input, Map<String, Integer> variables) {
		int i = 0, l = input.length();
		char c;
		while (i < l) {
			int k = i;
			while ((c = input.charAt(k)) == ' ') {
				k++;
				continue;
			}
			while ((c = input.charAt(k)) != '(' && c != ',' && c != ')')
				k++;
			String word = input.substring(i, k);
			if (StringUtils.isEmpty(word)) {
				i = k + 1;
				continue;
			}
			if (c == '(') {
				if (Operator.isLet(word)) {
					int endoflet = endofexp(input, k);
					int value = processLet(word + input.substring(k, endoflet), variables);
					k = endoflet;
					values.push(value);
				} else {
					stack.push(word);
				}
			} else {
				if (variables.get(word) != null) {
					values.push(variables.get(word));
				} else {
					values.push(Integer.valueOf(word));
				}
				if (c == ')') {
					cal(stack, values, variables);
				}
			}
			i = k + 1;
		}
		cal(stack, values, variables);
		if (values.size() == 1) {
			return values.pop();
		}
		logger.error("input is not a valid expression");
		throw new RuntimeException("something is not right");
	}

	int endofexp(String input, int from) {
		int i = from;
		int count = 0;
		while (i < input.length()) {
			char c = input.charAt(i);
			i++;
			if (c == '(') {
				count++;
				continue;
			} else if (c == ')') {
				count--;
				if (count == 0) {
					return i;
				}
			}
		}
		throw new RuntimeException("let is not right");
	}

	private void cal(Stack<String> stack, Stack<Integer> values, Map<String, Integer> variables) {
		while (!stack.isEmpty() && values.size() > 1) {
			values.push(Operator.cal(stack.pop(), values.pop(), values.pop()));
			if (values.size() == 1) {
				break;
			}
		}
	}

	private int processLet(String input, Map<String, Integer> variables) {
		int comma = input.indexOf(",");
		if (comma < 0) {
			throw new RuntimeException("invalid let");
		}
		//first parameter for let 
		String k = input.substring(4, comma);
		//second 
		int second = input.indexOf(",", comma + 1);
		if (second < 0) {
			throw new RuntimeException("invalid let");
		}
		String v = input.substring(comma + 1, second);
		int endoflet = second;
		if (v.contains("(")) {
			int p = input.indexOf("(", comma + 1);
			endoflet = endofexp(input, p);
			second = endoflet;
			String expression = input.substring(comma + 1, endoflet);
			int result = execute(expression, variables);
			variables.put(k, result);
		} else if (StringUtils.isNumeric(v)) {
			variables.put(k, Integer.valueOf(v));
		}
		//third
		String expression = input.substring(second, input.length() - 1);
		if (expression.startsWith(Operator.LET.name())) {
			int result = processLet(expression, variables);
			return result;
		}
		return execute(expression, variables);
	}

}
