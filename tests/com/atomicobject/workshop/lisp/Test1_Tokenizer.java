package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Test1_Tokenizer {
	
	Tokenizer tokenizer = new Tokenizer();

	@Test
	public void tokenize_should_return_expected_results() {
		String[] output = tokenizer.parse("(def val 10) (* val val)");
		String[] expected = new String[] {"(", "def", "val", "10", ")", "(", "*", "val", "val", ")"};
		assertArrayEquals(output, expected);
	}

}
