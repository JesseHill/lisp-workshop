package com.atomicobject.workshop.lisp;

import java.io.IOException;

import com.atomicobject.workshop.lisp.Types.LispType;

public class REPL {

	Environment environment;
	Reader reader;
	
	public REPL() {
		environment = new Environment();
		reader = new Reader(new Tokenizer());
	}
	
	public LispType read(String str) throws Throwable {
        return reader.read(str);
    }

    public LispType eval(Environment env, LispType ast) {
        return ast;
    }

    public String print(LispType exp) {
        return exp.toString();
    }

    public LispType readAndEval(Environment env, String str) throws Throwable {
        return eval(env, read(str));
    }
    
    public static void main(String[] args) throws Throwable {
        String prompt = "> ";
        REPL repl = new REPL();
        while (true) {
            String line;
            try {
                line = Readline.readline(prompt);
                if (line == null) { continue; }
            } catch (Readline.EOFException e) {
                break;
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                break;
            }
            try {
                System.out.println(repl.print(repl.readAndEval(null, line)));
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
                continue;
            }
        }
    }
}
