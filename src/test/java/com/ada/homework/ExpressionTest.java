package com.ada.homework;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionTest {

	private Expression tested = new Expression();

	@Test
	public void testSimple() {
		String input = "add(1, 2)";
		Assert.assertEquals(3, tested.execute(input));
	}

	@Test
	public void testSecondEx() {
		String input = "add(1, mult(2, 3))";
		Assert.assertEquals(7, tested.execute(input));
	}

	
	@Test
	public void testTwoEx() {
		String input = "mult(add(2, 2), div(9, 3))";
		Assert.assertEquals(12, tested.execute(input));
	}
	
	@Test
	public void testLetSecondEx() {
		String input = "let(a, 5, add(a, a))";
		Assert.assertEquals(10, tested.execute(input));
	}
	
	
	@Test
	public void testLetSecondWithLet() {
		String input = "let(a, 5, let(b, mult(a, 10), add(b, a)))";
		Assert.assertEquals(55, tested.execute(input));
	}
	
	@Test
	public void testLetWithLets() {
		String input = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))";
		Assert.assertEquals(40, tested.execute(input));
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testInvalidExpression() {
		String input = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))";
		Assert.assertEquals(40, tested.execute(input));
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testLetWithOnly2Parameters() {
		String input = "let(a, let(b, 10), let(b, 20, add(a, b))";
		tested.execute(input);
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testLetWithOnly2Parameters2() {
		String input = "let(a, let(b, 10), let(b, add(a, b))";
		tested.execute(input);
	}
	
	

}
