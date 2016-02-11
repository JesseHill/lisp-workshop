package com.atomicobject.workshop.lisp;

import com.atomicobject.workshop.lisp.Types.LispType;

public interface LispEvaluator {
	
	public LispType evaluate(Environment environment, LispType ast) throws Throwable;

}
