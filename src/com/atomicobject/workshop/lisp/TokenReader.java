package com.atomicobject.workshop.lisp;

import java.util.ArrayList;
import java.util.Arrays;

public class TokenReader {

	ArrayList<String> tokens;
	int position;
	
	public TokenReader(String[] tokens) {
		this.tokens = new ArrayList<String>(Arrays.asList(tokens));
		this.position = 0;
	}

    public String peek() {
        if (position >= tokens.size()) {
            return null;
        } else {
            return tokens.get(position);
        }
    }
    
    public String next() {
        return tokens.get(position++);
    }		
}