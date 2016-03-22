package com.ada.homework;

public enum Operator {
	ADD, SUB, MULT, DIV, LET;

	public static int cal(String op, int first, int second) {
		switch (get(op)) {
		case ADD:
			return first + second;
		case SUB:
			return second - first;
		case MULT:
			return first * second;
		case DIV:
			return second / first;
		default:
			throw new RuntimeException("not supported operator");
		}
	}

	private static Operator get(String op) {
		if (op.equalsIgnoreCase(ADD.name())) {
			return ADD;
		} else if (op.equalsIgnoreCase(SUB.name())) {
			return SUB;
		} else if (op.equalsIgnoreCase(MULT.name())) {
			return MULT;
		} else if (op.equalsIgnoreCase(DIV.name())) {
			return DIV;
		} else if (op.equalsIgnoreCase(LET.name())){
			return LET;
		}
		throw new RuntimeException("Invalid operator: " + op);
	}

	public static boolean isLet(String op) {
		return op.equalsIgnoreCase(LET.name());
	}
}
