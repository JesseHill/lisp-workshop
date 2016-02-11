package com.atomicobject.workshop.lisp;

import java.util.HashMap;

import com.atomicobject.workshop.lisp.Types.LispSymbol;
import com.atomicobject.workshop.lisp.Types.LispType;

public class Environment {

	Environment outer = null;
	HashMap<String, LispType> data = new HashMap<String, LispType>();

	public Environment() {
	}

	public Environment(Environment outer) {
		this.outer = outer;
	}

	public Environment find(LispSymbol key) {
		if (data.containsKey(key.getValue())) {
			return this;
		} else if (outer != null) {
			return outer.find(key);
		} else {
			return null;
		}
	}

	public LispType get(LispSymbol key) throws Throwable {
		Environment e = find(key);
		if (e == null) {
			throw new Exception("Undefined symbol: '" + key.getValue() + "' not found");
		} else {
			return e.data.get(key.getValue());
		}
	}
	
	public Environment set(String key, String value) {
		data.put(key, new LispSymbol(value));
		return this;
	}	
	
	public Environment set(String key, LispType value) {
		data.put(key, value);
		return this;
	}	

	public Environment set(LispSymbol key, LispType value) {
		data.put(key.getValue(), value);
		return this;
	}
}
