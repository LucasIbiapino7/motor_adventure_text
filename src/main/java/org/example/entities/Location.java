package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private Integer id;
    private String name;
    private String description;
    private List<Exit> exits = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Npc> npcs = new ArrayList<>();
    private List<Puzzle> puzzles = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public void setExits(List<Exit> exits) {
        this.exits = exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(List<Npc> npcs) {
        this.npcs = npcs;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addExit(Exit exit){
        exits.add(exit);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void addNpc(Npc npc) {
        npcs.add(npc);
    }

    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exits=" + exits +
                ", items=" + items +
                ", npcs=" + npcs +
                ", puzzles=" + puzzles +
                ", enemies=" + enemies +
                '}';
    }

}
