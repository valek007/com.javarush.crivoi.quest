package com.javarush.crivoi.quest.engine;

import java.util.Map;
import java.util.Scanner;

import com.javarush.crivoi.quest.builder.NodeFactory;
import com.javarush.crivoi.quest.model.Node;
import com.javarush.crivoi.quest.model.Option;

public class GameConsole {
    private static Map<String, Node> nodes;

    public GameConsole() {
    	nodes = NodeFactory.createNodes();
    }

	public void startQuest(Scanner scanner) {
        showNode("1", scanner);
    }

    private void showNode(String nodeId, Scanner scanner) {
        Node node = nodes.get(nodeId);
        node.show();

     // if there are no options → end
        if (node.getOptions().isEmpty()) {
            System.out.println("\nИгра окончена!");
            return;
        }

        while (true) {
            String option = scanner.nextLine().trim().toUpperCase();

            for (Option opt : node.getOptions()) {
                if (opt.getText().toUpperCase().startsWith(option + ")")) {
                    showNode(opt.getIdNextNode(), scanner);
                    return;
                }
            }
            System.out.println("Неверный выбор, попробуйте ещё раз.");
        }
    }
}
