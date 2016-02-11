package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispFunction;
import com.atomicobject.workshop.lisp.Types.LispInteger;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Test4_Multiplication {

	@Test
	public void functions_should_execute() {
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
		LispType output = null;
		try {
			LispList ast = new LispList();
			ast.append(new LispSymbol("*"));
			ast.append(new LispInteger(5));
			ast.append(new LispInteger(6));
			output = new Lispy().evaluate(environment, ast);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertEquals("30", output.toString());
	}

}
