package com.andradinho.calc.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Keyboard extends JPanel {
	
	private final Color COLOR_DARK_GRAY = new Color(68, 68, 68);
	private final Color COLOR_LIGHT_GRAY = new Color(99, 99, 99);
	private final Color COLOR_ORANGE = new Color(242, 163, 60);

	public Keyboard() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		// 1st line
		addButton("AC", COLOR_DARK_GRAY, c, 0, 0);
		addButton("+/-", COLOR_DARK_GRAY, c, 1, 0);
		addButton("%", COLOR_DARK_GRAY, c, 2, 0);
		addButton("/", COLOR_ORANGE, c, 3, 0);
		
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
		
		add(new Button("7", COLOR_LIGHT_GRAY));
		add(new Button("8", COLOR_LIGHT_GRAY));
		add(new Button("9", COLOR_LIGHT_GRAY));
		add(new Button("*", COLOR_ORANGE));
	}

	private void addButton(String text, Color color, GridBagConstraints c, int x, int y) {

		c.gridx = x;
		c.gridy = y;
		Button button = new Button(text, color);
		add(button, c);
	}
}
