package com.sum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SumTest {

	@Test
	void testplus() {
		Sum sum=new Sum();
		assertEquals(300, sum.plus(100, 200));
	}

}