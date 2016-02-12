package com.atomicobject.workshop.lisp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispFunction;
import com.atomicobject.workshop.lisp.Types.LispInteger;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Test4_Multiplication {
	
	Reader reader = new Reader(new Tokenizer());

	private Environment getEnvironment() {
		Environment environment = new Environment();
		environment.set("*", new LispFunction() {
			private LispType impl(LispList args) {
				if (args.size() == 1) return args.first();
				int value = ((LispInteger) args.first()).getValue() * ((LispInteger) impl(args.rest())).getValue();
				return new LispInteger(value);
			}
			
			@Override
			public LispType execute(LispList args) {
				if (args.size() == 0) throw new IllegalArgumentException("At least one value needed");
				return impl(args);				
			}			
		});
		return environment;
	}
	
	
	@Test
	public void functions_should_execute() {
		LispType output = new Lispy().evaluate(getEnvironment(), reader.read("(* 5 6)"));
		assertEquals("30", output.toString());
	}

	@Test
	public void functions_should_evaulate_args_before_executing() {
		LispType output = new Lispy().evaluate(getEnvironment(), reader.read("(* 5 (* 4 5))"));
		assertEquals("100", output.toString());
	}	
}
