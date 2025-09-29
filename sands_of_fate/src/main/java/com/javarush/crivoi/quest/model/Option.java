package com.javarush.crivoi.quest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Option {
    private String text;
    private String idNextNode;
}
