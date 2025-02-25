package org.example.controllers;

import org.example.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {
    private final Game game;
    private Integer round;
    private boolean limitItems;
    private Location currentLocation;
    private final List<Item> inventario = new ArrayList<>();
    private static final List<String> commands = List.of("pegar", "usar", "ajuda", "help", "inventario", "ir", "falar");

    public GameController(Game game) {
        this.game = game;
        if (game.getMax_itens() != null){
            limitItems = true;
        }
    }

    public String command(String command) {
        String[] split = command.split(" ");
        if (commands.contains(split[0])){
            if (split[0].equalsIgnoreCase("ir")){
                return commandIr(split[1]);
            } else if (split[0].equalsIgnoreCase("pegar")) {
                return commandPegar(split[1]);
            } else if (split[0].equalsIgnoreCase("inventario")) {
                System.out.println(inventario);
                return "";
            } else if (split[0].equalsIgnoreCase("ajuda") || split[0].equalsIgnoreCase("help")) {
                return commandAjuda();
            } else if (split[0].equalsIgnoreCase("falar")) {
                return commandFalar(split[1]);
            } else if (split[0].equalsIgnoreCase("usar")) {
                return commandUsar(split[1]);
            }
        }
        return "comando inválido!";
    }

    private String commandFalar(String npcName) {
        for (Npc npc : currentLocation.getNpcs()) {
            if (npcName.equalsIgnoreCase(npc.getName())){
                if (npc.getDialogues().size() > 1){
                    return "test";
                }else {
                    analyzeResult(npc.getDialogues().get(0).getResponses());
                    return npc.getDialogues().get(0).getText();
                }
            }
        }
        return "Essa pessoa não está no ambiente!";
    }

    /**
     * Responsável por retornar uma String que represanta os comandos do jogo. É usada quando o usuário usa o comando
     * "ajuda" ou "help"
     * @return - String que representa os comandos do jogo
     */
    private String commandAjuda() {
        return "Aqui está uma lista dos comandos que você pode usar: \n" +
                "ir <direção>\n" +
                "pegar <item>\n" +
                "usar <item>\n" +
                "falar <npc>" +
                "inventario - para ver seus itens";
    }

    /**
     * Responsável pela lógica de pegar um item, verificar se ele pode ser pego e adicionar ao inventário.
     * É chamada quando o usuário usa o comando "pegar"
     * @param itemName - nome do item
     * @return - String indicando se pegou ou não o item
     */
    private String commandPegar(String itemName) {
        for (Item item : currentLocation.getItems()) {
            if (itemName.equalsIgnoreCase(item.getName())){
                if (!item.getCan_take()){
                    return "você não pode pegar esse item ainda!";
                }
                if (verifyLimitItems()){
                    return "você está com o inventário cheio!";
                }
                inventario.add(item);
                item.setInactive(true);
                return "Você pegou o item: " + itemName;
            }
        }
        return "Objeto não encontrado!";
    }

    private boolean verifyLimitItems() {
        if (limitItems){
            return inventario.size() >= game.getMax_itens();
        }
        return false;
    }

    /**
     * Responsável pela lógica de ir para uma nova Location, caso a direção informada seja válida. Atualiza a location
     * atual. É chamada quando o usuário usa o comando "ir"
     * @param direction - direção desejada
     * @return - String indicando a mudança ou não de ambiente
     */
    private String commandIr(String direction) {
        for (Exit exit : currentLocation.getExits()) {
            if (direction.equalsIgnoreCase(exit.getDirection())){
                currentLocation = findLocation(exit.getTargetLocationId());
                return "indo para " + currentLocation.getName();
            }
        }
        return "direção inválida!";
    }

    private String commandUsar(String itemName) {
        //verifica os puzzles do ambiente
        for (Puzzle puzzle : currentLocation.getPuzzles()) {
            String[] split = puzzle.getSolution().getActions().split(" ");// quebra o action
            if (itemName.equalsIgnoreCase(split[1])){// Compara o item (ex: chave) com o action splitado (usar "chave")
                if (verifyRequiredItems(puzzle.getSolution().getRequiredItems())){
                    analyzeResult(List.of(puzzle.getResult()));
                    return "Você resolveu o puzzle, fique atento ao seu inventario e ao ambiente ao seu redor!";
                }
                return "você não tem os itens necessários ainda!";
            }
        }
        return "Puzzle não encontrado";
    }

    private Boolean verifyRequiredItems(List<Integer> requiredItems) {
        List<Item> aux = new ArrayList<>();
        for (Integer requiredItemId : requiredItems) {
            for (Item item : inventario) {
                if (Objects.equals(item.getId(), requiredItemId)){
                    aux.add(item);
                    continue;
                }
            }
        }
        return aux.size() == requiredItems.size();
    }


    private void analyzeResult(List<Result> responses) {
        System.out.println(responses);
        for (Result response : responses) {
            for (Integer idActive: response.getActive()) {
                active(idActive);
            }
            for (Integer idLoseItems : response.getLose_item()) {
                inventario.removeIf(id -> Objects.equals(id.getId(), idLoseItems));
            }

            //Lose life
        }
    }

    /**
     * responsável pela lógica de verificar um id e ativar o elemento do jogo correspondente, setando "inactive" como false
     * @param idActive - id do objeto (exit, item, npc) que vai ser ativado
     */
    private void active(Integer idActive) {
        for (Location location : game.getLocations()) {
            for (Exit exit : location.getExits()) {
                if (Objects.equals(idActive, exit.getTargetLocationId())){
                    System.out.println("mudei o inactive do " + idActive);
                    exit.setInactive(false);
                    return;
                }
            }

            for (Item item : location.getItems()) {
                if (Objects.equals(idActive, item.getId())){
                    item.setInactive(false);
                    return;
                }
            }

            for (Npc npc : location.getNpcs()) {
                if (Objects.equals(idActive, npc.getId())){
                    npc.setInactive(false);
                    return;
                }
            }
        }
    }


    public String initial(){
        StringBuilder sb = new StringBuilder();
        sb.append("jogo desenvolvido por ");
        sb.append(game.getAuthor()).append("\n\n");
        sb.append("Bem vindo ao jogo: ");
        sb.append(game.getTitle()).append("\n");
        sb.append(game.getDescription()).append("\n");
        return sb.toString();
    }

    public String startLocation(){
        Location startLocation = findLocation(game.getStartLocationId());
        currentLocation = startLocation;
        StringBuilder sb = locationInformation(startLocation);
        return sb.toString();
    }

    /**
     * Método responsável por preparar a String que vai ser exibida para representar uma sala
     * @param location - Location
     * @return - String apresentando as informações da sala
     */
    private StringBuilder locationInformation(Location location) {
        StringBuilder sb = new StringBuilder();
        sb.append(location.getName()).append("\n");
        sb.append(location.getDescription()).append("\n");
        for (Exit exit : location.getExits()) {
            if (!exit.getInactive()){
                sb.append("a ").append(exit.getDirection()).append(", ").append(exit.getDescription()).append("\n");
            }
        }
        for (Item item : location.getItems()) {
            if (!item.getInactive()){
                sb.append(item.getName()).append(" - ").append(item.getDescription()).append("\n");
            }
        }
        for (Npc npc : location.getNpcs()) {
            if (!npc.getInactive()){
                sb.append(npc.getName()).append(", ").append(npc.getDescription()).append("\n");
            }
        }
        for (Puzzle puzzle : location.getPuzzles()) {
            sb.append(puzzle.getDescription()).append("\n");
        }
//        for (Enemy enemy : location.getEnemies()) {
//            sb.append(enemy.getAttack() + enemy.getDefense()).append("\n");
//        }
        return sb;
    }

    /**
     *
     * @param id - id da Location
     * @return - Location
     */
    private Location findLocation(Integer id) {
        for (Location location : game.getLocations()) {
            if (location.getId() == id){
                return location;
            }
        }
        return null;
    }

    public String currentLocation() {
        return locationInformation(currentLocation).toString();
    }
}
