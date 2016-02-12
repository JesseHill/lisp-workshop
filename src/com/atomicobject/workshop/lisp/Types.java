package com.atomicobject.workshop.lisp;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Types {

	public abstract static class LispType {
	}

	public static abstract class LispTypeReader {
		public abstract boolean matches(TokenReader reader);
		public abstract LispType read(TokenReader reader);
	}	
	
	public static abstract class LispFunction extends LispType {
		public abstract LispType execute(LispList args);
	}
	
	public static class LispBoolean extends LispType {
		boolean value;
		
		public LispBoolean(boolean value) {
			this.value = value;
		}
		
		public boolean getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return value ? "true" : "false";
		}
		
		public boolean equals(Object obj) {
			return LispBoolean.class.equals(obj.getClass()) && this.value == (((LispBoolean) obj).getValue());
		}
	}
	
	public static class LispBooleanReader extends LispTypeReader {
		Pattern pattern = Pattern.compile("(^true|false$)");
		
		public boolean matches(TokenReader reader) {
			return pattern.matcher(reader.peek()).find();
		}
		
		public LispType read(TokenReader reader) {
			return new LispBoolean(Boolean.parseBoolean(reader.next()));
		}
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
		public String toString() {
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
		
		public Integer getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return LispInteger.class.equals(obj.getClass()) && this.value == ((LispInteger) obj).getValue();
		}

		@Override
		public String toString() {
			return Integer.toString(value);
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
		List<LispType> values;
		
		public LispList() {
			values = new ArrayList<LispType>();
		}

		public LispList(List<LispType> values) {
			this.values = values;
		}

		public LispType first() {
			return values.get(0);
		}
		
		public LispList rest() {
			return new LispList(values.subList(1, size()));
		}
		
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

		@Override
		public String toString() {
			StringWriter writer = new StringWriter();
			writer.append("(");
			for (int i = 0; i < size(); i++) {
				writer.append(get(i).toString());
				if (i < (size() - 1)) writer.append(" ");
			}
			writer.append(")");
			return writer.toString();
		}
	}
}
