package com.javarush.crivoi.quest.builder;

import java.util.HashMap;
import java.util.Map;

import com.javarush.crivoi.quest.model.AppConstants;
import com.javarush.crivoi.quest.model.Node;

public class NodeFactory {
	
	public static Map<String, Node> createNodes() {
        Map<String, Node> nodes = new HashMap<>();

        // --- Node 1 ---
        Node node1 = NodeBuilder.create("1")
                .withText(AppConstants.Questions.QUESTION_1)
                .addOption(AppConstants.Answers.ANSWER_1A, "2A")
                .addOption(AppConstants.Answers.ANSWER_1B, "2B")
                .addOption(AppConstants.Answers.ANSWER_1C, "2C")
                .build();

        // --- Node 2A ---
        Node node2A = NodeBuilder.create("2A")
                .withText(AppConstants.Questions.QUESTION_2A)
                .addOption(AppConstants.Answers.ANSWER_2A1, "END4")
                .addOption(AppConstants.Answers.ANSWER_2A2, "3A")
                .build();

        // --- Node 2B ---
        Node node2B = NodeBuilder.create("2B")
                .withText(AppConstants.Questions.QUESTION_2B)
                .addOption(AppConstants.Answers.ANSWER_2B1, "END4")
                .addOption(AppConstants.Answers.ANSWER_2B2, "3B")
                .build();

        // --- Node 2C ---
        Node node2C = NodeBuilder.create("2C")
                .withText(AppConstants.Questions.QUESTION_2C)
                .addOption(AppConstants.Answers.ANSWER_2C1, "END3")
                .addOption(AppConstants.Answers.ANSWER_2C2, "3C")
                .build();

        // --- Node 3A ---
        Node node3A = NodeBuilder.create("3A")
                .withText(AppConstants.Questions.QUESTION_3A)
                .addOption(AppConstants.Answers.ANSWER_3A1, "END1")
                .addOption(AppConstants.Answers.ANSWER_3A2, "END4")
                .build();

        // --- Node 3B ---
        Node node3B = NodeBuilder.create("3B")
                .withText(AppConstants.Questions.QUESTION_3B)
                .addOption(AppConstants.Answers.ANSWER_3B1, "END2")
                .addOption(AppConstants.Answers.ANSWER_3B2, "END2")
                .build();

        // --- Node 3C ---
        Node node3C = NodeBuilder.create("3C")
                .withText(AppConstants.Questions.QUESTION_3C)
                .addOption(AppConstants.Answers.ANSWER_3C1, "END3")
                .addOption(AppConstants.Answers.ANSWER_3C2, "END4")
                .build();

        // --- Endings ---
        Node end1 = NodeBuilder.create("END1")
                .withText(AppConstants.Endings.ENDING_1)
                .build();

        Node end2 = NodeBuilder.create("END2")
                .withText(AppConstants.Endings.ENDING_2)
                .build();

        Node end3 = NodeBuilder.create("END3")
                .withText(AppConstants.Endings.ENDING_3)
                .build();

        Node end4 = NodeBuilder.create("END4")
                .withText(AppConstants.Endings.ENDING_4)
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
