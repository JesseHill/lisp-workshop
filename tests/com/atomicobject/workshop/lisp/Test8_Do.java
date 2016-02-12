package com.atomicobject.workshop.lisp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.atomicobject.workshop.lisp.Types.LispType;

public class Test8_Do {

	Environment environment = new CoreEnvironment();
	Reader reader = new Reader(new Tokenizer());
	Lispy lispy = new Lispy();

	@Test
	public void do_is_a_pretty_important_feature() {
		LispType output = lispy.evaluate(environment, reader.read("(do 1 2)"));
		assertEquals("2", output.toString());

		output = lispy.evaluate(environment, reader.read("(do (* 1 2) (* 2 4))"));
		assertEquals("8", output.toString());
	}
}