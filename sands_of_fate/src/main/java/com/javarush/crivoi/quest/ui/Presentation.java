package com.javarush.crivoi.quest.ui;

import java.util.Scanner;

import com.javarush.crivoi.quest.engine.GameConsole;
import com.javarush.crivoi.quest.model.AppConstantsRus;

public class Presentation {
    private GameConsole game = new GameConsole();

    public void runGame() {
        menu();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        System.out.println(AppConstantsRus.Presentation.GAME_STARTED);
                        game.startQuest(scanner);
                        continueOption(scanner);
                        break;
                    case "2":
                        System.out.println(AppConstantsRus.Presentation.LOAD_GAME);
                        break;
                    case "3":
                        System.out.println(AppConstantsRus.Presentation.EXIT_MESSAGE);
                        return;
                    default:
                        System.out.println(AppConstantsRus.Presentation.INVALID_INPUT);
                }
            }
        }
    }

    private void menu() {
        System.out.println(AppConstantsRus.Presentation.WELCOME_MESSAGE);
        System.out.println(AppConstantsRus.Presentation.MENU_START);
        System.out.println(AppConstantsRus.Presentation.MENU_LOAD);
        System.out.println(AppConstantsRus.Presentation.MENU_EXIT);
    }

    private void continueOption(Scanner sc) {
        System.out.println(AppConstantsRus.Presentation.CONTINUE_OPTION);
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            System.out.println("\n");
            menu();
        } else {
            System.out.println(AppConstantsRus.Presentation.EXIT_MESSAGE);
            System.exit(0);
        }
    }
}
