package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String title;
    private String description;
    private String author;
    private Integer startLocationId;
    private Integer max_itens;
    private Integer max_turns_easy;
    private Integer max_turns_normal;
    private Integer max_turns_hard;
    private Integer attack;
    private Integer defense;
    private Integer life;
    private List<Location> locations = new ArrayList<>();

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(Integer startLocationId) {
        this.startLocationId = startLocationId;
    }

    public Integer getMax_itens() {
        return max_itens;
    }

    public void setMax_itens(Integer max_itens) {
        this.max_itens = max_itens;
    }

    public Integer getMax_turns_easy() {
        return max_turns_easy;
    }

    public void setMax_turns_easy(Integer max_turns_easy) {
        this.max_turns_easy = max_turns_easy;
    }

    public Integer getMax_turns_normal() {
        return max_turns_normal;
    }

    public void setMax_turns_normal(Integer max_turns_normal) {
        this.max_turns_normal = max_turns_normal;
    }

    public Integer getMax_turns_hard() {
        return max_turns_hard;
    }

    public void setMax_turns_hard(Integer max_turns_hard) {
        this.max_turns_hard = max_turns_hard;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location){
        locations.add(location);
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", startLocationId=" + startLocationId +
                ", max_itens=" + max_itens +
                ", max_turns_easy=" + max_turns_easy +
                ", max_turns_normal=" + max_turns_normal +
                ", max_turns_hard=" + max_turns_hard +
                ", attack=" + attack +
                ", defense=" + defense +
                ", life=" + life +
                ", locations=" + locations +
                '}';
    }
}
