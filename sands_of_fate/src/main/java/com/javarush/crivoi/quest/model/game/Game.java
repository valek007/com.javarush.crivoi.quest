package com.javarush.crivoi.quest.model.game;

import java.util.Map;

import com.javarush.crivoi.quest.builder.NodeFactory;

public class Game {
    private final Map<String, Node> nodes;

    public Game() {
        this.nodes = NodeFactory.createNodes();
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }
}

