package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispType;

public class Test7_GreaterAndLessThan {

	Environment environment = new CoreEnvironment();
	Reader reader = new Reader(new Tokenizer());
	Lispy lispy = new Lispy();

	@Test
	public void greater_than() {
		LispType output = lispy.evaluate(environment, reader.read("(> 1 2)"));
		assertEquals("false", output.toString());

		output = lispy.evaluate(environment, reader.read("(> 1 0)"));
		assertEquals("true", output.toString());

		// This case is optional, but nice to have
		output = lispy.evaluate(environment, reader.read("(> 1 0 2)"));
		assertEquals("false", output.toString());
		
		output = lispy.evaluate(environment, reader.read("(> \"a\" \"b\")"));
		assertEquals("false", output.toString());

		output = lispy.evaluate(environment, reader.read("(> \"b\" \"a\")"));
		assertEquals("true", output.toString());
	}

	@Test
	public void less_than() {
		LispType output = lispy.evaluate(environment, reader.read("(< 1 2)"));
		assertEquals("true", output.toString());

		output = lispy.evaluate(environment, reader.read("(< 1 0)"));
		assertEquals("false", output.toString());

		// These cases are optional, but nice to have
		output = lispy.evaluate(environment, reader.read("(< 1 0 2)"));
		assertEquals("false", output.toString());
		output = lispy.evaluate(environment, reader.read("(< 1 0 -1)"));
		assertEquals("true", output.toString());
		
		output = lispy.evaluate(environment, reader.read("(< \"a\" \"b\")"));
		assertEquals("true", output.toString());

		output = lispy.evaluate(environment, reader.read("(< \"b\" \"a\")"));
		assertEquals("false", output.toString());
	}
	
	/* We could add all kinds of tests for different types here ... */
	
}