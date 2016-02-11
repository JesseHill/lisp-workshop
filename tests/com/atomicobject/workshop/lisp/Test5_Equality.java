package com.atomicobject.workshop.lisp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispInteger;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Test5_Equality {

	@Test
	public void inequality_is_terrible() {
		Environment environment = new CoreEnvironment();
		LispType output = null;
		try {
			LispList ast = new LispList();
			ast.append(new LispSymbol("="));
			ast.append(new LispInteger(5));
			ast.append(new LispInteger(6));
			output = new Lispy().evaluate(environment, ast);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertEquals("false", output.toString());
	}

	@Test
	public void equality_is_pretty_important() {
		Environment environment = new CoreEnvironment();
		LispType output = null;
		try {
			LispList ast = new LispList();
			ast.append(new LispSymbol("="));
			ast.append(new LispInteger(5));
			ast.append(new LispInteger(5));
			output = new Lispy().evaluate(environment, ast);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertEquals("true", output.toString());
	}	
}