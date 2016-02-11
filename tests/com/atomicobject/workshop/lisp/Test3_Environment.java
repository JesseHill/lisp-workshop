package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Test3_Environment {

	@Test
	public void symbols_should_resolve_as_expected() {
		Environment environment = new Environment();
		environment.set("hi", "Hello");
		LispType output = null;
		try {
			output = new Lispy().evaluate(environment, new LispSymbol("hi"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertEquals("Hello", output.toString());
	}

}
