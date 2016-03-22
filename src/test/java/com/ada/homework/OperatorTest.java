package com.ada.homework;

import org.junit.Assert;
import org.junit.Test;

public class OperatorTest {

	@Test
	public void test() {
		Assert.assertEquals(3, Operator.cal("add", 1, 2));
	}
	
	@Test
	public void testDiv() {
		Assert.assertEquals(1, Operator.cal("DIV", 2, 3));
	}
	
	@Test
	public void testMULT() {
		Assert.assertEquals(6, Operator.cal("MULT", 3, 2));
	}
	
	@Test
	public void testSUB() {
		Assert.assertEquals(-1, Operator.cal("SUB", 3, 2));
	}
	
	@Test(expected=java.lang.RuntimeException.class)
	public void testInvalidOp() {
		 Operator.cal("ADDMULT", 1, 2);
	}

}
