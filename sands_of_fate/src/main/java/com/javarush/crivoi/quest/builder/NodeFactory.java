package com.javarush.crivoi.quest.builder;

import java.util.HashMap;
import java.util.Map;

import com.javarush.crivoi.quest.model.game.AppConstantsRus;
import com.javarush.crivoi.quest.model.game.Node;

public class NodeFactory {
	
	public static Map<String, Node> createNodes() {
        Map<String, Node> nodes = new HashMap<>();

        // --- Node 1 ---
        Node node1 = NodeBuilder.create("1")
                .withText(AppConstantsRus.Questions.QUESTION_1)
                .addOption(AppConstantsRus.Answers.ANSWER_1A, "2A")
                .addOption(AppConstantsRus.Answers.ANSWER_1B, "2B")
                .addOption(AppConstantsRus.Answers.ANSWER_1C, "2C")
                .build();

        // --- Node 2A ---
        Node node2A = NodeBuilder.create("2A")
                .withText(AppConstantsRus.Questions.QUESTION_2A)
                .addOption(AppConstantsRus.Answers.ANSWER_2A1, "END4")
                .addOption(AppConstantsRus.Answers.ANSWER_2A2, "3A")
                .build();

        // --- Node 2B ---
        Node node2B = NodeBuilder.create("2B")
                .withText(AppConstantsRus.Questions.QUESTION_2B)
                .addOption(AppConstantsRus.Answers.ANSWER_2B1, "END4")
                .addOption(AppConstantsRus.Answers.ANSWER_2B2, "3B")
                .build();

        // --- Node 2C ---
        Node node2C = NodeBuilder.create("2C")
                .withText(AppConstantsRus.Questions.QUESTION_2C)
                .addOption(AppConstantsRus.Answers.ANSWER_2C1, "END3")
                .addOption(AppConstantsRus.Answers.ANSWER_2C2, "3C")
                .build();

        // --- Node 3A ---
        Node node3A = NodeBuilder.create("3A")
                .withText(AppConstantsRus.Questions.QUESTION_3A)
                .addOption(AppConstantsRus.Answers.ANSWER_3A1, "END1")
                .addOption(AppConstantsRus.Answers.ANSWER_3A2, "END4")
                .build();

        // --- Node 3B ---
        Node node3B = NodeBuilder.create("3B")
                .withText(AppConstantsRus.Questions.QUESTION_3B)
                .addOption(AppConstantsRus.Answers.ANSWER_3B1, "END2")
                .addOption(AppConstantsRus.Answers.ANSWER_3B2, "END2")
                .build();

        // --- Node 3C ---
        Node node3C = NodeBuilder.create("3C")
                .withText(AppConstantsRus.Questions.QUESTION_3C)
                .addOption(AppConstantsRus.Answers.ANSWER_3C1, "END3")
                .addOption(AppConstantsRus.Answers.ANSWER_3C2, "END4")
                .build();

        // --- Endings ---
        Node end1 = NodeBuilder.create("END1")
                .withText(AppConstantsRus.Endings.ENDING_1)
                .build();

        Node end2 = NodeBuilder.create("END2")
                .withText(AppConstantsRus.Endings.ENDING_2)
                .build();

        Node end3 = NodeBuilder.create("END3")
                .withText(AppConstantsRus.Endings.ENDING_3)
                .build();

        Node end4 = NodeBuilder.create("END4")
                .withText(AppConstantsRus.Endings.ENDING_4)
                .build();

   	 	// Add all nodes to the map
        nodes.put("1", node1);
        nodes.put("2A", node2A);
        nodes.put("2B", node2B);
        nodes.put("2C", node2C);
        nodes.put("3A", node3A);
        nodes.put("3B", node3B);
        nodes.put("3C", node3C);
        nodes.put("END1", end1);
        nodes.put("END2", end2);
        nodes.put("END3", end3);
        nodes.put("END4", end4);

        return nodes;
    }

}

