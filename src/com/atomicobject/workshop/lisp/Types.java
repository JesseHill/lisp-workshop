package com.atomicobject.workshop.lisp;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Types {

	public static class LispType {
	}

	public static abstract class LispTypeReader {
		public abstract boolean matches(TokenReader reader);
		public abstract LispType read(TokenReader reader);
	}	
	
	public static class LispSymbol extends LispType {
		String value;

		public LispSymbol(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			return LispSymbol.class.equals(obj.getClass()) && this.value.equals(((LispSymbol) obj).getValue());
		}
	}
	
	public static class LispSymbolReader extends LispTypeReader {
		Pattern pattern = Pattern.compile("(^[^\"]*$)");
		
		public boolean matches(TokenReader reader) {
			return pattern.matcher(reader.peek()).find();
		}
		
		public LispType read(TokenReader reader) {
			return new LispSymbol(reader.next());
		}
	}	
	
	public static class LispInteger extends LispType {
		int value;

		public LispInteger(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return LispInteger.class.equals(obj.getClass()) && this.value == ((LispInteger) obj).getValue();
		}
	}

	public static class LispIntegerReader extends LispTypeReader {
		Pattern pattern = Pattern.compile("(^-?[0-9]+$)");
		
		public boolean matches(TokenReader reader) {
			return pattern.matcher(reader.peek()).find();
		}
		
		public LispType read(TokenReader reader) {
			return new LispInteger(Integer.parseInt(reader.next()));
		}
	}

	public static class LispList extends LispType {
		ArrayList<LispType> values = new ArrayList<LispType>();
		
		public int size() {
			return values.size();
		}

		public LispType get(int index) {
			return values.get(index);
		}
		
		public void append(LispType value) {
			values.add(value);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!LispList.class.equals(obj.getClass())) return false;
			LispList other = (LispList) obj;
			if (size() != other.size()) return false;
			for (int i = 0; i < size(); i++) {
				if (get(i) == null) {
					if (other.get(i) != null) return false;
					continue;
				}
				if (!get(i).equals(other.get(i))) return false;
			}
			return true;
		}		
	}
}
