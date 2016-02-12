package com.atomicobject.workshop.lisp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.atomicobject.workshop.lisp.Types.LispType;

public class Test9_Def {

	Environment environment = new CoreEnvironment();
	Reader reader = new Reader(new Tokenizer());
	Lispy lispy = new Lispy();

	@Test
	public void do_is_a_pretty_important_feature() {
		LispType output = lispy.evaluate(environment, reader.read("(do (def hi 1) hi)"));
		assertEquals("1", output.toString());

		output = lispy.evaluate(environment, reader.read("(do (def hi 1) (+ 1 hi))"));
		assertEquals("2", output.toString());

		output = lispy.evaluate(environment, reader.read("(do (def hi 1) (def hi 2) (+ 1 hi))"));
		assertEquals("3", output.toString());
	}
}