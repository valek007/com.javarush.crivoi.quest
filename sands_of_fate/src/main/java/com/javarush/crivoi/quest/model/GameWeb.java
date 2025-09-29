package com.javarush.crivoi.quest.model;

import java.util.Map;

import com.javarush.crivoi.quest.builder.NodeFactory;

public class GameWeb {
    private final Map<String, Node> nodes;

    public GameWeb() {
        this.nodes = NodeFactory.createNodes();
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }
}

