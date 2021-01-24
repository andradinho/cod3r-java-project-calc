package com.andradinho.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	private enum CommandType {
		CLEAR, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA;
	};

	private static final Memory instance = new Memory();
	
	private final List<MemoryObserver> observers = new ArrayList<>();
	
	private String currentText = "";
	
	private Memory() {
		
	}

	public static Memory getInstance() {
		return instance;
	}
	
	public void addObserver(MemoryObserver observer) {
		observers.add(observer);
	}

	public String getCurrentText() {
		return currentText.isEmpty() ? "0" : currentText;
	}
	
	public void commandProcessing(String text) {
		
		CommandType commandType = commandTypeDetect(text);
		System.out.println(commandType);
		
		if("AC".equals(text)) {
			currentText = "";
		} else {
			currentText += text;
		}
		
		observers.forEach(o -> o.changedValue(getCurrentText()));
	}

	private CommandType commandTypeDetect(String text) {
		if(currentText.isEmpty() && text == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(text);
			return CommandType.NUMBER;
		} catch (NumberFormatException e) {
			// Whuen isn't number
			if("AC".equals(text)) {
				return CommandType.CLEAR;
			} else if("/".equals(text)){
				return CommandType.DIV;
			} else if("*".equals(text)){
				return CommandType.MULT;
			} else if("+".equals(text)){
				return CommandType.SUM;
			} else if("-".equals(text)){
				return CommandType.SUB;
			} else if("=".equals(text)){
				return CommandType.EQUAL;
			} else if(",".equals(text)){
				return CommandType.COMMA;
			}
		}
		return null;
	}
}
