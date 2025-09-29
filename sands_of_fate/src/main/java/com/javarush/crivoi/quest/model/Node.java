package com.javarush.crivoi.quest.model;

import java.util.List;

public class Node {
    @SuppressWarnings("unused")
	private String id;
    private String text;
    private List<Option> options;

    public Node(String id, String text, List<Option> options) {
        this.id = id;
        this.text = text;
        this.options = options;
    }

    public void show() {
        System.out.println("\n" + text + "\n");
        for (Option option : options) {
            System.out.println(option.getText()); // ← aquí solo imprimimos el texto tal cual
        }
    }

    public List<Option> getOptions() {
        return options;
    }

	public String getText() {
		return text;
	}
}
