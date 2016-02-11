package com.atomicobject.workshop.lisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Readline {

	public static class EOFException extends Exception {
		private static final long serialVersionUID = -5280829411492774261L;
	}

	public static String readline(String prompt) throws EOFException, IOException {
		System.out.print(prompt);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String line = buffer.readLine();
		if (line == null) throw new EOFException();
		return line;
	}
}