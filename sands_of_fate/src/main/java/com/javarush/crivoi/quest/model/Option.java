package com.javarush.crivoi.quest.model;

public class Option {
    private String text;
    private String idNextNode;

    public Option(String text, String idNextNode) {
        this.text = text;
        this.idNextNode = idNextNode;
    }

    public String getText() {
        return text;
    }

    public String getIdNextNode() {
        return idNextNode;
    }
}