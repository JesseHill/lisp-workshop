package com.atomicobject.workshop.lisp;

import com.atomicobject.workshop.lisp.Types.LispFunction;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Lispy {

	public LispType evaluate(Environment environment, LispType ast) throws Throwable {
		if (ast instanceof LispSymbol) {
			return environment.get((LispSymbol) ast);
		}
		if (!(ast instanceof LispList)) return ast;
		LispList form = (LispList) ast;
		LispFunction function = (LispFunction) environment.get((LispSymbol) form.first());
//		LispList args = new LispList();
//		for (LispType next : form.rest().values) {
//			args.append(evaluate(environment, next));
//		}
		return function.execute(form.rest());
	}	
}
