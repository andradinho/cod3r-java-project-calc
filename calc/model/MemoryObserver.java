package com.andradinho.calc.model;

@FunctionalInterface
public interface MemoryObserver {

	public void changedValue(String newValue);
}
