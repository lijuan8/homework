package com.ada.homework;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testNull() {
		Assert.assertEquals(null, Utils.removeAllSpace(null));
	}
	
	@Test
	public void test() {
		Assert.assertEquals("", Utils.removeAllSpace(" "));
	}
	
	@Test
	public void testNormal() {
		Assert.assertEquals("abcdef", Utils.removeAllSpace("abc def "));
	}
	
	@Test
	public void testNormal3() {
		Assert.assertEquals("abcdef", Utils.removeAllSpace(" abc def"));
	}

	@Test
	public void testNormal2() {
		Assert.assertEquals("abcdef", Utils.removeAllSpace(" a bc de f "));
	}

}
