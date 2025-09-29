package com.javarush.crivoi.quest.ui;

import java.util.Scanner;

import com.javarush.crivoi.quest.engine.GameConsole;

public class Presentation {
    private GameConsole game = new GameConsole();

    public void runGame() {
        menu();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    System.out.println("Игра началась! Ваше приключение в пустыне начинается...");
                    game.startQuest(scanner);
                    continueOption(scanner);
                } else if (input.equals("2")) {
                    System.out.println("Загрузка сохранённой игры... (функция пока недоступна)");
                } else if (input.equals("3")) {
                    System.out.println("Выход из игры. До свидания!");
                    break;
                } else {
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            }
        }
    }

    private void menu() {
        System.out.println("Добро пожаловать в 'Пески Судьбы'!");
        System.out.println("1. Начать новую игру");
        System.out.println("2. Загрузить игру");
        System.out.println("3. Выход");
    }

    private void continueOption(Scanner sc) {
        System.out.println("Хотите вернуться в меню? yes/no");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            System.out.println("\n");
            menu();
        } else {
            System.out.println("Выход из игры. До свидания!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Presentation().runGame();
    }
}
