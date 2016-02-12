package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispType;

public class Test5_Equality {

	Reader reader = new Reader(new Tokenizer());

	@Test
	public void inequality_is_terrible() {
		Environment environment = new CoreEnvironment();
		LispType output = new Lispy().evaluate(environment, reader.read("(= 5 6)"));
		assertEquals("false", output.toString());
	}

	@Test
	public void equality_is_pretty_important() {
		Environment environment = new CoreEnvironment();
		LispType output = new Lispy().evaluate(environment, reader.read("(= 5 5)"));
     	assertEquals("true", output.toString());
	}	
	
	/* We could add all kinds of tests for different types here ... */
}