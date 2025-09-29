package com.javarush.crivoi.quest.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Node {
    private String id;
    private String text;
    private List<Option> options;

    public void show() {
        System.out.println("\n" + text + "\n");
        for (Option option : options) {
            System.out.println(option.getText());
        }
    }
}
