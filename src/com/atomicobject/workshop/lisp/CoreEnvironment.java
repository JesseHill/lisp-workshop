package com.atomicobject.workshop.lisp;

import com.atomicobject.workshop.lisp.Types.LispFunction;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispType;

public class CoreEnvironment extends Environment {

	public CoreEnvironment() {
		super();
		set("echo", echo());
	}

	public LispFunction echo() {
		return new LispFunction() {
			@Override
			public LispType execute(LispList args) {
				return args;
			}
		};
	}
}
