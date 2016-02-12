package com.atomicobject.workshop.lisp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.atomicobject.workshop.lisp.Types.LispType;

public class Test6_If {
	
	Environment environment = new CoreEnvironment();
	Reader reader = new Reader(new Tokenizer());
	Lispy lispy = new Lispy();

	@Test
	public void if_is_a_pretty_important_feature() {
		LispType output = lispy.evaluate(environment, reader.read("(if true 1 2)"));
		assertEquals("1", output.toString());

		output = lispy.evaluate(environment, reader.read("(if false 1 2)"));
		assertEquals("2", output.toString());

		output = lispy.evaluate(environment, reader.read("(if 1 (* 1 2) (* 2 4)"));
		assertEquals("2", output.toString());
		
		output = lispy.evaluate(environment, reader.read("(if false (* 1 2) (* 2 4)"));
		assertEquals("8", output.toString());

		output = lispy.evaluate(environment, reader.read("(if (= 1 1) (* 1 2) (* 2 4)"));
		assertEquals("2", output.toString());
		
		output = lispy.evaluate(environment, reader.read("(if (= 1 2) (* 1 2) (* 2 4)"));
		assertEquals("8", output.toString());
	}	
}