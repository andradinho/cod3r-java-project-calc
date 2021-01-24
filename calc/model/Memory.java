package com.andradinho.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	private enum CommandType {
		CLEAR, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA;
	};

	private static final Memory instance = new Memory();
	
	private final List<MemoryObserver> observers = new ArrayList<>();
	
	private CommandType lastOperation = null;
	private boolean replace = false;
	private String currentText = "";
	private String textBuffer = "";
	
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
		
		if(commandType == null) {
			return;
		} else if(commandType == CommandType.CLEAR) {
			currentText = "";
			textBuffer = "";
			replace = false;
			lastOperation = null;
		} else if(commandType == CommandType.NUMBER 
				|| commandType == CommandType.COMMA) {
			currentText = replace ? text : currentText + text;
			replace = false;
		} else {
			replace = true;
			currentText = getOperationResult();
			textBuffer = currentText;
			lastOperation = commandType;
		}
		
		observers.forEach(o -> o.changedValue(getCurrentText()));
	}

	private String getOperationResult() {
		if(lastOperation == null) {
			return currentText;
		}
		
		double numberBuffer = Double.parseDouble(textBuffer.replace(",", "."));
		double currentNumber = Double.parseDouble(currentText.replace(",", "."));
		
		double result = 0;
		
		if(lastOperation == CommandType.SUM) {
			result = numberBuffer + currentNumber;
		} else if(lastOperation == CommandType.SUB) {
			result = numberBuffer - currentNumber;
		} else if(lastOperation == CommandType.MULT) {
			result = numberBuffer * currentNumber;
		} else if(lastOperation == CommandType.DIV) {
			result = numberBuffer / currentNumber;
		}
		
		String stringResult = Double.toString(result).replace(".", ",");
		boolean integer = stringResult.endsWith(",0");
		return integer ? stringResult.replace(",0", "") : stringResult;
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
			} else if(",".equals(text) 
					&& !currentText.contains(",")){
				return CommandType.COMMA;
			}
		}
		return null;
	}
}
