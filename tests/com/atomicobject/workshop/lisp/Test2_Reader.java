package com.atomicobject.workshop.lisp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atomicobject.workshop.lisp.Types.LispInteger;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Test2_Reader {

	Reader reader = new Reader(new Tokenizer());

	@Test
	public void reader_should_return_expected_results() {
		LispType output = reader.read("(do (def val 10) (* val val))");
		
		LispList expression1 = new LispList();
		expression1.append(new LispSymbol("def"));
		expression1.append(new LispSymbol("val"));
		expression1.append(new LispInteger(10));
		
		LispList expression2 = new LispList();
		expression2.append(new LispSymbol("*"));
		expression2.append(new LispSymbol("val"));
		expression2.append(new LispSymbol("val"));

		LispList expected = new LispList();
		expected.append(new LispSymbol("do"));
		expected.append(expression1);		
		expected.append(expression2);
				
		assertEquals(expected, output);
	}

}
