package com.andradinho.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {

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
	
	public void commandProcessing(String value) {
		
		
		currentText += value;
		observers.forEach(o -> o.changedValue(getCurrentText()));
	}
	
}
