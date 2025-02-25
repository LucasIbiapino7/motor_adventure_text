package org.example.utils;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.example.entities.*;

import java.io.InputStream;

public class GameLoader {

    public static Game load(String path){
        try(InputStream inputStream = GameLoader.class.getResourceAsStream(path);
            JsonReader jsonReader = Json.createReader(inputStream)){

            JsonObject jsonObject = jsonReader.readObject();
            Game game = new Game();
            game.setTitle(jsonObject.getString("title"));
            game.setDescription(jsonObject.getString("description"));
            game.setAuthor(jsonObject.getString("author"));
            game.setStartLocationId(jsonObject.getInt("startLocationId"));

            // Lembrar de tratar os opcionais
            if (!jsonObject.isNull("max_itens")){
                game.setMax_itens(jsonObject.getInt("max_itens"));
            }
            if (!jsonObject.isNull("max_turns_easy")){
                game.setMax_turns_easy(jsonObject.getInt("max_turns_easy"));
            }
            if (!jsonObject.isNull("max_turns_normal")){
                game.setMax_turns_normal(jsonObject.getInt("max_turns_normal"));
            }
            if (!jsonObject.isNull("max_turns_hard")){
                game.setMax_turns_hard(jsonObject.getInt("max_turns_hard"));
            }

            game.setAttack(jsonObject.getInt("attack"));
            game.setDefense(jsonObject.getInt("defense"));
            game.setLife(jsonObject.getInt("life"));

            // Tratando Locations
            JsonArray locations = jsonObject.getJsonArray("locations");
            for (int i = 0; i < locations.size(); i++) {
                JsonObject locationJson = locations.getJsonObject(i);
                Location location = new Location();
                location.setId(locationJson.getInt("id"));
                location.setName(locationJson.getString("name"));
                location.setDescription(locationJson.getString("description"));

                // Tratando exits
                JsonArray exits = locationJson.getJsonArray("exits");
                if (exits != null) {
                    for (int j = 0; j < exits.size(); j++) {
                        JsonObject exitJson = exits.getJsonObject(j);
                        Exit exit = new Exit();
                        exit.setDirection(exitJson.getString("direction"));
                        exit.setTargetLocationId(exitJson.getInt("targetLocationId"));
                        exit.setDescription(exitJson.getString("description"));
                        exit.setInactive(exitJson.getBoolean("inactive"));
                        location.addExit(exit);
                    }
                }

                // Tratando items
                JsonArray items= locationJson.getJsonArray("items");
                if (items != null) {
                    for (int j = 0; j < items.size(); j++) {
                        JsonObject itemJson = items.getJsonObject(j);
                        Item item = new Item();
                        item.setId(itemJson.getInt("id"));
                        item.setName(itemJson.getString("name"));
                        item.setDescription(itemJson.getString("description"));
                        item.setCan_take(itemJson.getBoolean("can_take"));
                        item.setInactive(itemJson.getBoolean("inactive"));
                        location.addItem(item);
                    }
                }

                JsonArray npcs = locationJson.getJsonArray("npcs");
                if (npcs != null) {
                    for (int j = 0; j < npcs.size(); j++) {
                        JsonObject npcJson = npcs.getJsonObject(j);
                        Npc npc = new Npc();
                        npc.setId(npcJson.getInt("id"));
                        npc.setName(npcJson.getString("name"));
                        npc.setDescription(npcJson.getString("description"));
                        npc.setInactive(npcJson.getBoolean("inactive"));

                        // Tratando os dialogues
                        JsonArray dialogues = npcJson.getJsonArray("dialogues");
                        if (dialogues != null) {
                            for (int k = 0; k < dialogues.size(); k++) {
                                JsonObject dialogueJson = dialogues.getJsonObject(k);
                                Dialogue dialogue = new Dialogue();
                                dialogue.setText(dialogueJson.getString("text"));

                                // Tratando os responses (result)
                                JsonArray responses = dialogueJson.getJsonArray("responses");
                                if (responses != null) {
                                    for (int l = 0; l < responses.size(); l++) {
                                        JsonObject resultJson = responses.getJsonObject(l);
                                        JsonObject result = resultJson.getJsonObject("result");

                                        Result resultGame = new Result();

                                        resultGame.setActive(result.getJsonArray("active"));
                                        resultGame.setLose_item(result.getJsonArray("lose_item"));

                                        dialogue.addResult(resultGame);
                                    }
                                }

                                npc.addDialogue(dialogue);
                            }
                        }
                        location.addNpc(npc);
                    }
                }

                JsonArray puzzles = locationJson.getJsonArray("puzzles");
                if (puzzles != null) {
                    for (int j = 0; j < puzzles.size(); j++) {
                        JsonObject puzzleJson = puzzles.getJsonObject(j);
                        Puzzle puzzle = new Puzzle();
                        puzzle.setId(puzzleJson.getInt("id"));
                        puzzle.setDescription(puzzleJson.getString("description"));

                        // Processa o objeto solution
                        JsonObject solutionJson = puzzleJson.getJsonObject("solution");
                        Solution solution = new Solution();
                        solution.setRequiredItems(solutionJson.getJsonArray("requiredItems")); // ajuste conforme sua classe
                        solution.setActions(solutionJson.getString("actions"));
                        puzzle.setSolution(solution);

                        // Processa o objeto result
                        JsonObject resultJson = puzzleJson.getJsonObject("result");
                        Result result = new Result();
                        result.setActive(resultJson.getJsonArray("active"));
                        result.setLose_item(resultJson.getJsonArray("lose_item"));
                        result.setLose_life(resultJson.getInt("lose_life"));
                        puzzle.setResult(result);

                        location.addPuzzle(puzzle);
                    }
                }

                JsonArray enemies = locationJson.getJsonArray("enemies");
                if (enemies != null) {
                    for (int j = 0; j < enemies.size(); j++) {
                        JsonObject enemyJson = enemies.getJsonObject(j);
                        Enemy enemy = new Enemy();
                        enemy.setAttack(enemyJson.getInt("attack"));
                        enemy.setDefense(enemyJson.getInt("defense"));

                        JsonObject resultJson = enemyJson.getJsonObject("result");
                        Result result = new Result();
                        result.setActive(resultJson.getJsonArray("active"));
                        result.setLose_item(resultJson.getJsonArray("lose_item"));
                        result.setLose_life(resultJson.getInt("lose_life"));
                        enemy.setResult(result);

                        location.addEnemy(enemy);
                    }
                }

                game.addLocation(location);
            }

            return game;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
