package com.javarush.crivoi.quest.builder;

import java.util.ArrayList;
import java.util.List;

import com.javarush.crivoi.quest.model.game.Node;
import com.javarush.crivoi.quest.model.game.Option;

public class NodeBuilder {
    private String id;
    private String text;
    private List<Option> options = new ArrayList<>();

    private NodeBuilder(String id) {
        this.id = id;
    }

    public static NodeBuilder create(String id) {
        return new NodeBuilder(id);
    }

    public NodeBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public NodeBuilder addOption(String optionText, String nextNodeId) {
        this.options.add(new Option(optionText, nextNodeId));
        return this;
    }

    public Node build() {
        return new Node(id, text, options);
    }
}
