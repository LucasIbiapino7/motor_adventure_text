package org.example.views;

import org.example.controllers.GameController;
import org.example.entities.Game;
import org.example.utils.GameLoader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Próximo passo: Checar game over. combate. escrever game

        Scanner scanner = new Scanner(System.in);

        Game game = GameLoader.load("/game_ninja.json");
        GameController gameController = new GameController(game);

        System.out.println(gameController.initial());

        System.out.println(gameController.startLocation());

        while (true){
            String command = scanner.nextLine();

            String response = gameController.command(command);

            System.out.println(response + "\n");

            System.out.println(gameController.currentLocation());
        }


    }
}