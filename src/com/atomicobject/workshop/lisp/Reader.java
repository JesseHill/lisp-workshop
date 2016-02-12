package com.atomicobject.workshop.lisp;

import java.util.ArrayList;

import com.atomicobject.workshop.lisp.Types.LispBooleanReader;
import com.atomicobject.workshop.lisp.Types.LispIntegerReader;
import com.atomicobject.workshop.lisp.Types.LispList;
import com.atomicobject.workshop.lisp.Types.LispSymbolReader;
import com.atomicobject.workshop.lisp.Types.LispType;
import com.atomicobject.workshop.lisp.Types.LispTypeReader;

public class Reader {
	
	Tokenizer tokenizer;
	ArrayList<LispTypeReader> atomReaders = new ArrayList<LispTypeReader>();
	
    public static class ParseError extends RuntimeException {
    	
    	private static final long serialVersionUID = -5361526651871942263L;
    	
		public ParseError(String msg) {
            super(msg);
        }
    }
	
	public Reader(Tokenizer t) {
		this.tokenizer = t;
		atomReaders.add(new LispIntegerReader());
		atomReaders.add(new LispBooleanReader());
		atomReaders.add(new LispSymbolReader());
	}
	
	public LispType readAtom(TokenReader tokens) throws ParseError {
		for (LispTypeReader typeReader : atomReaders) {
			if (typeReader.matches(tokens)) return typeReader.read(tokens);
		}
        throw new ParseError("unrecognized token '" + tokens.peek() + "'");
	}
        
	public LispType readList(TokenReader reader, LispList list, char start, char end) throws ParseError {
        String token = reader.next();
        if (token.charAt(0) != start) throw new ParseError("expected '" + start + "'");

        while ((token = reader.peek()) != null && token.charAt(0) != end) {
            list.append(readForm(reader));
        }

        if (token == null) throw new ParseError("expected '" + end + "', got EOF");
        reader.next();
        return list;
	}
	
	public LispType readForm(TokenReader reader) throws ParseError {
        String token = reader.peek();
        if (token == null) { throw new ParseError("Expecting tokens"); }
        LispType form;

        switch (token.charAt(0)) {
            case '(': form = readList(reader, new LispList(), '(' , ')'); break;
            case ')': throw new ParseError("unexpected ')'");
            default:  form = readAtom(reader);
        }
        return form;
    }
	
	public LispType read(String tokens) throws ParseError {
       return readForm(new TokenReader(tokenizer.parse(tokens)));
	}
}
