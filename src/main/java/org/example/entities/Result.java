package org.example.entities;

import jakarta.json.JsonArray;
import jakarta.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Integer> active = new ArrayList<>();
    private List<Integer> lose_item = new ArrayList<>();
    private Integer lose_life;

    public Result() {
    }

    public List<Integer> getActive() {
        return active;
    }

    public void setActive(JsonArray active) {
        for (JsonValue jsonValue : active) {
            this.active.add(Integer.parseInt(jsonValue.toString()));
        }
    }

    public List<Integer> getLose_item() {
        return lose_item;
    }

    public void setLose_item(JsonArray lose_item) {
        for (JsonValue jsonValue : lose_item) {
            this.lose_item.add(Integer.parseInt(jsonValue.toString()));
        }
    }

    public Integer getLose_life() {
        return lose_life;
    }

    public void setLose_life(Integer lose_life) {
        this.lose_life = lose_life;
    }

    @Override
    public String toString() {
        return "Result{" +
                "active=" + active +
                ", lose_item=" + lose_item +
                ", lose_life=" + lose_life +
                '}';
    }
}
